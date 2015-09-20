package com.solinpromex.elpasojuarezexperience;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class ConfirmarComentarioHotel extends AppCompatActivity  {

    private TextView hotel_calificado,valor_calificacion;
    private String user_email, id_del_hotel, nombre_del_hotel;
    private int hotel_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_comentario_hotel);

        SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar);

        hotel_calificado = (TextView) findViewById(R.id.hotel_calificado);
        valor_calificacion = (TextView) findViewById(R.id.valor_calificacion);



        hotel_id = getIntent().getIntExtra("id_hotel", 0);
        id_del_hotel = String.valueOf(hotel_id);

        nombre_del_hotel = getIntent().getStringExtra("nombre_hotel");

        hotel_calificado.setText(nombre_del_hotel);
       // hotel_calificado.setText(id_del_hotel);


        //de prueba
        //hotel_calificado.setText(hotel_calificado);
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
