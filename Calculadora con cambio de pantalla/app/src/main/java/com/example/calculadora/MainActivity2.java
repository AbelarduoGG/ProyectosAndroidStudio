package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView tvtunombre;

    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvtunombre = findViewById(R.id.tvtunombre);

        //Obtiene toda la información enviada en Activity 1 y se guarda.
        b = getIntent().getExtras();

        //Mandamos la información a la etiqueta de tunombre, poniendo el identificador de la variable "Nombre"
        tvtunombre.setText(b.getString("Nombre"));

    }
}