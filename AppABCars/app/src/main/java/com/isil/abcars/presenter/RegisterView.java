package com.isil.abcars.presenter;

import android.content.Context;

/**
 * Created by MarcoTejeda on 20/11/16.
 */
public interface RegisterView {

    void showLoading();
    void hideLoading();
    Context getContext();

    void onMessageError(String message);
    void gotoLogin();

}
