package com.isil.abcars.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.isil.abcars.R;
import com.isil.abcars.entity.MarcaEntity;
import com.isil.abcars.presenter.MarcaPresenter;
import com.isil.abcars.presenter.MarcaView;
import com.isil.abcars.view.adapters.ListMarcaAdapter;
import com.isil.abcars.view.listeners.OnNavigationListener;

import java.util.List;

public class ListMarcaFragment extends Fragment implements MarcaView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GridView listMarca;
    private List<MarcaEntity> marcaEntities;
    private ListMarcaAdapter listMarcaAdapter;
    private MarcaPresenter marcaPresenter;

    private OnNavigationListener mListener;

    private View containerMarcas;

    public ListMarcaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListMarcaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListMarcaFragment newInstance(String param1, String param2) {
        ListMarcaFragment fragment = new ListMarcaFragment();
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
        return inflater.inflate(R.layout.fragment_list_marca, container, false);
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

        listMarca = (GridView) getView().findViewById(R.id.listMarca);
        containerMarcas = getView().findViewById(R.id.containerMarcas);

        marcaPresenter = new MarcaPresenter();
        marcaPresenter.attachedView(this);
        marcaPresenter.loadMarcas();

    }

    /*
    * Metodos de MArcaView
    * -------------------------------------------------------------------------------------- */
    @Override
    public void onMessageError(String message) {
        Snackbar snackbar = Snackbar.make(containerMarcas, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void renderMarca(List<MarcaEntity> marcas) {
        marcaEntities = marcas;
        listMarcaAdapter =  new ListMarcaAdapter(getContext(), marcaEntities);
        listMarca.setAdapter(listMarcaAdapter);
    }
}
