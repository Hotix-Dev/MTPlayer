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
import com.tm.mtplayer.helpers.Stub;
import com.tm.mtplayer.models.Channel;
import com.tm.mtplayer.models.PlayList;

import java.util.ArrayList;

public class PlayListActivity extends AppCompatActivity {


    private RelativeLayout rlHome;
    private RelativeLayout rlLiveTv;
    private RelativeLayout rlMovies;
    private RelativeLayout rlShows;
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
        rlHome = (RelativeLayout) findViewById(R.id.rl_play_list_home);
        rlLiveTv = (RelativeLayout) findViewById(R.id.rl_play_list_tv);
        rlMovies = (RelativeLayout) findViewById(R.id.rl_play_list_movies);
        rlShows = (RelativeLayout) findViewById(R.id.rl_play_list_shows);
        rlPlayList = (RelativeLayout) findViewById(R.id.rl_play_list);
        rlPlayListExpand = (RelativeLayout) findViewById(R.id.rl_play_list_expand);
        ibCollaps = (AppCompatImageButton) findViewById(R.id.ibtn_play_list_collaps_lists);
        ibExpand = (AppCompatImageButton) findViewById(R.id.ibtn_play_list_expand_lists);
        lvPlayList = (ListView) findViewById(R.id.lv_play_list_categories);
        lvChannels = (ListView) findViewById(R.id.lv_play_list_medias);
        vPlayer = (PlayerView) findViewById(R.id.ex_player);
    }

    private void init() {

        ALL_PLAY_LISTS = Stub.getPlayLists();

        mPlayListAdapter = new MyPlayListAdapter(ALL_PLAY_LISTS, getApplicationContext());
        lvPlayList.setAdapter(mPlayListAdapter);

        SELECTED_PLAY_LIST = ALL_PLAY_LISTS.get(0);

        if (SELECTED_PLAY_LIST != null) {
            mChannelsAdapter = new MyChannelsAdapter(SELECTED_PLAY_LIST.getChannels(), getApplicationContext());
            lvChannels.setAdapter(mChannelsAdapter);
        }

        rlHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    finish();
                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                }
            }
        });

        rlLiveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                }
            }
        });

        rlMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getApplicationContext(), MoviesActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    finish();
                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                }
            }
        });

        rlShows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getApplicationContext(), ShowsActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    finish();
                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                }
            }
        });

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

}