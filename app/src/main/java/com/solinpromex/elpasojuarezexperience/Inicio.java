package com.solinpromex.elpasojuarezexperience;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    private Button mexbutton;
    private Button usabutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        addListenermexButton();
        addListenerusaButton();
    }

    //test commit desde mac 1953...
    public void addListenermexButton() {

        //Select a specific button to bundle it with the action you want
//test desde Dell
        mexbutton = (Button) findViewById(R.id.mexButton);


        mexbutton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {

                startActivity(new Intent(Inicio.this, MainActivity.class));


            }

        });

    }

    public void addListenerusaButton() {

        //Select a specific button to bundle it with the action you want

        mexbutton = (Button) findViewById(R.id.usaButton);


        mexbutton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {

                Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.javacodegeeks.com"));

                startActivity(openBrowser);

            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
