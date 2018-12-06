package com.example.david.http_mysql_php;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.david.http_mysql_php.controlador.AnalizadorJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Examen05 extends AppCompatActivity {

    ListView listViewAlumnosCarrera;
    ArrayList<ArrayList> listaDatos2 = new ArrayList<ArrayList>();
    ArrayList<String> listaDatos = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    RadioGroup radioCarreras;
    RadioButton radioISC;
    RadioButton radioIM;
    RadioButton radioIIA;
    RadioButton radioLA;
    RadioButton radioLC;

    String datos = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen05);

        listViewAlumnosCarrera = findViewById(R.id.listaAlumnosCarrera);
        radioCarreras = findViewById(R.id.radioCarreras);
        radioISC = findViewById(R.id.radioISC);
        radioIM = findViewById(R.id.radioIM);
        radioIIA = findViewById(R.id.radioIIA);
        radioLA = findViewById(R.id.radioLA);
        radioLC = findViewById(R.id.radioLC);


        new ConsultarAlumnosCarrera().execute();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaDatos);
        listViewAlumnosCarrera.setAdapter(adapter);

        radioCarreras.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                listaDatos.clear();

                for (int i = 0; i < listaDatos2.size(); i++) {
                    for (int j = 0; j < listaDatos2.get(i).size(); j++) {
                        switch (checkedId) {
                            case R.id.radioISC:
                                if (radioISC.getText().toString().equals(listaDatos2.get(i).get(6).toString())) {
                                    datos =  listaDatos2.get(i).get(0).toString() + " - " +
                                            listaDatos2.get(i).get(1).toString() + " - " +
                                            listaDatos2.get(i).get(2).toString() + " - " +
                                            listaDatos2.get(i).get(3).toString() + " - " +
                                            listaDatos2.get(i).get(4).toString() + " - " +
                                            listaDatos2.get(i).get(5).toString() + " - " +
                                            listaDatos2.get(i).get(6).toString();
                                    listaDatos.add(datos);
                                }
                                break;

                            case R.id.radioIM:
                                if (radioIM.getText().toString().equals(listaDatos2.get(i).get(6).toString())) {
                                    datos =  listaDatos2.get(i).get(0).toString() + " - " +
                                            listaDatos2.get(i).get(1).toString() + " - " +
                                            listaDatos2.get(i).get(2).toString() + " - " +
                                            listaDatos2.get(i).get(3).toString() + " - " +
                                            listaDatos2.get(i).get(4).toString() + " - " +
                                            listaDatos2.get(i).get(5).toString() + " - " +
                                            listaDatos2.get(i).get(6).toString();
                                    listaDatos.add(datos);
                                }
                                break;

                            case R.id.radioIIA:
                                if (radioIIA.getText().toString().equals(listaDatos2.get(i).get(6).toString())) {
                                    datos =  listaDatos2.get(i).get(0).toString() + " - " +
                                            listaDatos2.get(i).get(1).toString() + " - " +
                                            listaDatos2.get(i).get(2).toString() + " - " +
                                            listaDatos2.get(i).get(3).toString() + " - " +
                                            listaDatos2.get(i).get(4).toString() + " - " +
                                            listaDatos2.get(i).get(5).toString() + " - " +
                                            listaDatos2.get(i).get(6).toString();
                                    listaDatos.add(datos);
                                }
                                break;

                            case R.id.radioLA:
                                if (radioLA.getText().toString().equals(listaDatos2.get(i).get(6).toString())) {
                                    datos =  listaDatos2.get(i).get(0).toString() + " - " +
                                            listaDatos2.get(i).get(1).toString() + " - " +
                                            listaDatos2.get(i).get(2).toString() + " - " +
                                            listaDatos2.get(i).get(3).toString() + " - " +
                                            listaDatos2.get(i).get(4).toString() + " - " +
                                            listaDatos2.get(i).get(5).toString() + " - " +
                                            listaDatos2.get(i).get(6).toString();
                                    listaDatos.add(datos);
                                }
                                break;

                            case R.id.radioLC:
                                if (radioLC.getText().toString().equals(listaDatos2.get(i).get(6).toString())) {
                                    datos =  listaDatos2.get(i).get(0).toString() + " - " +
                                            listaDatos2.get(i).get(1).toString() + " - " +
                                            listaDatos2.get(i).get(2).toString() + " - " +
                                            listaDatos2.get(i).get(3).toString() + " - " +
                                            listaDatos2.get(i).get(4).toString() + " - " +
                                            listaDatos2.get(i).get(5).toString() + " - " +
                                            listaDatos2.get(i).get(6).toString();
                                    listaDatos.add(datos);
                                }
                                break;
                        }
                    }
                }

                adapter = new ArrayAdapter<String>(Examen05.this, android.R.layout.simple_list_item_1, listaDatos);
                listViewAlumnosCarrera.setAdapter(adapter);
            }
        });
    }

    class ConsultarAlumnosCarrera extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            AnalizadorJSON analizadorJSON = new AnalizadorJSON();

            String url = "http://10.0.2.2/Practicas/CRUD-MySQL-PHP/HTTP_Android/consultas_alumnos.php";
            JSONObject jsonObject = analizadorJSON.peticionHTTP(url, "POST");
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("alumnos");

                for (int i=0; i<jsonArray.length(); i++) {
                    ArrayList<String> listaDatosAlumno = new ArrayList<>();

                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("nc"));
                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("n"));
                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("pa"));
                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("sa"));
                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("e"));
                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("s"));
                    listaDatosAlumno.add(jsonArray.getJSONObject(i).getString("c"));

                    listaDatos2.add(listaDatosAlumno);

                    datos = jsonArray.getJSONObject(i).getString("nc") + " - " +
                            jsonArray.getJSONObject(i).getString("n") + " - " +
                            jsonArray.getJSONObject(i).getString("pa") + " - " +
                            jsonArray.getJSONObject(i).getString("sa") + " - " +
                            jsonArray.getJSONObject(i).getString("e") + " - " +
                            jsonArray.getJSONObject(i).getString("s") + " - " +
                            jsonArray.getJSONObject(i).getString("c");

                    listaDatos.add(datos);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
