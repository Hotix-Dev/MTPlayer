package com.tm.mtplayer.activites;

import static com.tm.mtplayer.helpers.Utils.showSnackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tm.mtplayer.R;
import com.tm.mtplayer.helpers.MySettings;

public class HomeActivity extends AppCompatActivity {

    private AppCompatImageView ivHomeLogo;
    private RelativeLayout rlTv;
    private RelativeLayout rlMovies;
    private RelativeLayout rlShows;
    private RelativeLayout rlSettings;
    private RelativeLayout rlExit;

    private AppCompatTextView tvVersion;
    private AppCompatTextView tvInfos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bindViews();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            loadeImage();
        } catch (Exception e) {
            Log.e("HOME LOG", e.toString());
            showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
        }
    }

    @Override
    public void onBackPressed() {
        startExitDialog();
    }

    /**********************************************************************************************/

    private void bindViews() {

        ivHomeLogo = (AppCompatImageView) findViewById(R.id.iv_home_logo);

        rlTv = (RelativeLayout) findViewById(R.id.rl_home_tv);
        rlMovies = (RelativeLayout) findViewById(R.id.rl_home_movie);
        rlShows = (RelativeLayout) findViewById(R.id.rl_home_shows);
        rlSettings = (RelativeLayout) findViewById(R.id.rl_home_settings);
        rlExit = (RelativeLayout) findViewById(R.id.rl_home_exit);

        tvVersion = (AppCompatTextView) findViewById(R.id.tv_home_version);
        tvInfos = (AppCompatTextView) findViewById(R.id.tv_home_infos);
    }

    private void init() {

        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            tvVersion.setText(getString(R.string.text_version) + " " + pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("HOME LOG", e.toString());
        }

        rlTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getApplicationContext(), PlayListActivity.class);
                    startActivity(i);
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
                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                }
            }
        });

        rlSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(i);
                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                }
            }
        });

        rlExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startExitDialog();
                } catch (Exception e) {
                    showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
                } finally {
                }
            }
        });



    }

    /**********************************************************************************************/

    private void loadeImage() {
        try {
            Glide.with(getApplicationContext())
                    .load(R.drawable.ic_splash_screen_logo)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_splash_screen_logo)
                    .into(ivHomeLogo);
        } catch (Exception e) {
            showSnackbar(findViewById(android.R.id.content), getString(R.string.error_message_something_wrong));
        }
    }
    private void startExitDialog() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);

        View mView = getLayoutInflater().inflate(R.layout.dialog_exit, null);
        AppCompatButton btnYes = (AppCompatButton) mView.findViewById(R.id.btn_dialog_exit_yes);
        AppCompatButton btnCancel = (AppCompatButton) mView.findViewById(R.id.btn_dialog_exit_cancel);

        mBuilder.setView(mView);
        mBuilder.setCancelable(false);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
}