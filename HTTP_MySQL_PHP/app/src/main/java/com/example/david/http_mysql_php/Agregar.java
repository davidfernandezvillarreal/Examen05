package com.example.david.http_mysql_php;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.http_mysql_php.controlador.AnalizadorJSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Agregar extends AppCompatActivity {

    EditText cajaNoControl;
    EditText cajaNombre;
    EditText cajaPrimerApellido;
    EditText cajaSegundoApellido;
    EditText cajaEdad;
    EditText cajaSemestre;
    EditText cajaCarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        cajaNoControl = findViewById(R.id.cajaNoControl);
        cajaNombre = findViewById(R.id.cajaNombre);
        cajaPrimerApellido = findViewById(R.id.cajaPrimerApellido);
        cajaSegundoApellido = findViewById(R.id.cajaSegundoApellido);
        cajaEdad = findViewById(R.id.cajaEdad);
        cajaSemestre = findViewById(R.id.cajaSemestre);
        cajaCarrera = findViewById(R.id.cajaCarrera);
    }

    public void agregarRegistro(View v) {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if (ni != null && ni.isConnected()) {
            String nc = cajaNoControl.getText().toString();
            String n = cajaNombre.getText().toString();
            String pa = cajaPrimerApellido.getText().toString();
            String sa = cajaSegundoApellido.getText().toString();
            String e = cajaEdad.getText().toString();
            String s = cajaSemestre.getText().toString();
            String c = cajaCarrera.getText().toString();

            //usar una clase interna para que realice la inserción
            new AgregarAlumnos().execute(nc, n, pa, sa, e, s, c);
            Toast.makeText(this, "AGREGADO", Toast.LENGTH_SHORT).show();
        }
    }

    class AgregarAlumnos extends AsyncTask<String, String, String> {
        @Override                       //VARARGS
        protected String doInBackground(String... datos) {
            Map<String, String> registros = new HashMap<String, String>();

            registros.put("nc", datos[0]);
            registros.put("n", datos[1]);
            registros.put("pa", datos[2]);
            registros.put("sa", datos[3]);
            registros.put("e", datos[4]);
            registros.put("s", datos[5]);
            registros.put("c", datos[6]);

            AnalizadorJSON aJSON = new AnalizadorJSON();

            String url = "http://10.0.2.2/Practicas/CRUD-MySQL-PHP/HTTP_Android/altas_alumnos.php";
            //String url2 = "http://176.48.16.14/HTTP_Android/altas_alumnos.php";
            String metodoEnvio = "POST";

            JSONObject jsonObject = aJSON.peticionHTTP(url, metodoEnvio, registros);
            int r = 0;
            try {
                r = jsonObject.getInt("exito");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (r==1) {
                Log.i("MSJ", "Registro Agregado");
            } else {
                Log.i("MSJ", "Registro NO Agregado");
            }

            Log.i("MSJ JSON", jsonObject.toString());

            //enviar a GUI el mensaje
            //PantallaInicio pi = new PantallaInicio();
            //pi.mostrarErrores(jsonObject);

            return null;
        }
    }
}
