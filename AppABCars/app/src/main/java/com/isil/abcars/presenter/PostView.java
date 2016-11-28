package com.isil.abcars.presenter;

import android.content.Context;

import com.isil.abcars.entity.PostEntity;

import java.util.List;


/**
 * Created by MarcoTejeda on 16/11/16.
 */
public interface PostView {

    Context getContext();

    void onMessageError(String message);
    void renderPost(List<PostEntity> posts);

}
