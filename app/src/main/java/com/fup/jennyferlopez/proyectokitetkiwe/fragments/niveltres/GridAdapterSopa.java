package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import com.fup.jennyferlopez.proyectokitetkiwe.R;

/**
 * Created by Jennyfer Lopez on 31/05/2016.
 */
public class GridAdapterSopa extends BaseAdapter {

    private Context context;
    private String[] items;
    LayoutInflater inflater;

    public GridAdapterSopa(Context context, String[] items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cell, null);
        }
        Button button = (Button) convertView.findViewById(R.id.grid_item);
        button.setText(items[position]);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(context.getResources().getAssets(), font_url);
        button.setTypeface(font);

        return convertView;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

