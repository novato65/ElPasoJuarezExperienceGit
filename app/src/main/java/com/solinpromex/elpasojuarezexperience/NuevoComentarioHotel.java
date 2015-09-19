package com.solinpromex.elpasojuarezexperience;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class NuevoComentarioHotel extends AppCompatActivity  {

    private TextView hotel_calificado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_comentario_hotel);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        hotel_calificado = (TextView) findViewById(R.id.hotel_calificado);
        hotel_calificado.setText(getIntent().getStringExtra("nombre_hotel"));
        //test 22
        //TEST
    }


}
