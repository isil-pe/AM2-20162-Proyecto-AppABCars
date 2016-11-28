package com.isil.abcars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.isil.abcars.entity.PostEntity;
import com.isil.abcars.utils.BitmapManage;


public class DetailPostActivity extends AppCompatActivity {

    TextView precio, marca, titulo, color;
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
        precio = (TextView) findViewById(R.id.txtPrecio);
        marca = (TextView) findViewById(R.id.txtMarca);
        titulo = (TextView) findViewById(R.id.txtTitulo);
        color = (TextView) findViewById(R.id.txtColor);

        BitmapManage.getInstance().loadBitmap(postEntity.getFoto(),ivPost);
        precio.setText("S/. " + postEntity.getPrecio());
        marca.setText(postEntity.getMarca() + " - "+ postEntity.getModelo());
        titulo.setText(postEntity.getTitulo());
        color.setText("Color: " + postEntity.getColor());


    }
}
