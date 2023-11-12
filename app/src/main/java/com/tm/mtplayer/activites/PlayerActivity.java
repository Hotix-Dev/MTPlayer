package com.tm.mtplayer.activites;

import static com.tm.mtplayer.helpers.ConstantConfig.ALL_PLAY_LISTS;
import static com.tm.mtplayer.helpers.ConstantConfig.SELECTED_CHANNEL;
import static com.tm.mtplayer.helpers.ConstantConfig.SELECTED_PLAY_LIST;
import static com.tm.mtplayer.helpers.Utils.collapse;
import static com.tm.mtplayer.helpers.Utils.expand;
import static com.tm.mtplayer.helpers.Utils.showSnackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.tm.mtplayer.R;
import com.tm.mtplayer.adapters.MyChannelsAdapter;
import com.tm.mtplayer.adapters.MyPlayListAdapter;
import com.tm.mtplayer.helpers.Stub;

public class PlayerActivity extends AppCompatActivity {
    private PlayerView vPlayer;
    private SimpleExoPlayer exPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

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
            Log.e("PLAYER LIST LOG", e.toString());
            showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
        }
    }

    @Override
    public void onBackPressed() {
        if (exPlayer != null) {

            exPlayer.pause();
            exPlayer.release();
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (exPlayer != null) {

            exPlayer.pause();
            exPlayer.release();
        }
        super.onDestroy();
    }

    /**********************************************************************************************/

    private void bindViews() {
        vPlayer = (PlayerView) findViewById(R.id.ex_player_full);
    }

    private void init() {

        if (SELECTED_CHANNEL != null) {
            if (exPlayer != null) {
                exPlayer.pause();
                exPlayer.release();
            }
            exPlayer = new SimpleExoPlayer.Builder(getApplicationContext()).build();
            vPlayer.setPlayer(exPlayer);
            MediaItem _MediaItem = MediaItem.fromUri(SELECTED_CHANNEL.getUrl());
            exPlayer.addMediaItem(_MediaItem);
            exPlayer.prepare();
            exPlayer.play();
        }


    }

    /**********************************************************************************************/
}