package com.solinpromex.elpasojuarezexperience;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class NuevoComentarioRte extends AppCompatActivity  {

    private TextView rte_calificado,valor_calificacion,user_name_texto,opinion_texto, user_email_texto;
    private String user_email, id_del_rte, nombre_del_rte,user_name,opinion_del_usuario;
    private int rte_id;
    private Button boton_enviar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_comentario_rte);

        SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar);

        rte_calificado = (TextView) findViewById(R.id.hotel_calificado);

        valor_calificacion = (TextView) findViewById(R.id.valor_calificacion);

        user_name_texto = (TextView) findViewById(R.id.etusername);
        opinion_texto = (TextView) findViewById(R.id.editText);
        valor_calificacion = (TextView) findViewById(R.id.valor_calificacion);
        user_email_texto = (TextView) findViewById(R.id.etemail);



        rte_id = getIntent().getIntExtra("id_rte", 0);
        id_del_rte = String.valueOf(rte_id);

        nombre_del_rte = getIntent().getStringExtra("nombre_rte");

        rte_calificado.setText(nombre_del_rte);
       // hotel_calificado.setText(id_del_hotel);


        seekbar.setProgress(5);

        addListenerBotonEnviar();

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

    public void addListenerBotonEnviar() {

        //Select a specific button to bundle it with the action you want
//test desde mac 003.3
        boton_enviar = (Button) findViewById(R.id.button);


        boton_enviar.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {

                Intent intent = new Intent(NuevoComentarioRte.this, ConfirmarComentarioRte.class);

                user_name = user_name_texto.getText().toString();
                opinion_del_usuario = opinion_texto.getText().toString();
                user_email = user_email_texto.getText().toString();

                intent.putExtra("id_rte", id_del_rte);
                intent.putExtra("user_name", user_name);
                intent.putExtra("opinion", opinion_del_usuario);
                intent.putExtra("valoracion", valor_calificacion.getText());
                intent.putExtra("email", user_email);

                intent.putExtra("nombre_rte", rte_calificado.getText());

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
