package com.example.autolavado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Recuperamos el Intent que inició esta actividad
        Intent v = getIntent();

        //verificamos si hay datos extras en el Intent
        if (v != null){

            //Recuperamos la cadena de texto ingresada desde el otro activity
            String nombre = v.getStringExtra("nombre");
            String marca = v.getStringExtra("marca");
            String ano = v.getStringExtra("ano");

            //Muestra la cadena de texto en el TextView
            TextView mostrarnombre = findViewById(R.id.mostrarnombre);
            mostrarnombre.setText(nombre);
            TextView mostrarmarca = findViewById(R.id.mostrarmarca);
            mostrarmarca.setText(marca);
            TextView mostrarano = findViewById(R.id.mostrarano);
            mostrarano.setText(ano);

        }





    }
}