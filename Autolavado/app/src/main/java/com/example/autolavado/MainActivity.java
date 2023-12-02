package com.example.autolavado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    //Declaramos las variables de nuestros objetos
    EditText etano, tvnombre, tvmarca;
    Spinner spmodelo;
    RadioButton rblavado, rblavadoencerado;
    CheckBox cblavadomotor, cbaspirado, cblavadointerior;
    Button btcobrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Indicamos el nombre de nuestras variables a los id de nuestros objetos en xml
        tvnombre = findViewById(R.id.tvnombre);
        tvmarca = findViewById(R.id.tvmarca);
        etano = findViewById(R.id.etano);
        spmodelo = findViewById(R.id.spmodelo);
        rblavado = findViewById(R.id.rblavado);
        rblavadoencerado = findViewById(R.id.rblavadoencerado);
        cblavadomotor = findViewById(R.id.cblavadomotor);
        cbaspirado = findViewById(R.id.cbaspirado);
        cblavadointerior = findViewById(R.id.cblavadointerior);
        btcobrar = findViewById(R.id.btcobrar);

        //Hacemos que el botón mande a nuestra segunda página
        btcobrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //obtenemos el texto ingresado
                String nombre = tvnombre.getText().toString();
                String marca = tvmarca.getText().toString();
                String ano = etano.getText().toString();


                //Creamos un intent
                Intent v = new Intent(MainActivity.this, MainActivity2.class);

                //Ingresamos los datos extras
                v.putExtra("nombre", nombre);
                v.putExtra("marca", marca);
                v.putExtra("ano", ano);

                //inicia la siguiente pantalla
                startActivity(v);
            }
        });




    }
}