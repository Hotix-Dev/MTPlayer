package com.tm.mtplayer.activites;

import static com.tm.mtplayer.helpers.ConstantConfig.APP_VALIDITY;
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
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tm.mtplayer.R;
import com.tm.mtplayer.helpers.MySettings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private MySettings MySettings;

    private AppCompatImageView ivHomeLogo;
    private AppCompatImageView ivExit;
    private AppCompatImageView ivSettings;

    private RelativeLayout rlTv;
    private RelativeLayout rlMovies;
    private RelativeLayout rlShows;
    private RelativeLayout rlExit;
    private RelativeLayout rlSettings;

    private AppCompatTextView tvCurDate;
    private AppCompatTextView tvExpDate;
    private AppCompatTextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        ivHomeLogo = (AppCompatImageView) findViewById(R.id.iv_main_logo);
        ivSettings = (AppCompatImageView) findViewById(R.id.iv_main_settings);
        ivExit = (AppCompatImageView) findViewById(R.id.iv_main_exit);

        rlTv = (RelativeLayout) findViewById(R.id.rl_main_tv);
        rlMovies = (RelativeLayout) findViewById(R.id.rl_main_movie);
        rlShows = (RelativeLayout) findViewById(R.id.rl_main_shows);
        rlSettings = (RelativeLayout) findViewById(R.id.rl_main_settings);
        rlExit = (RelativeLayout) findViewById(R.id.rl_main_exit);

        tvCurDate = (AppCompatTextView) findViewById(R.id.tv_main_current_date);
        tvExpDate = (AppCompatTextView) findViewById(R.id.tv_main_expiration_date);
        tvVersion = (AppCompatTextView) findViewById(R.id.tv_main_app_version);
    }

    private void init() {

        MySettings = new MySettings(getApplicationContext());

        Calendar c = Calendar.getInstance();
        String curDate = new SimpleDateFormat("EE dd MMM yyyy", Locale.getDefault()).format(c.getTime());
        String curTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(c.getTime());
        tvCurDate.setText(getString(R.string.text_date) + " : " +  curDate + "   " + getString(R.string.text_time) + " : " + curTime);

//        c.add(Calendar.DATE, 7);
//        String expDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(c.getTime());
        tvExpDate.setText(getString(R.string.text_expiration_date) + " : " + ((APP_VALIDITY != null) ? APP_VALIDITY.getDate(): "-") );

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