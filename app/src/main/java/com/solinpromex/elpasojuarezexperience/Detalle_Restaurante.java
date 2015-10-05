package com.solinpromex.elpasojuarezexperience;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Detalle_Restaurante extends AppCompatActivity {
    // Declaring Your View and Variables

    public String nombre_rte_recibido, foto_rte_recibido, descripcion_rte_recibido,direccion_rte_recibido,web_rte,tel_rte,tel_reservas,zona_rte,facebook_rte,google_rte,
    tipo_rte;
    private int  id_rte_recibido,ciudad_rte_recibido,poi_rte_recibido;
    private double calificacion_rte,latitud_rte,longitud_rte;

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapterComer adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Info","Mapa","Opinión"};
    int Numboftabs =3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__rte);

        nombre_rte_recibido = getIntent().getStringExtra("nombre_rte");
        direccion_rte_recibido = getIntent().getStringExtra("direccion_rte");
        descripcion_rte_recibido = getIntent().getStringExtra("descripcion_rte");
        foto_rte_recibido = getIntent().getStringExtra("foto_hotel");

        id_rte_recibido = getIntent().getIntExtra("id_rte",0);

        setTitle(nombre_rte_recibido);

        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapterComer(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.rojomodesto);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);



    }



}