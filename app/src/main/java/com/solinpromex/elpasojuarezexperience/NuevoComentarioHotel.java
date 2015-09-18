package com.solinpromex.elpasojuarezexperience;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NuevoComentarioHotel extends AppCompatActivity {

    private TextView hotel_nombre, hotel_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_comentario_hotel);

        //ponemos el nombre del hotel en el textview
        hotel_nombre = (TextView) findViewById(R.id.nombre_hotel);
        hotel_nombre.setText(getIntent().getStringExtra("nombre_hotel"));
    }


}
