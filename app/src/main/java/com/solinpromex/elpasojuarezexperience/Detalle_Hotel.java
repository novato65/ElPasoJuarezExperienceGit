package com.solinpromex.elpasojuarezexperience;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Detalle_Hotel extends AppCompatActivity {
    // Declaring Your View and Variables

    public String nombre_hotel_recibido, foto_hotel_recibido, descripcion_hotel_recibido,direccion_hotel_recibido,web_hotel,tel_hotel,tel_reservas,zona_hotel,facebook_hotel,twitter_hotel;
    private int hotel_num_estrellas, id_hotel_recibido;
    private double calificacion_hotel,latitud_hotel,longitud_hotel;

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Info","Mapa","Opinión"};
    int Numboftabs =3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__hotel);

        nombre_hotel_recibido = getIntent().getStringExtra("nombre_hotel");
        direccion_hotel_recibido = getIntent().getStringExtra("direccion_hotel");
        descripcion_hotel_recibido = getIntent().getStringExtra("descripcion_hotel");
        foto_hotel_recibido = getIntent().getStringExtra("foto_hotel");
        hotel_num_estrellas = getIntent().getIntExtra("num_estrellas", 0);

        id_hotel_recibido = getIntent().getIntExtra("id_hotel",0);

        setTitle(nombre_hotel_recibido);

        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

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