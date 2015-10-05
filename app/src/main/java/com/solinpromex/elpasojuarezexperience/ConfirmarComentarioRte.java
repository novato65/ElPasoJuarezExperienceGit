package com.solinpromex.elpasojuarezexperience;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfirmarComentarioRte extends AppCompatActivity {

    private String user_name, id_rte,opinion,valoracion,email,nombre_rte;
    private TextView tvNombreHotel,tvOpinion,tvCalificacion,tvUsername,tvEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_comentario_rte);


        id_rte = getIntent().getStringExtra("id_rte");

        tvNombreHotel = (TextView) findViewById(R.id.textView9);
        nombre_rte = getIntent().getStringExtra("nombre_rte");
        tvNombreHotel.setText(nombre_rte);

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

        //db   user_name = user_name
        //      hotel_id = id_hotel
        //      opinion = opinion
        //      valoracion = valoracion
        //      user_email = email
    }

    public void cancelar(View view){
        finish();
    }
    public void enviar(View view){
        String user_name_db = user_name;

        String hotel_id_db = id_rte;

        String  opinion_db = opinion;

        String valoracion_db = valoracion;

        String user_email_db = email;

        insertToDatabase(user_name_db,hotel_id_db, opinion_db, valoracion_db, user_email_db);
    }

    private void insertToDatabase(String hotel_id_insert, String opinion_insert, String valoracion_insert, String user_email_insert,
                                  String user_name_insert){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {


                String paramHotel_id = params[0];
                String paramOpinion = params [1];
                String paramValoracion = params [2];
                String paramUser_email = params [3];
                String paramUser_name = params [4];


                String valor0 = id_rte;
                String valor1 = opinion;
                String valor2 = valoracion;
                String valor3 = email;
                String valor4 = user_name;
                String valor5 = nombre_rte;




                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("valor0", valor0));

                nameValuePairs.add(new BasicNameValuePair("valor1", valor1));

                nameValuePairs.add(new BasicNameValuePair("valor2", valor2));

                nameValuePairs.add(new BasicNameValuePair("valor3", valor3));

                nameValuePairs.add(new BasicNameValuePair("valor4", valor4));
                nameValuePairs.add(new BasicNameValuePair("valor5", valor5));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://solinpromex.com/epje/php/insertar_opinion_rte.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Datos enviados..una vez confirmado su email, publicaremos su opinión y valoración.";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                setResultOkSoSecondActivityWontBeShown();
                finish();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_rte, opinion, valoracion, email, user_name);
    }
    private void setResultOkSoSecondActivityWontBeShown() {
        Intent intent = new Intent();
        if (getParent() == null) {
            setResult(Activity.RESULT_OK, intent);
        } else {
            getParent().setResult(Activity.RESULT_OK, intent);
        }
    }
}
