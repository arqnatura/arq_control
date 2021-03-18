package com.arq_control.app;

import android.app.Application;

import com.arq_control.models.ObraDB;
import com.arq_control.models.VisitaDB;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApp extends Application {
    public static AtomicInteger ObraID = new AtomicInteger();
    public static AtomicInteger VisitaID = new AtomicInteger();

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
    }

    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable (Realm realm, Class<T> anyClass){
        RealmResults<T>  results = realm.where(anyClass).findAll();
        return (results.size()>0) ? new AtomicInteger(results.max("id").intValue())
                : new AtomicInteger();
    }

}
