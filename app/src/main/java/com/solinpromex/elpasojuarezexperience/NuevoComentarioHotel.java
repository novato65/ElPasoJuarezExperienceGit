package com.solinpromex.elpasojuarezexperience;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class NuevoComentarioHotel extends AppCompatActivity  {

    private TextView hotel_calificado,valor_calificacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_comentario_hotel);

        SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar);

        hotel_calificado = (TextView) findViewById(R.id.hotel_calificado);
        valor_calificacion = (TextView) findViewById(R.id.valor_calificacion);
        hotel_calificado.setText(getIntent().getStringExtra("nombre_hotel"));
        seekbar.setProgress(5);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                valor_calificacion.setText(String.valueOf(progress));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
        //test 22
        //TEST
    }


}
