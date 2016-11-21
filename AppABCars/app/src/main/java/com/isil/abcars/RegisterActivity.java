package com.isil.abcars;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.isil.abcars.presenter.RegisterPresenter;
import com.isil.abcars.presenter.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView{

    private EditText txtNombre, txtApellido, txtCorreo, txtContrasena;
    private Button btnRegistrar;
    private View containerLoading,containerRegister;
    private RegisterPresenter registerPresenter;
    private String nombre, apellido, correo, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        registerPresenter = new RegisterPresenter();
        registerPresenter.attachedView(this);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtContrasena = (EditText) findViewById(R.id.txtContrasena);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        containerLoading = findViewById(R.id.containerLoading);
        containerRegister = findViewById(R.id.containerRegister);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()){
                    registerPresenter.register(nombre, apellido, correo, contrasena);
                }
            }
        });

    }

    private boolean validateForm() {
        nombre = txtNombre.getText().toString();
        apellido = txtApellido.getText().toString();
        correo = txtCorreo.getText().toString();
        contrasena = txtContrasena.getText().toString();

        if(nombre.isEmpty()) {
            txtContrasena.setError("El campo nombre está vacio.");
            return false;
        }
        if(apellido.isEmpty()) {
            txtContrasena.setError("El campo apellido está vacio.");
            return false;
        }
        if(correo.isEmpty()) {
            txtCorreo.setError("El campo correo está vacio.");
            return false;
        }
        if(contrasena.isEmpty()) {
            txtContrasena.setError("El campo contraseña está vacio.");
            return false;
        }
        return true;
    }

    /*
    * Metodos de RegisterView
    * -------------------------------------------------------------------------------------- */

    @Override
    public void showLoading() {
        this.containerLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.containerLoading.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onMessageError(String message) {
        Snackbar snackbar = Snackbar.make(containerRegister, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    @Override
    public void gotoLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
