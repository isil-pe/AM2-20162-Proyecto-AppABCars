package com.isil.abcars;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.isil.abcars.entity.PostEntity;
import com.isil.abcars.presenter.PostPresenter;
import com.isil.abcars.presenter.PostView;
import com.isil.abcars.storage.PreferencesHelper;
import com.isil.abcars.view.adapters.PostAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private static final String TAG ="MainActivity";

    private TextView txtLogout,txtBienvenida;
    private ListView listPost;
    private PostAdapter postAdapter;

    private ArrayList<PostEntity> arreglo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arreglo = new ArrayList<PostEntity>();

        txtLogout = (TextView)findViewById(R.id.txtLogout);
        txtBienvenida = (TextView)findViewById(R.id.txtBienvenida);
        listPost = (ListView)(findViewById(R.id.listPost));

        //User Bienvenida
        String us = PreferencesHelper.getUserSession(this);
        if(us != null) {
            txtBienvenida.setText("Bienvenido "+ us);
        }

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        PostEntity pe1 = new PostEntity(10000.00, "descripcion 1");
        PostEntity pe2 = new PostEntity(13000.00, "descripcion 2");
        PostEntity pe3 = new PostEntity(14000.00, "descripcion 3");
        PostEntity pe4 = new PostEntity(20000.00, "descripcion 4");

        arreglo.add(pe1);
        arreglo.add(pe2);
        arreglo.add(pe3);
        arreglo.add(pe4);

        postAdapter = new PostAdapter(this,arreglo);
        listPost.setAdapter(postAdapter);


    }

    private void logout() {
        PreferencesHelper.signOut(this);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
