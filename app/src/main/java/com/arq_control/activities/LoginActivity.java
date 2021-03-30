package com.arq_control.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.arq_control.R;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.buttonRegistro);
        final Button entradaButton = findViewById(R.id.buttonEntrada);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        // Evitamos la salida del teclado por defecto, hasta que el usuario lo pida.
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

 /*   public void doLogin (View view){
        String email = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.equals("angel@arqnatura.com") && password.equals("12345")) {
            // Login correcto
            Toast.makeText(this, "Bienvenido a la app Arq Control", Toast.LENGTH_SHORT);

            Intent i = new Intent ( LoginActivity.this, MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Usuario y/o contraseña incorrectos.", Toast.LENGTH_SHORT);
        }

   }
 */

    // Para no tener que acceder con email y contraseña durante las pruebas
    public void initMainActivity(View view){
        Intent intentMainActivity = new Intent ( this, MainActivity.class);
        startActivity(intentMainActivity);
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}