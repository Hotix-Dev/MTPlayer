package com.tm.mtplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tm.mtplayer.R;
import com.tm.mtplayer.models.Channel;

import java.util.ArrayList;

public class MyGridAdapter extends BaseAdapter {
    Context mContext;
    private ArrayList<Channel> dataSet;

    public MyGridAdapter(ArrayList<Channel> data, Context context) {
        super();
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.item_grid_sell, null);
        } else {
            grid = (View) convertView;
        }

        AppCompatImageView imgIcon = (AppCompatImageView) grid.findViewById(R.id.img_grid_sell_icon);

        AppCompatTextView tvTitle = (AppCompatTextView) grid.findViewById(R.id.tv_grid_sell_title);

        //imgIcon.setImageDrawable(dataSet.get(position).getLogo());
        tvTitle.setText(dataSet.get(position).getTitle());
        try {

            Glide.with(mContext)
                    .load(dataSet.get(position).getLogo())
                    .centerCrop()
                    .error(R.drawable.ic_splash_screen_logo)
                    .placeholder(R.drawable.loading_animation)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgIcon);

        } catch (Exception e) {
        }

        return grid;
    }

}
