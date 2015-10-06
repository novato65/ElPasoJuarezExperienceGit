package com.solinpromex.elpasojuarezexperience;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements PrimaryFragmentComerTiposRestaurante.OnFragmentInteractionListener,SocialFragmentComerTiposRestaurante.OnFragmentInteractionListener {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        /**
         *Setup the DrawerLayout and NavigationView
         */

             mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
             mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

             mFragmentManager = getSupportFragmentManager();
             mFragmentTransaction = mFragmentManager.beginTransaction();
             mFragmentTransaction.replace(R.id.containerView,new SentFragment()).commit();






             mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();


//opcion para abrir actividad, no fragment
//                 if (menuItem.getItemId() == R.id.nav_item_sent) {
 //                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                     fragmentTransaction.replace(R.id.containerView,new SentFragment()).commit();

//                 }


                 if (menuItem.getItemId() == R.id.nav_item_dormir) {
                     FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                     xfragmentTransaction.replace(R.id.containerView,new TabFragmentDormir()).commit();
                 }

                 if (menuItem.getItemId() == R.id.nav_item_comer) {
                     FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                     xfragmentTransaction.replace(R.id.containerView,new TabFragmentComerTiposRestaurante()).commit();
                 }
                 if (menuItem.getItemId() == R.id.nav_item_comprar) {
                     FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                     xfragmentTransaction.replace(R.id.containerView,new TabFragmentComprar()).commit();
                 }

                 return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

                android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
                ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

                mDrawerLayout.setDrawerListener(mDrawerToggle);

                mDrawerToggle.syncState();

    }

    @Override
    public void onFragmentInteraction(Fragment fragment) {
        //here you have that fragment and can change it with transactions
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.containerView, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}