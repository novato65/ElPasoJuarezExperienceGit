package com.solinpromex.elpasojuarezexperience;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Tab3 extends Fragment {

    private Integer id_hotel;
    private TextView label_id_hotel;
    private Integer hotel_id;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_3,container,false);


        return v;
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        label_id_hotel = (TextView) getActivity().findViewById(R.id.hotel);

        hotel_id = getActivity().getIntent().getIntExtra("id_hotel", 0);
        label_id_hotel.setText(String.valueOf(hotel_id));


    }
}