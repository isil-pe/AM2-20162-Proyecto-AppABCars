package com.isil.abcars.presenter;

import android.util.Log;

import com.isil.abcars.entity.UserEntity;
import com.isil.abcars.storage.entity.LoginRaw;
import com.isil.abcars.storage.entity.LoginResponse;
import com.isil.abcars.storage.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MarcoTejeda on 15/11/16.
 */
public class LoginPresenter {

    private static final String TAG = "LoginPresenter";
    private final String ERROR_MESSAGE= "Ocurrió un error";
    private LoginView loginView;
    private String email;
    private String password;

    public void attachedView(LoginView loginView){
        this.loginView = loginView;
    }

    public void detachView(){
        this.loginView = null;
    }

    public void login(String email,String password) {
        this.email = email;
        this.password = password;

        LoginRaw loginRaw = new LoginRaw();
        loginRaw.setLogin(this.email);
        loginRaw.setPassword(this.password);

        loginView.showLoading();

        Call<LoginResponse> call = ApiClient.getMyApiClient().login(loginRaw);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    loginSuccess(response.body());
                } else {
                    loginError(ERROR_MESSAGE);
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
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

    public void loginSuccess(LoginResponse loginResponse){
        if(loginResponse != null){
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(loginResponse.getEmail());
            userEntity.setName(loginResponse.getName());
            userEntity.setObjectId(loginResponse.getObjectId());
            userEntity.setToken(loginResponse.getToken());
        }
        loginView.hideLoading();
        loginView.gotoMain();
    }

    public void loginError(String messageError){
        loginView.hideLoading();
        loginView.onMessageError(messageError);
    }

}
