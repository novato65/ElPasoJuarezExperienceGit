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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.solinpromex.elpasojuarezexperience.app.AppController;
import com.solinpromex.elpasojuarezexperience.model.OpinionesHotel;
import com.solinpromex.elpasojuarezexperience.model.OpinionesRte;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Tab3Comer extends Fragment {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();


    private Integer id_rte;
    private String nombre_rte,id_rte_string;

    private TextView tv_id_hotel;

    private ProgressDialog pDialog;
    private List<OpinionesRte> opinionesrteList = new ArrayList<OpinionesRte>();
    private ListView listView;
    private OpinionesRteListAdapter adapter;
    private ImageButton mexbutton;

    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_3comer,container,false);

        mContext = getActivity();
        return v;
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        addListenermexButton();



        //preparacion de la lista
        listView = (ListView) getView().findViewById(R.id.list);

        //inicializacion del List Adapter
        adapter = new OpinionesRteListAdapter(getActivity(), opinionesrteList);

        listView.setAdapter(adapter);

        //listView.setOnItemClickListener(this);

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Procesando opiniones...");
        pDialog.show();
        id_rte = getActivity().getIntent().getIntExtra("id_rte", 0);

        String url = "http://solinpromex.com/epje/php/recuperar_opiniones_rte.php?id="+id_rte;

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
                                OpinionesRte opinionesrte = new OpinionesRte();
                                opinionesrte.setId_opinion_rte(obj.getInt("id_opiniones_rte"));
                                opinionesrte.setUser_name(obj.getString("user_name"));
                                opinionesrte.setFecha(obj.getString("fecha"));
                                opinionesrte.setOpinion(obj.getString("opinion"));

                                opinionesrte.setValoracion(obj.getDouble("valoracion"));
                                opinionesrte.setimagen_rte_name(obj.getString("imagen_rte"));





                                // adding movie to movies array
                                opinionesrteList.add(opinionesrte);

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


                Intent intent = new Intent(getActivity(), NuevoComentarioRte.class);

                id_rte = getActivity().getIntent().getIntExtra("id_rte", 0);
                nombre_rte = getActivity().getIntent().getStringExtra("nombre_rte");

                intent.putExtra("id_rte", id_rte);
                intent.putExtra("nombre_rte", nombre_rte);


                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);




            }

        });

    }


}
