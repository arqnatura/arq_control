package com.arq_control.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.View.OnClickListener;

import android.widget.ListView;
import android.widget.Toast;

import com.arq_control.R;
import com.arq_control.ui.gallery.ObrasFragment;
import com.arq_control.ui.gallery.OnObraInteractionListener;
import com.arq_control.models.ObraDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnObraInteractionListener {

    ListView lista;
    List<ObraDB> obrasList;


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Códigos de acciónes de los botónes flotantes +
/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabObra);
        fab.setOnClickListener((view) -> {
                dialogoNuevaObra = new NuevaObraDialogo();
                dialogoNuevaObra.show(getSupportFragmentManager(),"NuevaObraDialogo");
        });
*/
        FloatingActionButton fab = findViewById(R.id.fabObra);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Nueva Obra", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent miIntent = new Intent(MainActivity.this, VisitaActivity.class);
                startActivity(miIntent);
            }
        });


        // Código del NavegationDrawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
            // Pasando cada ID de menú como un conjunto de ID porque cada menú
            // debe considerarse como destinos de nivel superior.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_nueva,
                R.id.nav_obras, R.id.nav_finalizadas, R.id.nav_operadores, R.id.nav_salir)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


     }





    //Personalización de las acciones de los componentes del NavegationDrawer
    //@SuppressWarnings("StatementWithEmptyBody")
    //@Override
/*    public boolean onOpionsItemSelected(MenuItem item) {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.onNavDestinationSelected(item, navController)
//                || super.onOptionsItemSelected(item);

        // Manejar el elemento de la vista de navegación haciendo clic aquí.
        int id = item.getItemId();
        Fragment f = null;

        if (id == R.id.nav_salir){
            System.exit(0);
        } else if (id == R.id.nav_compartir){
            f = new OperadoresFragment();

        }
        if (f != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_view, f)
                    .commit();
        }
        return true;

    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void initActivityObraNueva(View view){
        Toast.makeText(this, "Crea una nueva obra...", Toast.LENGTH_SHORT).show();
        Intent miIntent = new Intent(this, VisitaActivity.class);
        startActivity(miIntent);
    }
    public void initActivityObraCurso(View view){
        Toast.makeText(this, "Ver Listado de Obras en Curso...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ObrasActivity.class);
        startActivity(i);
    }


    public void initActivityArchivo(View view){
        Toast.makeText(this, "Ver Listado de Obras Finalizadas...", Toast.LENGTH_SHORT).show();
    }
    public void initActivityAgenda(View view){
        Toast.makeText(this, "Ver Listado de Operadores...", Toast.LENGTH_SHORT).show();
    }

    // Boton regresar y cerrar toda la aplicación.
    public void initActivitySalir(View view){
        Toast.makeText(this, "Hasta la próxima. Chao!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void initActivityRegresar(View view){
        onBackPressed();
        Toast.makeText(this, "Regresa a la anterior pantalla.", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void OnObraClick(ObraDB obraDB) {

    }

}