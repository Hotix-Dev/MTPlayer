package com.tm.mtplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatTextView;

import com.tm.mtplayer.R;
import com.tm.mtplayer.models.Channel;
import com.tm.mtplayer.models.PlayList;

import java.util.ArrayList;

public class MyChannelsAdapter extends ArrayAdapter<Channel> {
    Context mContext;
    private ArrayList<Channel> dataSet;

    public MyChannelsAdapter(ArrayList<Channel> data, Context context) {
        super(context, R.layout.item_data_obj_row, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Channel dataModel = getItem(position);

        MyChannelsAdapter.ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new MyChannelsAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_data_obj_row, parent, false);
            viewHolder.item_title = (AppCompatTextView) convertView.findViewById(R.id.tv_data_obj_row_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyChannelsAdapter.ViewHolder) convertView.getTag();
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
