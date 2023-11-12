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
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.tm.mtplayer.R;
import com.tm.mtplayer.adapters.MyChannelsAdapter;
import com.tm.mtplayer.adapters.MyGridAdapter;
import com.tm.mtplayer.adapters.MyPlayListAdapter;
import com.tm.mtplayer.helpers.Stub;

public class MoviesActivity extends AppCompatActivity {

    private RelativeLayout rlHome;
    private RelativeLayout rlLiveTv;
    private RelativeLayout rlMovies;
    private RelativeLayout rlShows;
    private ListView lvPlayList;
    private GridView gvMovies;
    private MyPlayListAdapter mPlayListAdapter;
    private MyGridAdapter mGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

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
            Log.e("MOVIES LOG", e.toString());
            showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**********************************************************************************************/

    private void bindViews() {
        rlHome = (RelativeLayout) findViewById(R.id.rl_movies_home);
        rlLiveTv = (RelativeLayout) findViewById(R.id.rl_movies_tv);
        rlMovies = (RelativeLayout) findViewById(R.id.rl_movies_movies);
        rlShows = (RelativeLayout) findViewById(R.id.rl_movies_shows);
        lvPlayList = (ListView) findViewById(R.id.lv_movies_categories);
        gvMovies = (GridView) findViewById(R.id.gv_movies);
    }

    private void init() {

        ALL_PLAY_LISTS = Stub.getMovies();

        mPlayListAdapter = new MyPlayListAdapter(ALL_PLAY_LISTS, getApplicationContext(), true);
        lvPlayList.setAdapter(mPlayListAdapter);

        SELECTED_PLAY_LIST = ALL_PLAY_LISTS.get(0);

        if (SELECTED_PLAY_LIST != null) {
            mGridAdapter = new MyGridAdapter(SELECTED_PLAY_LIST.getChannels(), getApplicationContext());
            gvMovies.setAdapter(mGridAdapter);
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
                    Intent i = new Intent(getApplicationContext(), PlayListActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    finish();
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
                        mGridAdapter = new MyGridAdapter(SELECTED_PLAY_LIST.getChannels(), getApplicationContext());
                        gvMovies.setAdapter(mGridAdapter);
                    }

                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                    lvPlayList.setEnabled(true);
                }
            }
        });

        gvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    gvMovies.setEnabled(false);
                    SELECTED_CHANNEL = SELECTED_PLAY_LIST.getChannels().get(position);
                    if (SELECTED_CHANNEL != null) {
                        Intent i = new Intent(getApplicationContext(), PlayerActivity.class);
                        startActivity(i);
                    }
                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                    gvMovies.setEnabled(true);
                }
            }
        });
    }

    /**********************************************************************************************/

}