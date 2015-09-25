package com.solinpromex.elpasojuarezexperience;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class NuevaSolicitudAparecer extends AppCompatActivity  {

    private TextView tvUsuario, tvEmail, etSolicitud;
    private String usuario, email, solicitud;
    private int hotel_id;
    private Button boton_enviar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud);


        addListenerBotonEnviar();


        //test 22
        //TEST
    }

    public void addListenerBotonEnviar() {

        //Select a specific button to bundle it with the action you want
//test desde mac 003.3
        boton_enviar = (Button) findViewById(R.id.button);




        boton_enviar.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {

                Intent intent = new Intent(NuevaSolicitudAparecer.this, ConfirmarSolicitud.class);

                tvUsuario = (TextView) findViewById(R.id.etusername);
                tvEmail = (TextView) findViewById(R.id.etemail);
                etSolicitud = (TextView) findViewById(R.id.solicitud);

                usuario = tvUsuario.getText().toString();
                email = tvEmail.getText().toString();
                solicitud = etSolicitud.getText().toString();


                intent.putExtra("usuario", usuario);
                intent.putExtra("email", email);
                intent.putExtra("solicitud", solicitud);


                int REQUEST_CODE = 123;
                startActivityForResult(intent, REQUEST_CODE);


            }

        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }

}
