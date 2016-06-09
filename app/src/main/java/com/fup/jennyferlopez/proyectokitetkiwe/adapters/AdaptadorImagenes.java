package com.fup.jennyferlopez.proyectokitetkiwe.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class AdaptadorImagenes extends BaseAdapter {

    int[] imagenes;
    Context context;

    public AdaptadorImagenes(int[] imagenes, Context context) {
        this.imagenes = imagenes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imagenes.length;
    }

    @Override
    public Object getItem(int position) {
        return imagenes[position];
    }

    @Override
    public long getItemId(int position) {
        return imagenes[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView =new ImageView(context);
        imageView.setBackgroundResource(imagenes[position]);
        imageView.setLayoutParams(new GridView.LayoutParams(135,
                145));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}
