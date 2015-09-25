package com.solinpromex.elpasojuarezexperience;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class SentFragment extends Fragment {

    private Button solicitarButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sent_layout,null);


    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        addListenermexButton();
    }

    public void addListenermexButton() {

        //Select a specific button to bundle it with the action you want
//test desde mac 003.3
        solicitarButton = (Button) getActivity().findViewById(R.id.solicitud);


        solicitarButton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {


                Intent intent = new Intent(getActivity(), NuevaSolicitudAparecer.class);



                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);


            }

        });

    }
}
