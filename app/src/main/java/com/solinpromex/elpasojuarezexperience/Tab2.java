package com.solinpromex.elpasojuarezexperience;

/**
 * Created by modestovascofornas on 9/16/15.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;



import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.solinpromex.elpasojuarezexperience.app.AppController;
import com.solinpromex.elpasojuarezexperience.model.Hotel;
import com.solinpromex.elpasojuarezexperience.model.POI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.*;

public class Tab2 extends Fragment implements OnMapReadyCallback {

    private View view;
    private Context mContext;

    private double latitud_del_hotel, longitud_del_hotel;
    private String nombre_del_hotel,direccion_del_hotel;

    private static final String url = "http://solinpromex.com/epje/php/recuperar_marcadores.php";
    private ProgressDialog pDialog;
    private List<POI> POIList = new ArrayList<POI>();

    private Button mostrarButton;
    private static final String TAG = MainActivity.class.getSimpleName();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_2, container, false);



        mContext = getActivity();

        nombre_del_hotel = getActivity().getIntent().getStringExtra("nombre_hotel");
        direccion_del_hotel = getActivity().getIntent().getStringExtra("direccion_hotel");
        latitud_del_hotel = getActivity().getIntent().getDoubleExtra("latitud_hotel", 0);
        longitud_del_hotel = getActivity().getIntent().getDoubleExtra("longitud_hotel",0);


        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SupportMapFragment fragment = new SupportMapFragment();
        transaction.add(R.id.mapView, fragment);
        transaction.commit();

        fragment.getMapAsync(this);





        return view;
    }
    @Override

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);



    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


    @Override
    public void onMapReady(final GoogleMap map) {


        //map is ready
        // latitude and longitude
        double latitude = latitud_del_hotel;
        double longitude =longitud_del_hotel;

        // create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(nombre_del_hotel).snippet(direccion_del_hotel).icon(BitmapDescriptorFactory.fromResource(R.drawable.poi));

        // Changing marker icon
        // marker.icon(BitmapDescriptorFactory
        // .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        map.addMarker(marker);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitud_del_hotel, longitud_del_hotel)).zoom(15).build();
        map.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));



        mostrarButton = (Button) getActivity().findViewById(R.id.boton_mostrar);



        mostrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                // Creating volley request obj
                JsonArrayRequest movieReq = new JsonArrayRequest(url,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                Log.d(TAG, response.toString());

                                for (int i = 0; i < response.length(); i++)
                                    try {


                                        JSONObject obj = (JSONObject) response.getJSONObject(i);
                                        POI poi = new POI();

                                        poi.setNombre_POI(obj.getString("nombre_hotel"));
                                        poi.setLatitud_POI(obj.getString("latitud_hotel"));
                                        poi.setLongitud_POI(obj.getString("longitud_hotel"));
                                        poi.setDireccion_POI(obj.getString("direccion_hotel"));
                                        poi.setDireccion_POI(obj.getString("tipo_poi"));

                                        String nombre = obj.optString("nombre_hotel");
                                        Log.d("NOMBRE=", nombre);

                                        String latitud = obj.optString("latitud_hotel");
                                        double latitud_del_hotel = Double.parseDouble(latitud);

                                        String longitud = obj.optString("longitud_hotel");
                                        double longitud_del_hotel = Double.parseDouble(longitud);

                                        String tipo_poi = obj.optString("tipo_poi");
                                        Log.d("NOMBRE=", nombre);
                                        String direccion_poi = obj.optString("direccion_hotel");
                                        Log.d("NOMBRE=", nombre);

                                        // create marker
                                        //cambiar marker icon dependiendo del tipo de poi

                                       switch (tipo_poi) {

                                           case "1": //hotel

                                           MarkerOptions markerH = new MarkerOptions().position(new LatLng(latitud_del_hotel, longitud_del_hotel)).snippet(direccion_poi).title(nombre).icon(BitmapDescriptorFactory.fromResource(R.drawable.hotel_0star));
                                               map.addMarker(markerH);
                                               break;

                                           case "2": //restaurante

                                           MarkerOptions markerR = new MarkerOptions().position(new LatLng(latitud_del_hotel, longitud_del_hotel)).snippet(direccion_poi).title(nombre).icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant));
                                               map.addMarker(markerR);
                                               break;


                                       }




                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());


                    }
                });



                AppController.getInstance().addToRequestQueue(movieReq);

            }
        });



    }
}