package com.solinpromex.elpasojuarezexperience;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Inicio extends AppCompatActivity {

    private Button mexbutton;
    private Button usabutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        addListenermexButton();
        addListenerusaButton();

        // Enable Local Datastore.

        //desde dell
//desde MAC
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "4ANQsOwBaaESGyfwLMJZpyQr95nXjVY75MNonCXd", "PDExtdiU4IvhYN5bWmsUoqmCfWF5TIlKnTjIHBAY");
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "barco del mississipi dell2 ");
        testObject.saveInBackground();
    }

    //test commit desde mac 1953 cambiado en dell ahora mac...
    public void addListenermexButton() {

        //Select a specific button to bundle it with the action you want
//test desde mac 003
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
