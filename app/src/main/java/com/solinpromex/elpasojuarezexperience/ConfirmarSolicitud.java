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

public class ConfirmarSolicitud extends AppCompatActivity {

    private String suSolicitud,suNombre,suEmail;
    private TextView tvSuSolicitud,tvSuNombre,tvSuEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_solicitud);

        suSolicitud = getIntent().getStringExtra("solicitud");
        suNombre = getIntent().getStringExtra("usuario");
        suEmail = getIntent().getStringExtra("email");

        tvSuSolicitud = (TextView) findViewById(R.id.susolicitud);
        tvSuNombre = (TextView) findViewById(R.id.sunombre);
        tvSuEmail = (TextView) findViewById(R.id.suemail);

        tvSuSolicitud.setText(suSolicitud);
        tvSuNombre.setText("Su nombre: "+suNombre);
        tvSuEmail.setText("Su email: "+suEmail);


    }

    public void cancelar(View view){
        finish();
    }

    public void enviar(View view){

        String nombre_db = suNombre;

        String email_db = suEmail;

        String solicitud_db = suSolicitud;



        insertToDatabase(nombre_db,email_db,solicitud_db);
    }

    private void insertToDatabase(String nombre_insert, String email_insert, String solicitud_insert){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {


                String paramNombre = params[0];
                String paramEmail = params [1];
                String paramSolicitud = params [2];



                String valor0 = suNombre;
                String valor1 = suEmail;
                String valor2 = suSolicitud;





                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("valor0", valor0));

                nameValuePairs.add(new BasicNameValuePair("valor1", valor1));

                nameValuePairs.add(new BasicNameValuePair("valor2", valor2));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://solinpromex.com/epje/php/insertar_solicitud.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Datos enviados..una vez confirmado su email, consideraremos su solicitud.";
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
        sendPostReqAsyncTask.execute(suNombre,suEmail,suSolicitud);
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
