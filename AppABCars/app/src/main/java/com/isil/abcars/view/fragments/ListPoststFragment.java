package com.isil.abcars.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.isil.abcars.R;
import com.isil.abcars.entity.PostEntity;
import com.isil.abcars.presenter.PostPresenter;
import com.isil.abcars.presenter.PostView;
import com.isil.abcars.view.adapters.ListPostsAdapter;
import com.isil.abcars.view.listeners.OnNavigationListener;

import java.util.List;


public class ListPoststFragment extends Fragment implements PostView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listPosts;
    private List<PostEntity> listPostsEntities;
    private ListPostsAdapter listPostsAdapter;
    private PostPresenter postPresenter;

    private View containerListPost, containerLogin;

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
        containerListPost = getView().findViewById(R.id.containerListPost);

        postPresenter = new PostPresenter();
        postPresenter.attachedView(this);
        postPresenter.loadPosts();

    }


    /*
    * Metodos de PostsView
    * -------------------------------------------------------------------------------------- */
    @Override
    public void onMessageError(String message) {
        Snackbar snackbar = Snackbar.make(containerListPost, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void renderPost(List<PostEntity> posts) {
        listPostsEntities = posts;
        listPostsAdapter =  new ListPostsAdapter(getContext(), listPostsEntities);
        listPosts.setAdapter(listPostsAdapter);
    }
}
