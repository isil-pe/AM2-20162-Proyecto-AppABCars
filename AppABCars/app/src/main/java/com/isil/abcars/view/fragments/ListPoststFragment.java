package com.isil.abcars.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.isil.abcars.R;
import com.isil.abcars.entity.MarcaEntity;
import com.isil.abcars.entity.PostEntity;
import com.isil.abcars.storage.entity.ListPostsResponse;
import com.isil.abcars.storage.entity.MarcaResponse;
import com.isil.abcars.storage.request.ApiClient;
import com.isil.abcars.view.adapters.ListPostsAdapter;
import com.isil.abcars.view.listeners.OnNavigationListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPoststFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listPosts;
    private PostEntity postEntity;
    private ListPostsAdapter listPostsAdapter;

    private OnNavigationListener mListener;

    public ListPoststFragment() {
        // Required empty public constructor
    }


    public static ListPoststFragment newInstance(String param1, String param2) {
        ListPoststFragment fragment = new ListPoststFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_posts, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNavigationListener) {
            mListener = (OnNavigationListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listPosts = (ListView) getView().findViewById(R.id.listPosts);


        /// Traemos el listado del backeles
        Call<ListPostsResponse> call = ApiClient.getMyApiClient().listarPosts();
        call.enqueue(new Callback<ListPostsResponse>() {
            @Override
            public void onResponse(Call<ListPostsResponse> call, Response<ListPostsResponse> response) {
                if(response.isSuccessful()){
                    //marcasSuccess(response.body());
                    ListPostsResponse lpr = response.body();
                    List<PostEntity> postEntities = lpr.getData();

                    listPostsAdapter = new ListPostsAdapter(getContext(), postEntities);
                    listPosts.setAdapter(listPostsAdapter);


                }else {
                    //marcasError(ERROR_MESSAGE);
                }
            }

            @Override
            public void onFailure(Call<ListPostsResponse> call, Throwable t) {
                String json = "Error";
                try{
                    json = new StringBuffer().append(t.getMessage()).toString();
                }catch (NullPointerException e) {}
                Log.v("FRAGMENT POSTS", "json marca>>>>> " + json);

                //marcasError(json);
            }
        });

        //crudOperations= new CRUDOperations(new MyDatabase(this));
       // postEntity =

        //listPostsAdapter = new ListPostsAdapter(getContext(), postEntity);
        //listPosts.setAdapter(listPostsAdapter);


    }


}
