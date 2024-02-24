package com.tm.mtplayer.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tm.mtplayer.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String TAG = "SplashScreenActivity";

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    // Views
    private AppCompatImageView ivSplashLogo;
    private AppCompatTextView tvSplashHeader;
    private AppCompatTextView tvSplashFooter;
    private ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bindViews();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            startDelay();
        } catch (Exception e) {
            Log.e("SPLASH LOG", e.toString());
        }
    }

    /**********************************************************************************************/

    private void bindViews() {
        ivSplashLogo = (AppCompatImageView) findViewById(R.id.iv_splash_logo);
        tvSplashHeader = (AppCompatTextView) findViewById(R.id.tv_splash_screen_header);
        tvSplashFooter = (AppCompatTextView) findViewById(R.id.tv_splash_screen_footer);
        pbLoading = (ProgressBar) findViewById(R.id.pb_splash_screen_footer);
    }

    private void init() {

        //load logo
        Glide.with(getApplicationContext())
                .load(R.drawable.ic_splash_screen_logo)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_splash_screen_logo)
                .into(ivSplashLogo);
    }

    /**********************************************************************************************/

    private void startDelay() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //Intent i = new Intent(SplashScreenActivity.this, DownloadActivity.class);
                //Intent i = new Intent(SplashScreenActivity.this, HomeActivity.class);
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }


}