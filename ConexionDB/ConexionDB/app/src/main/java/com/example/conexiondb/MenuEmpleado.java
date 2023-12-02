package com.example.conexiondb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuEmpleado extends AppCompatActivity {

    ImageButton ibtnopera, ibtnlistaEmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_empleado);

        ibtnopera = findViewById(R.id.ibtnoperaciones);
        ibtnlistaEmp = findViewById(R.id.ibtnlistaEmp);

        ibtnlistaEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent v = new Intent(MenuEmpleado.this, ListaEmpleados.class);
                startActivity(v);
            }
        });

        ibtnopera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent v = new Intent(MenuEmpleado.this, CrudEmpleados.class);
                startActivity(v);
            }
        });

    }
}