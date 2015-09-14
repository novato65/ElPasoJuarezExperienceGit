package com.solinpromex.elpasojuarezexperience;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Tab1 extends Fragment {

    private ProgressDialog pDialog;
    private TextView hotel_nombre, hotel_direccion,hotel_descripcion;
    private WebView desc_text;
    private ImageView hotel_foto, estrella1,estrella2,estrella3,estrella4,estrella5;
    private String nombre_hotel, foto_hotel_recibida,direccion_hotel,descripcion_hotel;
    private Integer numero_estrellas;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);


        //ponemos el nombre del hotel en el textview
        hotel_nombre = (TextView) getView().findViewById(R.id.nombre_hotel);
        hotel_nombre.setText(((Detalle_Hotel) getActivity()).getIntent().getStringExtra("nombre_hotel"));

        //ponemos foto del hotel en el imageview
        hotel_foto = (ImageView) getView().findViewById(R.id.fotodelhotel);
        foto_hotel_recibida = ((Detalle_Hotel) getActivity()).getIntent().getStringExtra("foto_hotel");
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Procesando datos...");
        pDialog.show();
        new FetchItems().execute();

        //ponemos direccion del hotel
        hotel_direccion = (TextView) getView().findViewById(R.id.tvdireccion_hotel);
        hotel_direccion.setText(((Detalle_Hotel) getActivity()).getIntent().getStringExtra("direccion_hotel"));

        //ponemos descripcion  del hotel
        hotel_descripcion = (TextView) getView().findViewById(R.id.tvdescripcion_hotel);

        descripcion_hotel = ((Detalle_Hotel) getActivity()).getIntent().getStringExtra("descripcion_hotel");

        //hotel_descripcion.setText(((Detalle_Hotel) getActivity()).getIntent().getStringExtra("descripcion_hotel"));

        hotel_descripcion.setText(Html.fromHtml(descripcion_hotel).toString());
        hotel_descripcion.setMovementMethod(new ScrollingMovementMethod());

        WebView wv = (WebView) getView().findViewById(R.id.webView);

        final String mimeType = "text/html";
        final String encoding = "UTF-8";



        wv.loadDataWithBaseURL("", descripcion_hotel, mimeType, encoding, "");

        //pintamos estrellas

        estrella1 = (ImageView) getView().findViewById(R.id.estrella1);
        estrella2 = (ImageView) getView().findViewById(R.id.estrella2);
        estrella3 = (ImageView) getView().findViewById(R.id.estrella3);
        estrella4 = (ImageView) getView().findViewById(R.id.estrella4);
        estrella5 = (ImageView) getView().findViewById(R.id.estrella5);

        //
        numero_estrellas = ((Detalle_Hotel) getActivity()).getIntent().getIntExtra("num_estrellas",0);

        if (numero_estrellas ==1){
            estrella1.setVisibility(View.VISIBLE);
            estrella2.setVisibility(View.GONE);
            estrella3.setVisibility(View.GONE);
            estrella4.setVisibility(View.GONE);
            estrella5.setVisibility(View.GONE);

        }
        if (numero_estrellas ==2){
            estrella1.setVisibility(View.VISIBLE);
            estrella2.setVisibility(View.VISIBLE);
            estrella3.setVisibility(View.GONE);
            estrella4.setVisibility(View.GONE);
            estrella5.setVisibility(View.GONE);

        }
        if (numero_estrellas ==3){
            estrella1.setVisibility(View.VISIBLE);
            estrella2.setVisibility(View.VISIBLE);
            estrella3.setVisibility(View.VISIBLE);
            estrella4.setVisibility(View.GONE);
            estrella5.setVisibility(View.GONE);

        }
        if (numero_estrellas ==4){
            estrella1.setVisibility(View.VISIBLE);
            estrella2.setVisibility(View.VISIBLE);
            estrella3.setVisibility(View.VISIBLE);
            estrella4.setVisibility(View.VISIBLE);
            estrella5.setVisibility(View.GONE);

        }
        if (numero_estrellas ==5){
            estrella1.setVisibility(View.VISIBLE);
            estrella2.setVisibility(View.VISIBLE);
            estrella3.setVisibility(View.VISIBLE);
            estrella4.setVisibility(View.VISIBLE);
            estrella5.setVisibility(View.VISIBLE);

        }


    }


    private class FetchItems extends AsyncTask<String, Bitmap, Bitmap> {

        protected Bitmap doInBackground(String... params) {


            //Descargamos la imagen en bitmap y la almacenamos
            Bitmap imagenBitmap = downloadImage(foto_hotel_recibida);

            //Este return enviara la imagen al siguiente proceso, onPostExecute
            return imagenBitmap;
        }

        protected void onPostExecute(Bitmap imagen) {
            //Colocamos la imagen que hemos obtenido en el ImageView
            try {

                hotel_foto.setImageBitmap(imagen);
                hidePDialog();
            } catch (Exception e) {

            }
        }

        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                //stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        private InputStream getHttpConnection(String urlString) throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


}
