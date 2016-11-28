package com.isil.abcars.presenter;


import android.util.Log;

import com.isil.abcars.storage.entity.RegisterRaw;
import com.isil.abcars.storage.entity.RegisterResponse;
import com.isil.abcars.storage.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MarcoTejeda on 20/11/16.
 */
public class RegisterPresenter {

    private static final String TAG = "RegisterPresenter";
    private final String ERROR_MESSAGE= "Ocurrió un error";

    private RegisterView registerView;
    private String name;
    private String last_name;
    private String email;
    private String password;

    public void attachedView(RegisterView registerView){
        this.registerView = registerView;
    }

    public void detachView(){
        this.registerView = null;
    }

    public void register(String nombre,String apellido, String correo, String contrasena) {
        this.name = nombre;
        this.last_name = apellido;
        this.email = correo;
        this.password = contrasena;

        RegisterRaw registerRaw = new RegisterRaw();
        registerRaw.setName(this.name);
        registerRaw.setLast_name(this.last_name);
        registerRaw.setEmail(this.email);
        registerRaw.setPassword(this.password);

        registerView.showLoading();

        Call<RegisterResponse> call = ApiClient.getMyApiClient().addRegister(registerRaw);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()) {
                    loginSuccess();
                } else {
                    loginError(ERROR_MESSAGE);
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String json = "Error ";
                try {
                    json = new StringBuffer().append(t.getMessage()).toString();
                } catch (NullPointerException e) {

                }
                Log.v(TAG, "json >>>> " + json);
                loginError(json);
            }
        });

    }

    public void loginSuccess(){
        registerView.hideLoading();
        registerView.gotoLogin();
    }

    public void loginError(String messageError){
        registerView.hideLoading();
        registerView.onMessageError(messageError);
    }


}
