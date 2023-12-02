package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   //Variable para enlazar con elementos visuales (variables globales)
    TextView tvresultados;
    EditText etcantidad1, etcantidad2, etNombre;
    Button btnsuma, btnresta, btnmultiplicacion, btndivision, btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //enlace de variable global con el elemento visual
        etcantidad1 = findViewById(R.id.etcantidad1);
        etcantidad2 = findViewById(R.id.etcantidad2);
        etNombre = findViewById(R.id.etNombre);
        tvresultados = findViewById(R.id.tvresultados);
        btnsuma = findViewById(R.id.btnsuma);
        btnresta = findViewById(R.id.btnresta);
        btndivision = findViewById(R.id.btndivision);
        btnmultiplicacion = findViewById(R.id.btnmultiplicacion);
        btnAceptar = findViewById(R.id.btnAceptar);

        btnsuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    double n1 = Double.parseDouble(etcantidad1.getText().toString());
                    double n2 = Double.parseDouble(etcantidad2.getText().toString());
                    double res = n1 + n2;
                    tvresultados.setText(String.valueOf(res));

                }catch(Exception e){
                    tvresultados.setText("Introduzca dos valores");
                }

            }
        });//cierre onclick suma

        btnresta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    double n1 = Double.parseDouble(etcantidad1.getText().toString());
                    double n2 = Double.parseDouble(etcantidad2.getText().toString());
                    double res = n1 - n2;
                    tvresultados.setText(String.valueOf(res));

                }catch(Exception e){
                    tvresultados.setText("Introduzca dos valores");
                }
            }
        });//cierre onclick resta

        btnmultiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    double n1 = Double.parseDouble(etcantidad1.getText().toString());
                    double n2 = Double.parseDouble(etcantidad2.getText().toString());
                    double res = n1 * n2;
                    tvresultados.setText(String.valueOf(res));
                }catch(Exception e){
                        tvresultados.setText("Introduzca dos valores");
                }
            }
        });//cierre onclick multiplicación

        btndivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    double n1 = Double.parseDouble(etcantidad1.getText().toString());
                    double n2 = Double.parseDouble(etcantidad2.getText().toString());
                    double res = n1 / n2;

                    if(n2 == 0){
                        tvresultados.setText("No se puede dividir entre cero");
                    }else{
                        tvresultados.setText(String.valueOf(res));
                    }

                }catch(Exception e){
                    tvresultados.setText("Introduzca dos valores");
                }
            }
        });//cierre onclick división

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Definimos variable de para el nombre
                String nom = etNombre.getText().toString();

                //Creamos el intent con la pagina actual y a la que se mandará
                Intent v = new Intent(MainActivity.this, MainActivity2.class);
                //Mandamos la variable Nom. Con el identificador de primero
                v.putExtra("Nmobre", nom);

                //Con esto mandamos el intento
                startActivity(v);

                Toast.makeText(MainActivity.this, "Hola, bienvenido", Toast.LENGTH_LONG).show();
            }
        });//cierre onclick Aceptar

        }
    }
