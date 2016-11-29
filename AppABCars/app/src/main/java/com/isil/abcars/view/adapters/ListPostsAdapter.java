package com.isil.abcars.view.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.isil.abcars.R;
import com.isil.abcars.entity.PostEntity;
import com.isil.abcars.utils.BitmapManage;

import java.util.List;

/**
 * Created by MarcoTejeda on 16/11/16.
 */
public class ListPostsAdapter extends BaseAdapter{

    private Context context;
    private List<PostEntity> lsPostEntities;

    public ListPostsAdapter(Context context, List<PostEntity> lsNoteEntities) {
        this.context = context;
        this.lsPostEntities = lsNoteEntities;

        // Image Placeholder
        BitmapManage.getInstance().setPlaceholder(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.placeholdercar
        ));

    }

    @Override
    public int getCount() {
        return lsPostEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return lsPostEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        View v = convertView;

        if(v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.post_row, null);

            holder = new ViewHolder();
            holder.txtPrecio = (TextView)v.findViewById(R.id.txtPrecio);
            holder.txtMarca = (TextView)v.findViewById(R.id.txtMarca);
            holder.ivPost = (ImageView) v.findViewById(R.id.ivPost);
            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }

        PostEntity entry = lsPostEntities.get(position);
        if(entry != null) {

            holder.txtPrecio.setText("S/. " + String.valueOf(entry.getPrecio()));
            holder.txtMarca.setText(entry.getMarca() + " - " + entry.getModelo());
            BitmapManage.getInstance().loadBitmap(entry.getFoto(), holder.ivPost);

        }

        return v;

    }

    static class ViewHolder {
        ImageView ivPost;
        TextView txtPrecio;
        TextView txtMarca;
    }


}
