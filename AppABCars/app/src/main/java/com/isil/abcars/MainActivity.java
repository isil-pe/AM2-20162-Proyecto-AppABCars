package com.isil.abcars;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.isil.abcars.storage.PreferencesHelper;
import com.isil.abcars.view.adapters.ListPostsAdapter;

import com.isil.abcars.view.fragments.ListMarcaFragment;
import com.isil.abcars.view.fragments.PerfilFragment;

import com.isil.abcars.view.fragments.ListPoststFragment;
import com.isil.abcars.view.listeners.OnNavigationListener;



public class MainActivity extends AppCompatActivity implements OnNavigationListener {

    private static final String TAG ="MainActivity";

    private ListPostsAdapter postAdapter;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private TextView txtUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.navigationToolbar);
        setSupportActionBar(toolbar);


        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {

                    case R.id.menuPerfil:
                        changeFragment(0);
                        return true;
                    case R.id.menuMarcas:
                        changeFragment(1);
                        return true;
                    case R.id.menuLisatdo:
                        changeFragment(2);
                        return true;
                    case R.id.menuSesion:
                        logout();
                        return true;
                    default:
                        return true;

                }
            }

        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                /* Code here will be triggered once the drawer closes as we dont want anything
                to happen so we leave this blank */
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                /* Code here will be triggered once the drawer open as we dont want anything to
                happen so we leave this blank */
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        //Setting the userEmail on herderLayout
        View headerView = navigationView.getHeaderView(0);
        txtUsuario = (TextView) headerView.findViewById(R.id.txtUsuario);
        String us = PreferencesHelper.getUserSession(this);
        if(us != null) {
            txtUsuario.setText("Bienvenido "+ us);
        }

        // Mostranos el fragmento de listPosts
        changeFragment(2);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Metodos privados
     ___________________________________________________________________________________________ */

    private void logout() {
        PreferencesHelper.signOut(this);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void changeFragment(int i) {
        Fragment fragment=null;
        switch (i){
            case 0:
                String username = PreferencesHelper.getUserSession(this);
                String password = PreferencesHelper.getUserPasswordSession(this);
                Bundle bundle= new Bundle();
                bundle.putSerializable("USERNAME",username);
                bundle.putSerializable("PASSWORD",password);
                fragment = new PerfilFragment();
                fragment.setArguments(bundle);
                break;

            case 1:
                fragment = new ListMarcaFragment();
                break;

            case 2:
                fragment = new ListPoststFragment();
                break;

        }
        if(fragment!=null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameMain,fragment);
            fragmentTransaction.commit();
        }
    }

}
