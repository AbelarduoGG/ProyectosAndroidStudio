package com.example.conexiondb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaEmpleados extends AppCompatActivity {
    RequestQueue requestQueue;
    ArrayList listaEmp;
    ListView lvlistaEmp;
    EditText etnomEmpBuscar;
    Button btnbuscarEmp;
    Spinner splistaEmp;
    String ip = "192.168.90.190";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empleados);

        requestQueue = Volley.newRequestQueue(this);
        listaEmp = new ArrayList();

        lvlistaEmp = findViewById(R.id.lvlistaEmp);
        etnomEmpBuscar = findViewById(R.id.etnomEmpBuscar);
        btnbuscarEmp = findViewById(R.id.btnbuscarEmp);
        splistaEmp = findViewById(R.id.splistaEmp);

        btnbuscarEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta("http://" + ip + "/ejemplomovil/consultatodoEmpXnom.php?nom="+ etnomEmpBuscar.getText().toString());
                //llenar(listaEmp);
            }
        });

        consulta("http://" + ip + "/ejemplomovil/consultatodoEmp.php");
        //llenar(listaEmp);
    }

    private void consulta(String url){
        JsonArrayRequest jsonarrayRequest = new JsonArrayRequest
                (url, new Response.Listener<JSONArray>() {
                    //respuesta exitosa por parte del servidor
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject= null;
                        listaEmp.clear();
                        for(int i=0; i < response.length();i++){
                            try{//extrayendo el objecto json de cada una de las posiciones del arreglo
                                jsonObject = response.getJSONObject(i);

                                listaEmp.add(jsonObject.getString("puesto")+" "+jsonObject.getString("nombre") + " "+jsonObject.getString("apellido"));

                                llenar(listaEmp);
                                llenarSpinner(listaEmp);
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
                                listaEmp.clear();
                                llenar(listaEmp);
                                llenarSpinner(listaEmp);
                            }
                        });

        jsonarrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constants.MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonarrayRequest);
    }//cierre del método consulta

    public void llenar(ArrayList datos){
        ArrayAdapter adaptador = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,datos);
        lvlistaEmp.setAdapter(adaptador);
    }

    public void llenarSpinner(ArrayList datos){
        ArrayAdapter adaptador = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,datos);
        lvlistaEmp.setAdapter(adaptador);
    }

    public class Constants{
        public static final int MY_DEFAULT_TIMEOUT = 15000;
    }

}//cierre de la clase ListaEmpleados