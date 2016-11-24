package com.isil.abcars.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.isil.abcars.R;
import com.isil.abcars.entity.PostEntity;

import java.util.ArrayList;
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

        View v = convertView;
        if(v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.post_row, null);

            ViewHolder holder = new ViewHolder();
            holder.txtPrecio = (TextView)v.findViewById(R.id.txtPrecio);
            holder.txtDesc = (TextView)v.findViewById(R.id.txtDesc);
            v.setTag(holder);
        }

        PostEntity entry = lsPostEntities.get(position);
        if(entry != null) {
            ViewHolder holder = (ViewHolder)v.getTag();
            holder.txtPrecio.setText(String.valueOf(entry.getPrecio()));
            holder.txtDesc.setText(entry.getDesc());
        }

        return v;

    }

    static class ViewHolder {
        ImageView ivPost;
        TextView txtPrecio;
        TextView txtDesc;
    }
}
