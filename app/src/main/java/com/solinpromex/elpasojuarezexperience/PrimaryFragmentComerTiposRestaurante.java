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
import com.solinpromex.elpasojuarezexperience.model.Restaurante;
import com.solinpromex.elpasojuarezexperience.model.TipoRestaurante;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PrimaryFragmentComerTiposRestaurante extends Fragment implements AdapterView.OnItemClickListener {



    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://solinpromex.com/epje/php/recuperar_tipos_rte.php";
    private ProgressDialog pDialog;
    private List<TipoRestaurante> tipoRestauranteList = new ArrayList<TipoRestaurante>();
    private ListView listView;
    private CustomListAdapterTipoRte adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.primary_layout_tiporte, null);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);


        listView = (ListView) getView().findViewById(R.id.list);
        adapter = new CustomListAdapterTipoRte (getActivity(), tipoRestauranteList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Procesando tipos..");
        pDialog.show();


        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();
    Log.d("estoy aqui","estoy");
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                TipoRestaurante restaurante = new TipoRestaurante();
                                restaurante.setId_tipo(obj.getInt("id_tipo"));
                                restaurante.setNombre_tipo(obj.getString("nombre_tipo"));

                                restaurante.setFoto_tipo(obj.getString("foto_tipo"));

                                Log.d(TAG, response.toString());

                                // adding movie to movies array
                                tipoRestauranteList.add(restaurante);
                                pDialog = new ProgressDialog(getActivity());
                                pDialog.setMessage(obj.optString("id_tipo"));
                               // pDialog.show();

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

        TipoRestaurante rteActual = (TipoRestaurante) adapter.getItem(position);
        String msg = "Has elegido el tipo " + rteActual.getNombre_tipo();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getActivity(), MainActivity.class);


        intent.putExtra("nombre_tipo", rteActual.getNombre_tipo());




        startActivity(intent);




    }


}
