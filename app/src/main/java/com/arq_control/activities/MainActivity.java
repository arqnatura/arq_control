package com.arq_control.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.arq_control.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Código de acción del botón flotante +
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Nueva Obra", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent miIntent = new Intent(MainActivity.this, ObrasActivity.class);
                startActivity(miIntent);
            }
        });

/*         // Instanciamos el método de Realm getDefaultInstance
        realm = Realm.getDefaultInstance();
*/
        // Código del NavegationDrawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
            // Pasando cada ID de menú como un conjunto de ID porque cada menú
            // debe considerarse como destinos de nivel superior.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_obras,
                R.id.nav_finalizadas, R.id.nav_operadores, R.id.nav_salir, R.id.nav_politica,
                R.id.nav_manual_uso)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
     }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_salir) {
        Toast.makeText(this, "Salir de la aplicación", Toast.LENGTH_LONG).show();
//        onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        return true;
        }
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Se presionó el menú de settings", Toast.LENGTH_SHORT).show();
            return true;
        }        return super.onOptionsItemSelected(item);
    }

/*    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
*/
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
        return super.onOpionsItemSelected(item);

    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Añadir elementos al menú; agrega elementos a la barra de acción si está presente.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

 /*   public void OnObraVisitar(View view) {
        Snackbar.make(view, "Nueva Visita", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        Toast.makeText(this, "Ver Listado de Visitas en Curso...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, VisitasActivity.class);
        startActivity(i);
    }


    @Override
    public void OnObraVisitar(VisitaDB visitaDB) {
        Toast.makeText(this, "Visitas", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, VisitasActivity.class);
        i.putExtra(VisitaDB.VISITADB_ID, visitaDB.getIdVisita());
        startActivity(i);
    }
 */

    // Acciones de los botones principales
    public void initActivityVisitaNueva(View view){
        Toast.makeText(this, "Crea una nueva visita de obra...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, VisitasActivity.class);
        startActivity(i);
    }
    public void initActivityObraCurso(View view){
        Toast.makeText(this, "Ver Listado de Obras en Curso...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ObrasActivity.class);
        startActivity(i);
    }
/*    public void initActivityArchivo(View view){
        Toast.makeText(this, "Ver Listado de Obras Finalizadas...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, FinalizadasFragment.class);
        startActivity(i);
    }
 */
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


}

