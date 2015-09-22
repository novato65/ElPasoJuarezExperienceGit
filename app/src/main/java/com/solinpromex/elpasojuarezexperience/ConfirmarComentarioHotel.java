package com.solinpromex.elpasojuarezexperience;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ConfirmarComentarioHotel extends AppCompatActivity {

    private String user_name, id_hotel,opinion,valoracion,email,nombre_hotel;
    private TextView tvNombreHotel,tvOpinion,tvCalificacion,tvUsername,tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_comentario_hotel);

        tvNombreHotel = (TextView) findViewById(R.id.textView9);
        nombre_hotel = getIntent().getStringExtra("nombre_hotel");
        tvNombreHotel.setText(nombre_hotel);

        tvUsername = (TextView) findViewById(R.id.textView2);
        user_name = getIntent().getStringExtra("user_name");
        tvUsername.setText(user_name + ", su opinión es:");

        tvOpinion = (TextView) findViewById(R.id.textView5);
        opinion = getIntent().getStringExtra("opinion");
        tvOpinion.setText(opinion);

        tvEmail = (TextView) findViewById(R.id.textView11);
        email = getIntent().getStringExtra("email");
        tvEmail.setText("Su email: "+email);

        tvCalificacion = (TextView) findViewById(R.id.textView7);
        valoracion = getIntent().getStringExtra("valoracion");
        tvCalificacion.setText(valoracion);




    }

}
