package com.edith.tlahuac.app_jaz;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button calendario,registrar;
    EditText telefono,fecha,nombre,direccion;
    private int dia,mes,año;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.nombrelinea);
        telefono = (EditText) findViewById(R.id.telefonolinea);
        direccion = (EditText) findViewById(R.id.direccionlinea);

        calendario = (Button) findViewById(R.id.calendario);
        fecha = (EditText) findViewById(R.id.fecha);
        calendario.setOnClickListener(this);

        registrar = (Button) findViewById(R.id.registrar);
        registrar.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {//ver si contiene datos
            String datoNombre = (String) extras.get("nombre");
            String datoTelefono = (String) extras.get("telefono");
            String datoDireccion = (String) extras.get("direccion");
            String datoFecha = (String) extras.get("fecha");

            nombre.setText(datoNombre);
            telefono.setText(datoTelefono);
            direccion.setText(datoDireccion);
            fecha.setText(datoFecha);


        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if(v==calendario){
            final Calendar c = Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            año=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfyear, int dayOfMonth) {
                    fecha.setText(dayOfMonth+"/"+(monthOfyear+1)+"/"+year);
                }
            }
                    ,dia,mes,año);
            datePickerDialog.show();
        }else if(v == registrar){
            Intent explicit_intent;

            explicit_intent = new Intent(this, Main2Activity.class);
            String auxEdiNombre = nombre.getText().toString();
            String auxEdiTelefono = telefono.getText().toString();
            String auxEdiDireccion = direccion.getText().toString();
            String auxEdiFecha = fecha.getText().toString();

            explicit_intent.putExtra("nombre", auxEdiNombre);
            explicit_intent.putExtra("telefono", auxEdiTelefono);
            explicit_intent.putExtra("direccion", auxEdiDireccion);
            explicit_intent.putExtra("fecha",auxEdiFecha);
            startActivity(explicit_intent);


        }
    }
}
