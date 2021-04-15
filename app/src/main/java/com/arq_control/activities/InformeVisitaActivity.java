package com.arq_control.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.arq_control.R;
import com.arq_control.models.ObraDB;
import com.arq_control.models.VisitaDB;
import com.arq_control.ui.visitas.OnNuevaPhotoListener;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

import io.realm.Realm;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class InformeVisitaActivity extends AppCompatActivity
        implements OnNuevaPhotoListener {

    TextView textViewInfo;
    long idVisita;
    long idObra;
    Realm realm, realmObra;
    VisitaDB visitaDB;
    ObraDB obraDB;
    FloatingActionButton fab;

    // Parámetros para la toma y guardado de fotografías
    private final String CARPETA_RAIZ="imagenesArqControl/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"fotosVisitas";
    final int COD_SELECCION=10;
    //private static final int COD_SELECCION = 10;
    final int COD_FOTO=20;
    private ImageView imageView;
    private String path;
//    private Uri existPath;
    private File fileImagen;

//    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe_visita);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout_visita);

/*        String uri = String.valueOf(existPath);
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        imageView.setImageDrawable(imagen);
 */

        fab = (FloatingActionButton) findViewById(R.id.fabFoto);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validamos los permisos de acceso a los contenidos del dispositivo
                if(validarPermisos()){
                    fab.setEnabled(true);
                    cargarImagen(view);
                }else{
                    fab.setEnabled(false);
                }
            }
        });

        // Código del informe
        textViewInfo = (TextView) findViewById(R.id.texto_titulo_visita);
        // Rescatamos el ID de la visita.
        Bundle extras = getIntent().getExtras();
        idVisita = extras.getLong(VisitaDB.VISITADB_ID);
//        idObra = extras.getLong(ObraDB.OBRADB_ID);
        // Rescatamos los registros de la base de datos Realm de esa visita.
        realm = Realm.getDefaultInstance();
//        realmObra = Realm.getDefaultInstance();
        // Tabla sobre la que queremos ejecutar la consulta VisitaDB.class
        // Buscamos el primer elemento que concuerde con la consulta.
        visitaDB = realm.where(VisitaDB.class)
                .equalTo(VisitaDB.VISITADB_ID, idVisita)
                .findFirst();
/*        obraDB = realmObra.where(ObraDB.class)
                .equalTo(obraDB.OBRADB_ID, idObra)
                .findFirst();
 */

        toolBarLayout.setTitle(visitaDB.getTituloVisita());

        textViewInfo.setText("\nDatos de la Visita ___________"+
//                "\n   Promotor: "+obraDB.getPromotor()+
                "\n   Motivo: "+visitaDB.getTituloVisita()+
                "\n   Fecha: "+visitaDB.getFecha()+
                "   Visita Nº: "+visitaDB.getNumeroVisita()+
                "\nDescripción _______________\n"+visitaDB.getDescripcion()+
                "\n   Fecha Visita: "+visitaDB.getFecha()+
                "    Id: "+visitaDB.getId()+
                "\n   Path: "+visitaDB.getAlmacenFoto());

        // Cargamos la imagen en el imageHeader.
        imageView = (ImageView) findViewById(R.id.imageHeader);
            Glide.with(this)
                    .load(visitaDB.getAlmacenFoto())
                    .into(imageView);


    }

    private boolean validarPermisos() {
        // Método para comprobar y/o solicitar permisos de acceso a la cámara
        // y/o la memoria externa del dispositivo.
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }
        if((checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED)&&
                (checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)){
            return true;
        }
        if((shouldShowRequestPermissionRationale(CAMERA))||
                (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA},100);
        }
        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED
            && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                fab.setEnabled(true);
            }else{
                solicitarPermisosManual();
            }
        }
    }
    private void solicitarPermisosManual() {
        final CharSequence[] opciones={"Sí", "No"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(
                InformeVisitaActivity.this);
        alertOpciones.setTitle("¿Desea activar los permisos de forma manual?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Sí")){
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri=Uri.fromParts("package",getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Los permisos no fueron aceptados.",
                            Toast.LENGTH_SHORT);
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
    }
    private void cargarDialogoRecomendacion() {
        // Diálogo donde le indicamos al usuario de que debe dar los permisos a la aplicación
        AlertDialog.Builder dialogo=new AlertDialog.Builder(InformeVisitaActivity.this);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Aceptar los permisos para el funcionamiento de la aplicación");
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA},100);
                }
            }
        });
        dialogo.show();
    }


    private void cargarImagen(View view) {

        final CharSequence[] opciones={"Hacer una Fotografía","Galería de Imágenes","CANCELAR"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(
                InformeVisitaActivity.this);
        alertOpciones.setTitle("Selecciona una Opción");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Hacer una Fotografía")){
                    hacerFotografia();
                }else{
                    if (opciones[i].equals("Galería de Imágenes")){
                        //ACTION_GET_CONTENT accede a todos los contenidos. ACTION_PICK a la Galería.
                        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Selecciona la Aplicación"),COD_SELECCION);
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });

        alertOpciones.show();
    }

    private void hacerFotografia() {
        // Creamos una nueva imagen y su ruta
        fileImagen = new File(Environment.getExternalStorageDirectory(),RUTA_IMAGEN);
        Boolean esCreada = fileImagen.exists();  // Si no existe el archivo de la imagen la creamos.
        String nombreImagen = "";
        if(esCreada==false){
            esCreada=fileImagen.mkdirs();
        }
        if(esCreada==true){
            Long consecutivo = System.currentTimeMillis()/1000; // Devuelve la hora actual.
            nombreImagen=(consecutivo.toString()+".jpg");
        }
        // Ruta de almacenamiento.
        path = Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN+
                File.separator+nombreImagen;

        // Lanzamos la opción de la cámara del dispositivo.
        fileImagen = new File(path);
        Intent intent = null;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            String authorities=getApplicationContext().getPackageName()+".provider";
            Uri imageUri= FileProvider.getUriForFile(this,authorities,fileImagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }else{
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen));
        }
        startActivityForResult(intent,COD_FOTO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){

            switch (requestCode){
                // Caso de seleccionar una fotografía del dispositivo
                case COD_SELECCION:
                    Uri existPath=data.getData();
                    //imageView.setImageURI(existPath);
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(existPath, projection,
                            null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    path = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    imageView.setImageBitmap(bitmap);

                    break;
                // Caso de hacer una nueva fotografía
                case COD_FOTO:
                    MediaScannerConnection.scanFile(this,new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener(){
                                @Override
                                public void onScanCompleted(String s, Uri uri) {
                                    Log.i("Ruta de almacenamiento","Path: "+path);
                                }
                            });
                    // Asignamos la foto al imageView.
                    bitmap = BitmapFactory.decodeFile(path);
                    imageView.setImageBitmap(bitmap);

                    break;
            }
        }

        Toast.makeText(this, "Debe guardar la fotografía.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPhotoGuardarClickListener(String almacenFoto) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    VisitaDB editVisita = realm.where(VisitaDB.class)
                            .equalTo(VisitaDB.VISITADB_ID, idVisita)
                            .findFirst();
                    editVisita.setAlmacenFoto(almacenFoto);
                    // Crea o actualiza la ruta de la fotografía
                    realm.copyToRealmOrUpdate(editVisita);
                }
            });
    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_visita, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
           int id = item.getItemId();
        if (id == R.id.action_regresar) {
            Toast.makeText(this, "Regresa a la pantalla anterior.", Toast.LENGTH_SHORT).show();
            onBackPressed();
            return true;
        }
        if (id == R.id.action_guardar) {
            onPhotoGuardarClickListener(path);
            Toast.makeText(this, "Imagen guardada.", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
        }

}