package com.solinpromex.elpasojuarezexperience;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class Tab1Comer extends Fragment {

    private ProgressDialog pDialog;
    private TextView rte_nombre, rte_direccion,rte_descripcion,rte_tipo;
    private WebView desc_text;
    private ImageView rte_foto;
    private String nombre_rte_string, foto_rte_recibida, direccion_rte,
            id_rte_string, descripcion_rte_string, web_rte_string, tel_rte_string, tel_reservar_string, facebook_rte_string,
            google_rte_string, tipo_string,ciudad_string;
    private Integer id_rte,ciudad;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1comer, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        id_rte = getActivity().getIntent().getIntExtra("id_rte", 0);
        ciudad = getActivity().getIntent().getIntExtra("ciudad_rte", 30);
      //  ciudad_string = getActivity().getIntent().getStringExtra("ciudad_rte");

        Log.d("CIUDAD***************", String.valueOf(ciudad));
        //ponemos el nombre del hotel en el textview
        rte_nombre = (TextView) getView().findViewById(R.id.nombre_rte_tv );
        id_rte_string = String.valueOf(id_rte_string);

     rte_nombre.setText(getActivity().getIntent().getStringExtra("nombre_rte"));

       // rte_nombre.setText(String.valueOf(ciudad));
        //ponemos foto del hotel en el imageview
        rte_foto = (ImageView) getView().findViewById(R.id.fotodelrte);
        foto_rte_recibida = getActivity().getIntent().getStringExtra("foto_rte");
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Procesando datos...");
        pDialog.show();
        new FetchItems().execute();

        //ponemos direccion del hotel
        rte_direccion = (TextView) getView().findViewById(R.id.direccion_rte_tv);
        rte_direccion.setText(getActivity().getIntent().getStringExtra("direccion_rte"));

        rte_tipo = (TextView) getView().findViewById(R.id.tipococinarte);
        String tipo_cocina = getActivity().getIntent().getStringExtra("tipo_rte");

        rte_tipo.setText("Tipo de cocina: "+tipo_cocina);
        //ponemos descripcion  del hotel
        rte_descripcion = (TextView) getView().findViewById(R.id.descripcion_rte_tv);

        descripcion_rte_string = getActivity().getIntent().getStringExtra("descripcion_rte");

        //hotel_descripcion.setText(((Detalle_Hotel) getActivity()).getIntent().getStringExtra("descripcion_hotel"));

        rte_descripcion.setText(Html.fromHtml(descripcion_rte_string).toString());
        rte_descripcion.setMovementMethod(new ScrollingMovementMethod());

        WebView wv = (WebView) getView().findViewById(R.id.webView);

        final String mimeType = "text/html";
        final String encoding = "UTF-8";



        wv.loadDataWithBaseURL("", descripcion_rte_string, mimeType, encoding, "");
        //boton web

        web_rte_string = getActivity().getIntent().getStringExtra("web_rte");
        ImageButton WebButton = (ImageButton) getView().findViewById(R.id.web_button); // Retrieve the button from the XML file
        WebButton.setOnClickListener(new View.OnClickListener() {  //Add a listener for when the button is pressed
            @Override
            public void onClick(View v) {
                abrir_web();
            }
        });

        //boton llamar
        tel_rte_string = getActivity().getIntent().getStringExtra("tel_rte");
        ImageButton LlamarButton = (ImageButton) getView().findViewById(R.id.botonLlamar); // Retrieve the button from the XML file
        LlamarButton.setOnClickListener(new View.OnClickListener() {  //Add a listener for when the button is pressed
            @Override
            public void onClick(View v) {
                String number = tel_rte_string;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });
        //boton reservar
        tel_reservar_string = getActivity().getIntent().getStringExtra("tel_reservas");
        ImageButton ReservarButton = (ImageButton) getView().findViewById(R.id.botonreservar); // Retrieve the button from the XML file
        ReservarButton.setOnClickListener(new View.OnClickListener() {  //Add a listener for when the button is pressed
            @Override
            public void onClick(View v) {
                String number = tel_reservar_string;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        //boton facebook

        facebook_rte_string = getActivity().getIntent().getStringExtra("facebook_rte");
        ImageButton FacebookButton = (ImageButton) getView().findViewById(R.id.botofacebook); // Retrieve the button from the XML file
        FacebookButton.setOnClickListener(new View.OnClickListener() {  //Add a listener for when the button is pressed
            @Override
            public void onClick(View v) {
                abrir_facebook();
            }
        });

        //boton google

        google_rte_string = getActivity().getIntent().getStringExtra("google_rte");
        ImageButton GoogleButton = (ImageButton) getView().findViewById(R.id.googlebutton); // Retrieve the button from the XML file
        GoogleButton.setOnClickListener(new View.OnClickListener() {  //Add a listener for when the button is pressed
            @Override
            public void onClick(View v) {
                abrir_google();
            }
        });



    }

    protected void abrir_web() {
        web_rte_string = getActivity().getIntent().getStringExtra("web_rte");
        // You could have this at the top of the class as a constant, or pass it in as a method variable, if you wish to send to multiple websites
        Intent i = new Intent(Intent.ACTION_VIEW); // Create a new intent - stating you want to 'view something'
        i.setData(Uri.parse(web_rte_string));  // Add the url data (allowing android to realise you want to open the browser)
        startActivity(i); // Go go go!
    }

    protected void abrir_facebook() {
        facebook_rte_string = getActivity().getIntent().getStringExtra("facebook_rte");
        // You could have this at the top of the class as a constant, or pass it in as a method variable, if you wish to send to multiple websites
        Intent i = new Intent(Intent.ACTION_VIEW); // Create a new intent - stating you want to 'view something'
        i.setData(Uri.parse(facebook_rte_string));  // Add the url data (allowing android to realise you want to open the browser)
        startActivity(i); // Go go go!
    }

    protected void abrir_google() {
        google_rte_string = getActivity().getIntent().getStringExtra("google_rte");
        // You could have this at the top of the class as a constant, or pass it in as a method variable, if you wish to send to multiple websites
        Intent i = new Intent(Intent.ACTION_VIEW); // Create a new intent - stating you want to 'view something'
        i.setData(Uri.parse(google_rte_string));  // Add the url data (allowing android to realise you want to open the browser)
        startActivity(i); // Go go go!
    }


    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    private class FetchItems extends AsyncTask<String, Bitmap, Bitmap> {

        protected Bitmap doInBackground(String... params) {


            //Descargamos la imagen en bitmap y la almacenamos
            Bitmap imagenBitmap = downloadImage(foto_rte_recibida);

            //Este return enviara la imagen al siguiente proceso, onPostExecute
            return imagenBitmap;
        }

        protected void onPostExecute(Bitmap imagen) {
            //Colocamos la imagen que hemos obtenido en el ImageView
            try {

                rte_foto.setImageBitmap(imagen);
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


}
