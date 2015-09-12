package com.solinpromex.elpasojuarezexperience;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.solinpromex.elpasojuarezexperience.CustomListAdapter;
import com.solinpromex.elpasojuarezexperience.app.AppController;
import com.solinpromex.elpasojuarezexperience.model.Hotel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import java.util.ArrayList;
import java.util.List;


public class PrimaryFragmentDormir extends Fragment {
    private Button mexbutton;


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
        return inflater.inflate(R.layout.primary_layout_dormir,null);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        addListenermexButton();
        listView = (ListView) getView().findViewById(R.id.list);
        adapter = new CustomListAdapter(getActivity(), hotelList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Procesando datos...");
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
                                hotel.setNombre(obj.getString("nombre_hotel"));
                                hotel.setZona_hotel(obj.getString("zona_hotel"));
                                hotel.setFoto(obj.getString("foto_hotel"));
                                hotel.setNum_estrellas(obj.getInt("num_estrellas"));
                                hotel.setCalificacion(obj.getInt("calificacion_hotel"));



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

    public void addListenermexButton() {

        //Select a specific button to bundle it with the action you want
//test desde mac 003.3
        mexbutton = (Button) getView().findViewById(R.id.mexButton);


        mexbutton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {

                startActivity(new Intent(getActivity() , Detalle_Hotel.class));


            }

        });

    }
}
