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

public class ShowsActivity extends AppCompatActivity {

    private RelativeLayout rlHome;
    private RelativeLayout rlLiveTv;
    private RelativeLayout rlMovies;
    private RelativeLayout rlShows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows);

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
        rlHome = (RelativeLayout) findViewById(R.id.rl_shows_home);
        rlLiveTv = (RelativeLayout) findViewById(R.id.rl_shows_tv);
        rlMovies = (RelativeLayout) findViewById(R.id.rl_shows_movies);
        rlShows = (RelativeLayout) findViewById(R.id.rl_shows_shows);
    }

    private void init() {

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
                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                }
            }
        });


    }

    /**********************************************************************************************/

}