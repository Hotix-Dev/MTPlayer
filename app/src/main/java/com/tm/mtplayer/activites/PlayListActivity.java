package com.tm.mtplayer.activites;

import static com.tm.mtplayer.helpers.ConstantConfig.ALL_PLAY_LISTS;
import static com.tm.mtplayer.helpers.ConstantConfig.SELECTED_PLAY_LIST;
import static com.tm.mtplayer.helpers.ConstantConfig.SELECTED_CHANNEL;
import static com.tm.mtplayer.helpers.Utils.collapse;
import static com.tm.mtplayer.helpers.Utils.expand;
import static com.tm.mtplayer.helpers.Utils.showSnackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import com.tm.mtplayer.models.Channel;
import com.tm.mtplayer.models.PlayList;

import java.util.ArrayList;

public class PlayListActivity extends AppCompatActivity {

    private RelativeLayout rlPlayList;
    private RelativeLayout rlPlayListExpand;
    private AppCompatImageButton ibCollaps;
    private AppCompatImageButton ibExpand;
    private ListView lvPlayList;
    private ListView lvChannels;
    private MyPlayListAdapter mPlayListAdapter;
    private MyChannelsAdapter mChannelsAdapter;
    private PlayerView vPlayer;
    private SimpleExoPlayer exPlayer;

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

    @Override
    public void onBackPressed() {
        exPlayer.pause();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        exPlayer.pause();
        super.onDestroy();
    }

    /**********************************************************************************************/

    private void bindViews() {
        rlPlayList = (RelativeLayout) findViewById(R.id.rl_play_list);
        rlPlayListExpand = (RelativeLayout) findViewById(R.id.rl_play_list_expand);
        ibCollaps = (AppCompatImageButton) findViewById(R.id.ibtn_play_list_collaps_lists);
        ibExpand = (AppCompatImageButton) findViewById(R.id.ibtn_play_list_expand_lists);
        lvPlayList = (ListView) findViewById(R.id.lv_play_list_categories);
        lvChannels = (ListView) findViewById(R.id.lv_play_list_medias);
        vPlayer = (PlayerView) findViewById(R.id.ex_player);
    }

    private void init() {

        loadTestData();

        mPlayListAdapter = new MyPlayListAdapter(ALL_PLAY_LISTS, getApplicationContext());
        lvPlayList.setAdapter(mPlayListAdapter);

        SELECTED_PLAY_LIST = ALL_PLAY_LISTS.get(0);

        if (SELECTED_PLAY_LIST != null) {
            mChannelsAdapter = new MyChannelsAdapter(SELECTED_PLAY_LIST.getChannels(), getApplicationContext());
            lvChannels.setAdapter(mChannelsAdapter);
        }

        lvPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                try {
                    lvPlayList.setEnabled(false);
                    SELECTED_PLAY_LIST = ALL_PLAY_LISTS.get(position);

                    if (SELECTED_PLAY_LIST != null) {
                        mChannelsAdapter = new MyChannelsAdapter(SELECTED_PLAY_LIST.getChannels(), getApplicationContext());
                        lvChannels.setAdapter(mChannelsAdapter);
                    }

                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                    lvPlayList.setEnabled(true);
                }
            }
        });

        lvChannels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                try {
                    lvChannels.setEnabled(false);
                    SELECTED_CHANNEL = SELECTED_PLAY_LIST.getChannels().get(position);

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

                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                    lvChannels.setEnabled(true);
                }
            }
        });

        ibCollaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    collapse(rlPlayList);
                    rlPlayListExpand.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                }
            }
        });

        ibExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    expand(rlPlayList);
                    rlPlayListExpand.setVisibility(View.GONE);
                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                }
            }
        });


    }

    /**********************************************************************************************/
    private void loadTestData() {
        ALL_PLAY_LISTS = new ArrayList<PlayList>();

        PlayList p1 = new PlayList();
        p1.setTitle("GENERALISTE FHD");
        p1.channels = new ArrayList<Channel>();
        p1.channels.add(new Channel(1, "[FR] TF1 FHD", "http://dm.lion-ott.com:80/MDju3120/76un4658/820880"));
        p1.channels.add(new Channel(2, "[FR] STAR ACADEMY 2022", "http://dm.lion-ott.com:80/MDju3120/76un4658/820879"));
        p1.channels.add(new Channel(3, "[FR] FRANCE 2 FHD", "http://dm.lion-ott.com:80/MDju3120/76un4658/820878"));
        p1.channels.add(new Channel(4, "[FR] FRANCE 3 FHD", "http://dm.lion-ott.com:80/MDju3120/76un4658/820877"));
        p1.channels.add(new Channel(5, "[FR] FRANCE 4 FHD", "http://dm.lion-ott.com:80/MDju3120/76un4658/820876"));
        p1.channels.add(new Channel(6, "[FR] FRANCE 5 FHD", "http://dm.lion-ott.com:80/MDju3120/76un4658/820875"));
        p1.channels.add(new Channel(7, "[FR] M6 FHD", "http://dm.lion-ott.com:80/MDju3120/76un4658/820874"));
        p1.channels.add(new Channel(8, "[FR] ARTE FHD", "http://dm.lion-ott.com:80/MDju3120/76un4658/820873"));
        ALL_PLAY_LISTS.add(p1);

        PlayList p2 = new PlayList();
        p2.setTitle("CANAL✚ FHD");
        p2.channels = new ArrayList<Channel>();
        p2.channels.add(new Channel(1, "[FR] CANAL+ FHD", "http://dm.lion-ott.com:80/MDju3120/76un4658/820858"));
        p2.channels.add(new Channel(2, "[FR] CANAL+ CINEMA FHD", "http://dm.lion-ott.com:80/MDju3120/76un4658/820857"));
        p2.channels.add(new Channel(3, "[FR] CANAL+ BOX OFFICE FHD", "http://dm.lion-ott.com:80/MDju3120/76un4658/820789"));
        p2.channels.add(new Channel(4, "[FR] CANAL+ SPORT FHD", "http://dm.lion-ott.com:80/MDju3120/76un4658/820856"));
        p2.channels.add(new Channel(5, "[FR] CANAL+ SERIES FHD", "http://dm.lion-ott.com:80/MDju3120/76un4658/820855"));
        p2.channels.add(new Channel(6, "[FR] CANAL+ GRAND ECRAN FHD", "http://dm.lion-ott.com:80/MDju3120/76un4658/820854"));
        ALL_PLAY_LISTS.add(p2);

        PlayList p3 = new PlayList();
        p3.setTitle("ALL");
        p3.channels = new ArrayList<Channel>();
        p3.channels.add(new Channel(1, "Destination Nature", "https://i.mjh.nz/SamsungTVPlus/FRBC4000001IO.m3u8"));
        p3.channels.add(new Channel(2, "A prendre ou à laisser", "https://i.mjh.nz/SamsungTVPlus/FRBC4700003FS.m3u8"));
        p3.channels.add(new Channel(3, "Euronews en direct", "https://i.mjh.nz/SamsungTVPlus/FRBA1000005TW.m3u8"));
        p3.channels.add(new Channel(4, "Comédies - Rakuten TV", "https://i.mjh.nz/SamsungTVPlus/FRAJ4500024MR.m3u8"));
        p3.channels.add(new Channel(5, "L'effet papillon", "https://i.mjh.nz/SamsungTVPlus/FRBD11000038N.m3u8"));
        p3.channels.add(new Channel(6, "Les Z'amours", "https://i.mjh.nz/SamsungTVPlus/FRBD1900005BA.m3u8"));
        ALL_PLAY_LISTS.add(p3);
    }

}