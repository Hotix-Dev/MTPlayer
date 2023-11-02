package com.tm.mtplayer.activites;

import static com.tm.mtplayer.helpers.ConstantConfig.ALL_PLAY_LISTS;
import static com.tm.mtplayer.helpers.Utils.showSnackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.tm.mtplayer.R;
import com.tm.mtplayer.adapters.MyChannelsAdapter;
import com.tm.mtplayer.adapters.MyPlayListAdapter;
import com.tm.mtplayer.models.PlayList;

import java.util.ArrayList;

public class PlayListActivity extends AppCompatActivity {
    private ListView lvPlayList;
    private ListView lvChannels;
    private MyPlayListAdapter mPlayListAdapter;
    private MyChannelsAdapter mChannelsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bindViews();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
        } catch (Exception e) {
            Log.e("PLAY LIST LOG", e.toString());
            showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
        }
    }

    /**********************************************************************************************/

    private void bindViews() {
        lvPlayList = (ListView) findViewById(R.id.lv_play_list_categories);
        lvChannels = (ListView) findViewById(R.id.lv_play_list_medias);
    }

    private void init() {

        ALL_PLAY_LISTS = new ArrayList<PlayList>();

    }

    /**********************************************************************************************/

}