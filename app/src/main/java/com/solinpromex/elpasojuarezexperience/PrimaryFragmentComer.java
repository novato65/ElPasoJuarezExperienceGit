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
import com.solinpromex.elpasojuarezexperience.util.BusProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PrimaryFragmentComer extends Fragment implements AdapterView.OnItemClickListener {



    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://solinpromex.com/epje/php/recuperar_restaurantes.php";
    private ProgressDialog pDialog;
    private List<Restaurante> restauranteList = new ArrayList<Restaurante>();
    private ListView listView;
    private CustomListAdapterRte adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.primary_layout_comer, null);

    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);



        Bundle args = getArguments();
        String hola = args.getString("myStringLabel");

        Log.d(TAG, hola);

        listView = (ListView) getView().findViewById(R.id.list);
        adapter = new CustomListAdapterRte (getActivity(), restauranteList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Procesando restaurantes...");
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
                                Restaurante restaurante = new Restaurante();
                                restaurante.setId_rte(obj.getInt("id_rte"));
                                restaurante.setNombre(obj.getString("nombre_rte"));
                                restaurante.setDescripcion(obj.getString("descripcion_rte"));
                                restaurante.setLatitud(obj.getDouble("latitud_rte"));
                                restaurante.setLongitud(obj.getDouble("longitud_rte"));
                                restaurante.setDireccion(obj.getString("direccion_rte"));
                                restaurante.setWeb(obj.getString("web_rte"));
                                restaurante.setTel_rte(obj.getString("tel_rte"));
                                restaurante.setTel_reservas(obj.getString("tel_reservas"));
                                restaurante.setFoto(obj.getString("foto_rte"));
                                restaurante.setCalificacion(obj.getDouble("calificacion_rte"));
                                restaurante.setTipo_rte(obj.getString("tipo_rte"));
                                restaurante.setFacebook(obj.getString("facebook_rte"));
                                restaurante.setTwitter(obj.getString("google_rte"));
                                restaurante.setZona(obj.getString("zona_rte"));
                                restaurante.setCiudad(obj.getInt("ciudad"));
                                restaurante.setPoi(obj.getInt("poi"));



                                // adding movie to movies array
                                restauranteList.add(restaurante);
                                pDialog = new ProgressDialog(getActivity());
                                pDialog.setMessage(obj.optString("id_rte"));
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

        Restaurante rteActual = (Restaurante) adapter.getItem(position);
        String msg = "Elegiste el restaurante " + rteActual.getNombre();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getActivity(), Detalle_Hotel.class);

        intent.putExtra("id_rte", rteActual.getId_rte());
        intent.putExtra("nombre_rte", rteActual.getNombre());
        intent.putExtra("descripcion_rte", rteActual.getDescripcion());
        intent.putExtra("latitud_rte", rteActual.getLatitud());
        intent.putExtra("longitud_rte", rteActual.getLongitud());
        intent.putExtra("direccion_rte", rteActual.getDireccion());
        intent.putExtra("web_rte", rteActual.getWeb());
        intent.putExtra("tel_rte", rteActual.getTel_rte());
        intent.putExtra("tel_reservas", rteActual.getTel_reservas());
        intent.putExtra("foto_rte", rteActual.getFoto());
        intent.putExtra("calificacion_rte", rteActual.getCalificacion());
        intent.putExtra("tipo_rte", rteActual.getTipo_rte());
        intent.putExtra("facebook_rte", rteActual.getFacebook());
        intent.putExtra("google_rte", rteActual.getTwitter());


        startActivity(intent);




    }


}
