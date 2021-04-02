package com.arq_control.app;

import android.app.Application;

import com.arq_control.models.ObraDB;
import com.arq_control.models.VisitaDB;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApp extends Application {

    public static AtomicLong ObraID = new AtomicLong();
    public static AtomicLong VisitaID = new AtomicLong();

    @Override
    public void onCreate() {
        // Esta clase se ejecutará antes de nuestro MainActivity
        // Aquí leemos de la BD cuál es el máximo valor de ID que tenemos
        super.onCreate();
        // Método para configurar la BD
        initRealm();

        Realm realm = Realm.getDefaultInstance();
        ObraID = getIdByTable(realm, ObraDB.class);
        VisitaID = getIdByTable(realm, VisitaDB.class);

        realm.close();

//        initPreferences();

    }

    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .allowWritesOnUiThread (true)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

 /*   private void initPreferences() {
        prefs = getSharedPreferences(Constantes.PREFS_LOGIN, Context.MODE_PRIVATE);
    }

  */
    private <T extends RealmObject> AtomicLong getIdByTable (Realm realm, Class<T> anyClass){
        RealmResults<T>  results = realm.where(anyClass).findAll();
        return (results.size()>0) ? new AtomicLong(results.max("id").intValue()) : new AtomicLong();
    }

}
