package com.isil.abcars.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.isil.abcars.R;
import com.isil.abcars.entity.PostEntity;
import com.isil.abcars.entity.UserEntity;
import com.isil.abcars.storage.entity.ListPostsResponse;
import com.isil.abcars.storage.entity.PerfilRaw;
import com.isil.abcars.storage.entity.PerfilResponse;
import com.isil.abcars.storage.entity.RegisterRaw;
import com.isil.abcars.storage.request.ApiClient;
import com.isil.abcars.view.adapters.ListPostsAdapter;
import com.isil.abcars.view.listeners.OnNavigationListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerfilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnNavigationListener mListener;

    private EditText eteNombre;
    private EditText eteApellido;
    private EditText eteEmail;
    private EditText etePassword;
    private Button btnActualizarPerfil;

    private String nombre;
    private String apellido;
    private String email;
    private String password;

    private PerfilResponse lpr;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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
        return inflater.inflate(R.layout.fragment_perfil, container, false);
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

        if(getArguments()!=null)
        {
            String emailPreferences = (String)getArguments().getSerializable("USERNAME");
            String passwordPreferences = (String)getArguments().getSerializable("PASSWORD");

            ((EditText)getView().findViewById(R.id.txtCorreo)).setText(emailPreferences);
            ((EditText)getView().findViewById(R.id.txtContrasena)).setText(passwordPreferences);

            PerfilRaw perfilRaw = new PerfilRaw();
            perfilRaw.setLogin(emailPreferences);
            perfilRaw.setPassword(passwordPreferences);

            Call<PerfilResponse> call = ApiClient.getMyApiClient().getPerfil(perfilRaw);
            call.enqueue(new Callback<PerfilResponse>() {
                @Override
                public void onResponse(Call<PerfilResponse> call, Response<PerfilResponse> response) {
                    if(response.isSuccessful()){
                        lpr = response.body();

                        ((EditText)getView().findViewById(R.id.txtNombre)).setText(lpr.getName());
                        ((EditText)getView().findViewById(R.id.txtApellido)).setText(lpr.getLast_name());

                    }else {
                        //marcasError(ERROR_MESSAGE);
                    }
                }

                @Override
                public void onFailure(Call<PerfilResponse> call, Throwable t) {
                    String json = "Error";
                    try{
                        json = new StringBuffer().append(t.getMessage()).toString();
                    }catch (NullPointerException e) {}
                    Log.v("FRAGMENT PERFIL", "json marca>>>>> " + json);
                }
            });
        }

        btnActualizarPerfil =(Button)getView().findViewById(R.id.btnActualizar);
        btnActualizarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActualizarPerfil();
            }
        });
    }

    public void ActualizarPerfil()
    {
        nombre = ((EditText)getView().findViewById(R.id.txtNombre)).getText().toString();
        apellido = ((EditText)getView().findViewById(R.id.txtApellido)).getText().toString();
        email = ((EditText)getView().findViewById(R.id.txtCorreo)).getText().toString();
        password = ((EditText)getView().findViewById(R.id.txtContrasena)).getText().toString();

        PerfilRaw perfilRaw = new PerfilRaw();
        perfilRaw.setName(nombre);
        perfilRaw.setLast_name(apellido);
        perfilRaw.setEmail(email);
        perfilRaw.setPassword(password);
        perfilRaw.setObjectId(lpr.getObjectId());


        Call<PerfilResponse> call = ApiClient.getMyApiClient().setPerfil(perfilRaw);
        call.enqueue(new Callback<PerfilResponse>() {
            @Override
            public void onResponse(Call<PerfilResponse> call, Response<PerfilResponse> response) {
                if(response.isSuccessful()) {
                    //loginSuccess();
                } else {
                    //loginError(ERROR_MESSAGE);
                }
            }

            @Override
            public void onFailure(Call<PerfilResponse> call, Throwable t) {
                String json = "Error ";
                try {
                    json = new StringBuffer().append(t.getMessage()).toString();
                } catch (NullPointerException e) {
                }
                //Log.v(TAG, "json >>>> " + json);
                //loginError(json);
            }
        });
    }

}
