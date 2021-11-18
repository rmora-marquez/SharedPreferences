package edu.ieu.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText etMail;
    private EditText etNombre;
    private EditText etDatos;
    private Button btnEjecutar;
    private Button btnGuardar;
    private Button btnRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind - enlazado de objetos
        etMail = findViewById(R.id.etEmail);
        btnEjecutar = findViewById(R.id.boton_ejecutar);
        etDatos = findViewById(R.id.et_datos);
        etNombre = findViewById(R.id.et_nombre);
        btnGuardar = findViewById(R.id.btn_guardar_contacto);
        btnRecuperar = findViewById(R.id.btn_recuperar_contacto);

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        etMail.setText( preferences.getString("email","") );

        btnEjecutar.setOnClickListener(view -> {
            guardarYSalir();
        });

        btnGuardar.setOnClickListener( view -> {
            guardarContacto();
        });

        btnRecuperar.setOnClickListener( view -> {
            recuperarContacto();
        });
    }

    private void recuperarContacto() {
        //var miVar = 'hola';
        //if(miVar == 'mundo') Js, C#, python, Vb,

        String nombre = etNombre.getText().toString().toUpperCase(Locale.ROOT);
        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String datos = preferences.getString(nombre, "");
        if(datos.equals("")) {
            Toast.makeText(this, "No existe el nombre en la agenda", Toast.LENGTH_SHORT).show();
        }else{
            etDatos.setText(datos);
        }
    }

    private void guardarContacto() {
        String nombre = etNombre.getText().toString().toUpperCase(Locale.ROOT);
        String datos = etDatos.getText().toString();
        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(nombre,datos);
        editor.commit();
        Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show();
    }

    private void guardarYSalir() {
        SharedPreferences preferences = getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String email = etMail.getText().toString();
        // llave key-> valor
        editor.putString("email", email);
        editor.commit();
        //Cierra la aplicacion
        finish();

    }

}