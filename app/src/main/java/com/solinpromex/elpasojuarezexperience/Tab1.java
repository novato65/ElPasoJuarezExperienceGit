package com.solinpromex.elpasojuarezexperience;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;


public class Tab1 extends Fragment {

   private  TextView hotel_nombre;
    private String nombre_hotel;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_1,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

    hotel_nombre = (TextView) getView().findViewById(R.id.nombre_hotel);

        hotel_nombre.setText( ((Detalle_Hotel)getActivity()).getIntent().getStringExtra("nombre_hotel") );
    }
}
