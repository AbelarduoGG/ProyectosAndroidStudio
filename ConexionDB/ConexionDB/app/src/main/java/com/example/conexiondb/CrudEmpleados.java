package com.example.conexiondb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CrudEmpleados extends AppCompatActivity {
    EditText etcodigo,etnombre,etapellido,etpuesto,etsueldo;
    String ip = "192.168.90.190";
    Button btnconsulta,btnguarda,btnmodifica,btnelimina;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_empleados);

        etcodigo = findViewById(R.id.etcodigoEmp);
        etnombre = findViewById(R.id.etnombreEmp);
        etapellido = findViewById(R.id.etnombreEmp);
        etapellido = findViewById(R.id.etapellidoEmp);
        etpuesto = findViewById(R.id.etpuesto);
        etsueldo = findViewById(R.id.etsueldo);
        btnconsulta = findViewById(R.id.btnconsultarEmp);
        btnguarda = findViewById(R.id.btnguardarEmp);
        btnmodifica = findViewById(R.id.btnmodificarEmp);
        btnelimina = findViewById(R.id.btneliminarEmp);
        requestQueue = Volley.newRequestQueue(this);

        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta("http://" + ip + "/ejemplomovil/consultaEmp.php?codigo="+etcodigo.getText().toString());
            }
        });//cierre del onclick consulta

        btnguarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operaciones("http://" + ip + "/ejemplomovil/insertarEmp.php");
            }
        });//cierre del onclick guarda

        btnmodifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operaciones("http://" + ip + "/ejemplomovil/modificaEmp.php");
            }
        });//cierre del onclick modifica

        btnelimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operaciones("http://" + ip + "/ejemplomovil/eliminaEmp.php");
            }
        });//cierre del onclick elimina
    }//cierre del metodo oncreate

    private void operaciones(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) { //en caso de una respuesta exitosa
                System.out.println(response);
                if(response.equals("1")){
                    Toast.makeText(getApplicationContext(), "operación exitosa", Toast.LENGTH_LONG).show();
                    etcodigo.setText("");
                    etnombre.setText("");
                    etapellido.setText("");
                    etpuesto.setText("");
                    etsueldo.setText("");

                }
                else{
                    Toast.makeText(getApplicationContext(), "operación no exitosa", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { //cuando se ejecuta la petición en el servidor y hay un error respondera con un errorRespose
                Toast.makeText(getApplicationContext(),"Error"+ error.toString(), Toast.LENGTH_LONG).show();
                System.out.println("ERROR Voley: "+ error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<>();
                parametros.put("codigo",etcodigo.getText().toString());
                parametros.put("nombre",etnombre.getText().toString());
                parametros.put("apellido",etapellido.getText().toString());
                parametros.put("puesto",etpuesto.getText().toString());
                parametros.put("sueldo",etsueldo.getText().toString());
                parametros.put("tipo","Empleado");
                return parametros;
            }

        };
        // remove caching
        stringRequest.setShouldCache(false);
        // Wait 30 seconds and don't retry more than once
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(stringRequest);
    }//cierre del método operaciones

    private void consulta(String url){
        JsonArrayRequest jsonarrayRequest = new JsonArrayRequest
                (url, new Response.Listener<JSONArray>() {
                    //respuesta exitosa por parte del servidor
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject= null;
                        for(int i=0; i < response.length();i++){
                            try{//extrayendo el objecto json de cada una de las posiciones del arreglo
                                jsonObject = response.getJSONObject(i);

                                etcodigo.setText(jsonObject.getInt("idempleados")+"");
                                etnombre.setText(jsonObject.getString("nombre"));
                                etapellido.setText(jsonObject.getString("apellido"));
                                etpuesto.setText(jsonObject.getString("puesto"));
                                etsueldo.setText(jsonObject.getDouble("sueldo")+"");

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
                                Toast.makeText(getApplicationContext(),"Empleado no encontrado",Toast.LENGTH_LONG).show();
                                etnombre.setText("");
                                etapellido.setText("");
                                etpuesto.setText("");
                                etsueldo.setText("");
                            }
                        });
        requestQueue.add(jsonarrayRequest);
    }//cierre del método consulta

}//cierre de la clase