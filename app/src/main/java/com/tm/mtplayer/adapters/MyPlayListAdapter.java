package com.tm.mtplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tm.mtplayer.R;
import com.tm.mtplayer.models.PlayList;

import java.util.ArrayList;

public class MyPlayListAdapter  extends ArrayAdapter<PlayList> {
    Context mContext;
    Boolean ShowCount = false;
    private ArrayList<PlayList> dataSet;

    public MyPlayListAdapter(ArrayList<PlayList> data, Context context) {
        super(context, R.layout.item_data_obj_row, data);
        this.dataSet = data;
        this.mContext = context;
        this.ShowCount = false;
    }

    public MyPlayListAdapter(ArrayList<PlayList> data, Context context, Boolean ShowCount) {
        super(context, R.layout.item_data_obj_row, data);
        this.dataSet = data;
        this.mContext = context;
        this.ShowCount = ShowCount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PlayList dataModel = getItem(position);

        MyPlayListAdapter.ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new MyPlayListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_data_obj_row, parent, false);
            viewHolder.rl_count = (RelativeLayout) convertView.findViewById(R.id.rl_data_obj_row_count);
            viewHolder.tv_count = (AppCompatTextView) convertView.findViewById(R.id.tv_data_obj_row_count);
            viewHolder.item_title = (AppCompatTextView) convertView.findViewById(R.id.tv_data_obj_row_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyPlayListAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.rl_count.setVisibility(View.GONE);
        if ((ShowCount) && (dataModel.getChannels() != null)&& (dataModel.getChannels().size() > 0)){
            viewHolder.rl_count.setVisibility(View.VISIBLE);
            viewHolder.tv_count.setText(dataModel.getChannels().size() + "");
        }
        viewHolder.item_title.setText(dataModel.getTitle());

        // Return the completed view to render on screen
        return convertView;
    }

    // View lookup cache
    private static class ViewHolder {
        RelativeLayout rl_count;
        AppCompatTextView tv_count;
        AppCompatTextView item_title;
    }
}
