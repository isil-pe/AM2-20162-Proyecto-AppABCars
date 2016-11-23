package com.isil.abcars.presenter;

import android.util.Log;

import com.isil.abcars.entity.MarcaEntity;
import com.isil.abcars.storage.entity.MarcaResponse;
import com.isil.abcars.storage.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by FranciscoParedes on 23/11/16.
 */

public class MarcaPresenter {

    private static final String TAG = "MarcaPresenter";
    private final String ERROR_MESSAGE = "Ocurrio un error";

    private MarcaView marcaView;

    public void attachedView(MarcaView marcaView)
    {
        this.marcaView = marcaView;
    }

    public void loadMarcas(){
        marcaView.showLoading();

        Call<MarcaResponse> call = ApiClient.getMyApiClient().marcas();
        call.enqueue(new Callback<MarcaResponse>() {
            @Override
            public void onResponse(Call<MarcaResponse> call, Response<MarcaResponse> response) {
                if(response.isSuccessful()){
                    marcasSuccess(response.body());
                }else {
                    marcasError(ERROR_MESSAGE);
                }
            }

            @Override
            public void onFailure(Call<MarcaResponse> call, Throwable t) {
                String json = "Error";
                try{
                    json = new StringBuffer().append(t.getMessage()).toString();
                }catch (NullPointerException e) {}
                Log.v(TAG, "json marca>>>>> " + json);

                 marcasError(json);
            }
        });

    }

    private void marcasSuccess(MarcaResponse marcaResponse){
        marcaView.hideLoading();

        if(marcaResponse != null) {
            List<MarcaEntity> marcas = marcaResponse.getData();
            marcaView.renderMarca(marcas);
        }
    }

    private void marcasError(String messageError){
        marcaView.hideLoading();
        marcaView.onMessageError(messageError);
    }

}
