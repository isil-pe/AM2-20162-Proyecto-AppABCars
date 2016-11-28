package com.isil.abcars.presenter;

import android.util.Log;

import com.isil.abcars.entity.PostEntity;
import com.isil.abcars.storage.entity.ListPostsResponse;
import com.isil.abcars.storage.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MarcoTejeda on 16/11/16.
 */
public class PostPresenter {

    private static final String TAG = "PostPresenter";
    private final String ERROR_MESSAGE = "Ocurrio un error";

    private PostView postView;

    public void attachedView(PostView postView) {
        this.postView = postView;
    }

    public void loadPosts(){

        /// Traemos el listado del backeles
        Call<ListPostsResponse> call = ApiClient.getMyApiClient().listarPosts();
        call.enqueue(new Callback<ListPostsResponse>() {
            @Override
            public void onResponse(Call<ListPostsResponse> call, Response<ListPostsResponse> response) {
                if(response.isSuccessful()){
                    postSuccess(response.body());

                }else {
                    postError(ERROR_MESSAGE);
                }
            }

            @Override
            public void onFailure(Call<ListPostsResponse> call, Throwable t) {
                String json = "Error";
                try{
                    json = new StringBuffer().append(t.getMessage()).toString();
                }catch (NullPointerException e) {}
                Log.v("FRAGMENT POSTS", "json marca>>>>> " + json);

                postError(json);
            }
        });

    }

    public void postSuccess(ListPostsResponse listPostsResponse){
        if(listPostsResponse != null) {
            List<PostEntity> posts = listPostsResponse.getData();
            postView.renderPost(posts);
        }
    }

    public void postError(String messageError){
        postView.onMessageError(messageError);
    }

    public void goToDetail(PostEntity postEntity){
        postView.gotToDetail(postEntity);
    }
}
