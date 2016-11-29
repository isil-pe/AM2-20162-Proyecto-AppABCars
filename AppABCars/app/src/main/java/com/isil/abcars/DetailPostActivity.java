package com.isil.abcars;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.isil.abcars.entity.PostEntity;
import com.isil.abcars.utils.BitmapManage;


public class DetailPostActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView txtPrecio;
    TextView txtMarca;
    TextView txtTitulo;
    TextView txtDesc;
    TextView txtAnio;
    TextView txtKM;
    TextView txtCilindrada;
    TextView txtCombustible;
    TextView txtTransmision;
    TextView txtColor;
    TextView txtPuertas;
    ImageView ivPost;
    PostEntity postEntity;
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        intent = this.getIntent();
        bundle = intent.getExtras();

        postEntity = new PostEntity();
        postEntity = (PostEntity) bundle.getSerializable("POST_ENTITY");

        ivPost = (ImageView) findViewById(R.id.ivPost);
        txtPrecio = (TextView) findViewById(R.id.txtPrecio);
        txtMarca = (TextView) findViewById(R.id.txtMarca);
        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtDesc = (TextView) findViewById(R.id.txtDesc);
        txtAnio = (TextView) findViewById(R.id.txtAnio);
        txtKM = (TextView) findViewById(R.id.txtKM);
        txtCilindrada = (TextView) findViewById(R.id.txtCilindrada);
        txtCombustible = (TextView) findViewById(R.id.txtCombustible);
        txtTransmision = (TextView) findViewById(R.id.txtTransmision);
        txtColor = (TextView) findViewById(R.id.txtColor);
        txtPuertas = (TextView) findViewById(R.id.txtPuertas);

        BitmapManage.getInstance().loadBitmap(postEntity.getFoto(),ivPost);
        txtPrecio.setText("S/. " + postEntity.getPrecio());
        txtMarca.setText(postEntity.getMarca() + " - "+ postEntity.getModelo());
        txtTitulo.setText(postEntity.getTitulo());
        txtDesc.setText(postEntity.getDesc());
        txtAnio.setText("Año: " + postEntity.getAnio());
        txtKM.setText("Km: " + postEntity.getKm());
        txtCilindrada.setText("Cilindrada: " + postEntity.getCilindrada());
        txtCombustible.setText("Combustibe: " + postEntity.getCombustible());
        txtTransmision.setText("Transmisión: " + postEntity.getTransmision());
        txtColor.setText("Color: " + postEntity.getColor());
        txtPuertas.setText("Num. Puertas: " + postEntity.getPuertas());

        // Para el mapa
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.detalleMapa);
        mapFragment.getMapAsync(this);


    }

    public void llamarContacto(View v){

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setAction("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:" + postEntity.getTelefono()));
        startActivity(intent);

    }

    /*
    * Metodos de OnMapReadyCallback
    * -------------------------------------------------------------------------------------- */
    @Override
    public void onMapReady(GoogleMap map) {

        Double la = Double.parseDouble(postEntity.getLatitud());
        Double lg = Double.parseDouble(postEntity.getLongitud());

        LatLng sydney = new LatLng(lg, la);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));

        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_puntero))
                .position(sydney));

    }
}
