package com.solinpromex.elpasojuarezexperience;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.solinpromex.elpasojuarezexperience.app.AppController;
import com.solinpromex.elpasojuarezexperience.model.Hotel;
import com.solinpromex.elpasojuarezexperience.model.OpinionesHotel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Tab3 extends Fragment {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    private Integer id_hotel;

    private Integer hotel_id;
    private String nombre_hotel;


    private ProgressDialog pDialog;
    private List<OpinionesHotel> opinioneshotelList = new ArrayList<OpinionesHotel>();
    private ListView listView;
    private OpinionesHotelListAdapter adapter;
    private ImageButton mexbutton;

    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_3,container,false);

        mContext = getActivity();
        return v;
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        addListenermexButton();

        //id del hotel

        hotel_id = getActivity().getIntent().getIntExtra("id_hotel", 0);

        //preparacion de la lista
        listView = (ListView) getView().findViewById(R.id.list);

        //inicializacion del List Adapter
        adapter = new OpinionesHotelListAdapter(getActivity(), opinioneshotelList);

        listView.setAdapter(adapter);

        //listView.setOnItemClickListener(this);

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Procesando opiniones...");
        pDialog.show();


        String url = "http://solinpromex.com/epje/php/recuperar_opiniones_hotel.php?id="+hotel_id;

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
                                OpinionesHotel opinioneshotel = new OpinionesHotel();
                                opinioneshotel.setId_opinion_hotel(obj.getInt("id_opiniones_hotel"));
                                opinioneshotel.setUser_name(obj.getString("user_name"));
                                opinioneshotel.setFecha(obj.getString("fecha"));
                                opinioneshotel.setOpinion(obj.getString("opinion"));
                                opinioneshotel.setUser_id(obj.getInt("user_id"));
                                opinioneshotel.setValoracion(obj.getDouble("valoracion"));
                                opinioneshotel.setimagen_hotel_name(obj.getString("imagen_hotel"));





                                // adding movie to movies array
                                opinioneshotelList.add(opinioneshotel);

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
        mexbutton = (ImageButton) getActivity().findViewById(R.id.imageButton);


        mexbutton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {


                Intent intent = new Intent(getActivity(), NuevoComentarioHotel.class);

                hotel_id = getActivity().getIntent().getIntExtra("id_hotel", 0);
                nombre_hotel = getActivity().getIntent().getStringExtra("nombre_hotel");

                intent.putExtra("id_hotel", id_hotel);
                intent.putExtra("nombre_hotel", nombre_hotel);



                startActivity(intent);




            }

        });

    }


}
