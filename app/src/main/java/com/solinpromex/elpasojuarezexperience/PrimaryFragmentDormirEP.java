package com.solinpromex.elpasojuarezexperience;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.solinpromex.elpasojuarezexperience.app.AppController;
import com.solinpromex.elpasojuarezexperience.model.Hotel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PrimaryFragmentDormirEP extends Fragment implements AdapterView.OnItemClickListener {



    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://solinpromex.com/epje/php/recuperar_hoteles.php";
    private ProgressDialog pDialog;
    private List<Hotel> hotelList = new ArrayList<Hotel>();
    private ListView listView;
    private CustomListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.primary_layout_dormir, null);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);


        listView = (ListView) getView().findViewById(R.id.list);
        adapter = new CustomListAdapter(getActivity(), hotelList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Procesando hoteles...");
        pDialog.show();


        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Hotel hotel = new Hotel();
                                hotel.setId_hotel(obj.getInt("id_hotel"));
                                hotel.setNombre(obj.getString("nombre_hotel"));
                                hotel.setDescripcion(obj.getString("descripcion_hotel"));
                                hotel.setLatitud(obj.getDouble("latitud_hotel"));
                                hotel.setLongitud(obj.getDouble("longitud_hotel"));
                                hotel.setDireccion(obj.getString("direccion_hotel"));
                                hotel.setWeb(obj.getString("web_hotel"));
                                hotel.setTel_hotel(obj.getString("tel_hotel"));
                                hotel.setTel_reservas(obj.getString("tel_reservas"));
                                hotel.setFoto(obj.getString("foto_hotel"));
                                hotel.setCalificacion(obj.getDouble("calificacion_hotel"));
                                hotel.setNum_estrellas(obj.getInt("num_estrellas"));
                                hotel.setZona_hotel(obj.getString("zona_hotel"));
                                hotel.setFacebook(obj.getString("facebook_hotel"));
                                hotel.setTwitter(obj.getString("twitter_hotel"));


                                // adding movie to movies array
                                hotelList.add(hotel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);


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


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Hotel hotelActual = (Hotel) adapter.getItem(position);
        String msg = "Elegiste el hotel " + hotelActual.getNombre();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getActivity(), Detalle_Hotel.class);

        intent.putExtra("id_hotel", hotelActual.getId_hotel());
        intent.putExtra("nombre_hotel", hotelActual.getNombre());
        intent.putExtra("descripcion_hotel", hotelActual.getDescripcion());
        intent.putExtra("latitud_hotel", hotelActual.getLatitud());
        intent.putExtra("longitud_hotel", hotelActual.getLongitud());
        intent.putExtra("direccion_hotel", hotelActual.getDireccion());
        intent.putExtra("web_hotel", hotelActual.getWeb());
        intent.putExtra("tel_hotel", hotelActual.getTel_hotel());
        intent.putExtra("tel_reservas", hotelActual.getTel_reservas());
        intent.putExtra("foto_hotel", hotelActual.getFoto());
        intent.putExtra("calificacion_hotel", hotelActual.getCalificacion());
        intent.putExtra("num_estrellas", hotelActual.getNum_estrellas());
        intent.putExtra("zona_hotel", hotelActual.getZona_hotel());
        intent.putExtra("facebook_hotel", hotelActual.getFacebook());
        intent.putExtra("twitter_hotel", hotelActual.getTwitter());


        startActivity(intent);




    }


}
