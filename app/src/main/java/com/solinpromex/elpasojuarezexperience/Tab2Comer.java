package com.solinpromex.elpasojuarezexperience;

/**
 * Created by modestovascofornas on 9/16/15.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.solinpromex.elpasojuarezexperience.app.AppController;
import com.solinpromex.elpasojuarezexperience.model.POI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.OnClickListener;

public class Tab2Comer extends Fragment implements OnMapReadyCallback {

    private View view;
    private Context mContext;

    private double latitud_del_rte, longitud_del_rte;
    private String nombre_del_rte,direccion_del_rte;

    private static final String url = "http://solinpromex.com/epje/php/recuperar_marcadores.php";
    private ProgressDialog pDialog;
    private List<POI> POIList = new ArrayList<POI>();

    private Button mostrarButton;
    private static final String TAG = MainActivity.class.getSimpleName();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_2comer, container, false);



        mContext = getActivity();

        nombre_del_rte = getActivity().getIntent().getStringExtra("nombre_rte");
        direccion_del_rte = getActivity().getIntent().getStringExtra("direccion_rte");
        latitud_del_rte = getActivity().getIntent().getDoubleExtra("latitud_rte", 0);
        longitud_del_rte = getActivity().getIntent().getDoubleExtra("longitud_rte",0);


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
//FIN DONDE DORMIR ESP

    @Override
    public void onMapReady(final GoogleMap map) {


        //map is ready
        // latitude and longitude
        final double latitude = latitud_del_rte;
        final double longitude =longitud_del_rte;

        // create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(nombre_del_rte).snippet(direccion_del_rte).icon(BitmapDescriptorFactory.fromResource(R.drawable.poi));

        // Changing marker icon
        // marker.icon(BitmapDescriptorFactory
        // .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        map.addMarker(marker);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitud_del_rte, longitud_del_rte)).zoom(15).build();
        map.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));



        mostrarButton = (Button) getActivity().findViewById(R.id.boton_mostrar);



        mostrarButton.setOnClickListener(new OnClickListener() {
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


                                        JSONObject obj = response.getJSONObject(i);
                                        POI poi = new POI();

                                        poi.setNombre_POI(obj.getString("nombre_poi"));
                                        poi.setLatitud_POI(obj.getString("latitud_poi"));
                                        poi.setLongitud_POI(obj.getString("longitud_poi"));
                                        poi.setDireccion_POI(obj.getString("direccion_poi"));
                                        poi.setDireccion_POI(obj.getString("tipo_poi"));

                                        String nombre_poi = obj.optString("nombre_poi");

                                        String latitud_poi = obj.optString("latitud_poi");
                                        double latitud_del_poi = Double.parseDouble(latitud_poi);

                                        String longitud_poi = obj.optString("longitud_poi");
                                        double longitud_del_poi = Double.parseDouble(longitud_poi);

                                        String tipo_poi = obj.optString("tipo_poi");

                                        String direccion_poi = obj.optString("direccion_poi");

                                        // create marker
                                        //cambiar marker icon dependiendo del tipo de poi

                                        //calculo de distancias

                                        double distance;

                                        Location locationA = new Location("Current");

                                        locationA.setLatitude(latitude);//sitio en cuestion

                                        locationA.setLongitude(longitude);//sitio en cuestion

                                        Location locationB = new Location("POI");

                                        locationB.setLatitude(latitud_del_poi);

                                        locationB.setLongitude(longitud_del_poi);

                                        distance = locationA.distanceTo(locationB);
                                        Log.d("ADebugTag", "DISTANCIA: " + Double.toString(distance));

                                        //limitar a marcadores en un radio de 1km

                                        if (distance < 1000) {

                                            switch (tipo_poi) {

                                                case "1": //hotel

                                                    MarkerOptions markerH = new MarkerOptions().position(new LatLng(latitud_del_poi, longitud_del_poi)).snippet(direccion_poi).title(nombre_poi).icon(BitmapDescriptorFactory.fromResource(R.drawable.hotel_0star));
                                                    map.addMarker(markerH);
                                                    break;

                                                case "2": //restaurante

                                                    MarkerOptions markerR = new MarkerOptions().position(new LatLng(latitud_del_poi, longitud_del_poi)).snippet(direccion_poi).title(nombre_poi).icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant));
                                                    map.addMarker(markerR);
                                                    break;


                                            }
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