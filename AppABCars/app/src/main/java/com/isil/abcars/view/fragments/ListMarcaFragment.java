package com.isil.abcars.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.isil.abcars.R;
import com.isil.abcars.entity.MarcaEntity;
import com.isil.abcars.storage.entity.MarcaResponse;
import com.isil.abcars.storage.request.ApiClient;
import com.isil.abcars.view.adapters.ListMarcaAdapter;
import com.isil.abcars.view.listeners.OnNavigationListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListMarcaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GridView listMarca;
    private MarcaEntity marcaEntity;
    private ListMarcaAdapter listMarcaAdapter;

    private OnNavigationListener mListener;

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

        /// Traemos el listado del backeles
        Call<MarcaResponse> call = ApiClient.getMyApiClient().marcas();
        call.enqueue(new Callback<MarcaResponse>() {
            @Override
            public void onResponse(Call<MarcaResponse> call, Response<MarcaResponse> response) {
                if(response.isSuccessful()){
                    //marcasSuccess(response.body());
                    MarcaResponse lpr = response.body();
                    List<MarcaEntity> marcaEntities = lpr.getData();

                    listMarcaAdapter = new ListMarcaAdapter(getContext(), marcaEntities);
                    listMarca.setAdapter(listMarcaAdapter);

                }else {
                    //marcasError(ERROR_MESSAGE);
                }
            }

            @Override
            public void onFailure(Call<MarcaResponse> call, Throwable t) {
                String json = "Error";
                try{
                    json = new StringBuffer().append(t.getMessage()).toString();
                }catch (NullPointerException e) {}
                Log.v("FRAGMENT MARCAS", "json marca>>>>> " + json);
                //marcasError(json);
            }
        });
    }

}
