package com.isil.abcars.presenter;

import android.content.Context;

import com.isil.abcars.entity.MarcaEntity;
import com.isil.abcars.entity.PostEntity;

import java.util.List;


/**
 * Created by MarcoTejeda on 16/11/16.
 */
public interface MarcaView {

    void showLoading();
    void hideLoading();
    Context getContext();

    void onMessageError(String message);
    void renderMarca(List<MarcaEntity> marcas);

}
