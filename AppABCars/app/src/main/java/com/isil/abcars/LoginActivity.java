package com.isil.abcars;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.isil.abcars.presenter.LoginPresenter;
import com.isil.abcars.presenter.LoginView;
import com.isil.abcars.storage.PreferencesHelper;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private Button btnEntrar;
    private TextView btnRegistro;
    private EditText txtCorreo, txtContrasena;
    private String correo, contrasena;
    private View containerLoading,containerLogin;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        loginPresenter = new LoginPresenter();
        loginPresenter.attachedView(this);

        txtCorreo = (EditText)findViewById(R.id.txtCorreo);
        txtContrasena = (EditText)findViewById(R.id.txtContrasena);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        btnRegistro = (TextView) findViewById(R.id.btnRegistro);
        containerLoading = findViewById(R.id.containerLoading);
        containerLogin = findViewById(R.id.containerLogin);


        //Click Boton Entrar
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    loginPresenter.login(correo, contrasena);
                }
            }
        });

        //Click Boton Registro
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.gotoRegister();
            }
        });

    }

    private boolean validateForm() {
        correo = txtCorreo.getText().toString();
        contrasena = txtContrasena.getText().toString();

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

    private void savePreferences() {
        PreferencesHelper.saveSession(this, correo, contrasena);
    }


    /*
    * Metodos de LoginView
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
        Snackbar snackbar = Snackbar.make(containerLogin, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void gotoMain() {
        savePreferences();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
