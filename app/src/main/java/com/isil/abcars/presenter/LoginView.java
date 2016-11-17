package com.isil.abcars.presenter;

import android.content.Context;

/**
 * Created by MarcoTejeda on 15/11/16.
 */
public interface LoginView {

    void showLoading();
    void hideLoading();
    Context getContext();

    void onMessageError(String message);
    void gotoMain();

}
