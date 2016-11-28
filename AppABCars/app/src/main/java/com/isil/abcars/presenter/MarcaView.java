package com.isil.abcars.presenter;

import android.content.Context;

import com.isil.abcars.entity.MarcaEntity;

import java.util.List;


/**
 * Created by FranciscoParedes on 16/11/16.
 */
public interface MarcaView {

    Context getContext();

    void onMessageError(String message);
    void renderMarca(List<MarcaEntity> marcas);


}
