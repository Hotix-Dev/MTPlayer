package com.tm.mtplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tm.mtplayer.R;
import com.tm.mtplayer.models.PlayList;

import java.util.ArrayList;

public class MyPlayListAdapter  extends ArrayAdapter<PlayList> {
    Context mContext;
    private ArrayList<PlayList> dataSet;

    public MyPlayListAdapter(ArrayList<PlayList> data, Context context) {
        super(context, R.layout.item_data_obj_row, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PlayList dataModel = getItem(position);

        MyPlayListAdapter.ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new MyPlayListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_data_obj_row, parent, false);
            viewHolder.item_title = (AppCompatTextView) convertView.findViewById(R.id.tv_data_obj_row_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyPlayListAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.item_title.setText(dataModel.getTitle());

        // Return the completed view to render on screen
        return convertView;
    }

    // View lookup cache
    private static class ViewHolder {
        AppCompatTextView item_title;
    }
}
