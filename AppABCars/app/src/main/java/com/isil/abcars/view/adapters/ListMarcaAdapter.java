package com.isil.abcars.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.isil.abcars.R;
import com.isil.abcars.entity.MarcaEntity;
import com.isil.abcars.entity.PostEntity;
import com.isil.abcars.utils.BitmapManage;

import java.util.List;

/**
 * Created by FranciscoParedes on 16/11/16.
 */
public class ListMarcaAdapter extends BaseAdapter{

    private Context context;
    private List<MarcaEntity> lsMarcaEntities;

    public ListMarcaAdapter(Context context, List<MarcaEntity> lsMarcaEntities) {
        this.context = context;
        this.lsMarcaEntities = lsMarcaEntities;
    }

    @Override
    public int getCount() {
        return lsMarcaEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return lsMarcaEntities.get(position);
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
            v = inflater.inflate(R.layout.marca_row, null);

            holder = new ViewHolder();
            holder.txtMarca = (TextView)v.findViewById(R.id.txtMarca);
            holder.ivMarca = (ImageView) v.findViewById(R.id.ivMarca);
            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }

        MarcaEntity entry = lsMarcaEntities.get(position);
        if(entry != null) {

            holder.txtMarca.setText(entry.getMarca());
            BitmapManage.getInstance().loadBitmap(entry.getLogo(), holder.ivMarca);

        }

        return v;

    }

    static class ViewHolder {
        ImageView ivMarca;
        TextView txtMarca;
    }
}
