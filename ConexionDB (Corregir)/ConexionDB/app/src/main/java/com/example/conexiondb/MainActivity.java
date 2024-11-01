package com.example.conexiondb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText etusuario, etpass;
    Button btnentrar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etusuario = findViewById(R.id.etUsuario);
        etpass = findViewById(R.id.etPassword);
        btnentrar = findViewById(R.id.btnEntrar);
        requestQueue = Volley.newRequestQueue(this);

        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //la ip 10.0.2.2 android la reconoce como localhost
                //inicioSesion("http://10.0.2.2:8080/ejemplomovil/validaentradaMovil.php?usuario="+ etusuario.getText().toString());
                //utilizando la ip de la laptop o computadora.
                inicioSesion("http://192.168.90.130/ejemplomovil/validaentradaMovil.php?usuario=" + etusuario.getText().toString());
                //conexion desde el dispositivo movil deben usar un hosting (conexion segura https) o tener un certificado digital (SSL)
                //inicioSesion("https://ip o nombre_domino del hosting/ejemplomovil/validaentradaMovil.php?usuario="+ etusuario.getText().toString());
            }
        });

    }//cierre del método oncreate

    /*Agregar métodos personalizados*/
    private void inicioSesion(String url) {
        JsonArrayRequest jsonarrayRequest = new JsonArrayRequest
                (url, new Response.Listener<JSONArray>() {
                    //respuesta exitosa por parte del servidor
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;
                        for (int i = 0; i < response.length(); i++) {
                            try {//extrayendo el objecto json de cada una de las posiciones del arreglo
                                jsonObject = response.getJSONObject(i);

                                //la validación de si el password es correcto
                                if (jsonObject.getString("contraseña").equals(etpass.getText().toString())) {
                                    Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_LONG).show();
                                    Intent ventana = null;
                                    if (jsonObject.getString("tipo").equals("Empleado")) {
                                        Toast.makeText(getApplicationContext(), jsonObject.getString("puesto") + " " + jsonObject.getString("nombre") + " " + jsonObject.getString("apellido"), Toast.LENGTH_LONG).show();
                                        ventana = new Intent(MainActivity.this, MenuEmpleado.class);
                                    } else {
                                        Toast.makeText(getApplicationContext(), jsonObject.getString("nombre") + " " + jsonObject.getString("apellido"), Toast.LENGTH_LONG).show();
                                        ventana = new Intent(MainActivity.this, MenuCliente.class);
                                    }
                                    startActivity(ventana);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Usuario y contraseña incorrectos", Toast.LENGTH_LONG).show();
                                }//cierre del else
                            }//cierre del try
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }//cierre del for
                    }//cierre del onResponse

                }, //en caso de respuesta de error por parte del servidor
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                System.out.println(error);
                                //Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), "Usuario y contraseña incorrectos", Toast.LENGTH_LONG).show();
                            }
                        });
        requestQueue.add(jsonarrayRequest);
    }//cierre del método iniciSesion
}

