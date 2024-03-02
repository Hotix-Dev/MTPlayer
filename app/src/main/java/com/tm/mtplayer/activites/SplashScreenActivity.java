package com.tm.mtplayer.activites;

import static com.tm.mtplayer.helpers.ConstantConfig.APP_VALIDITY;
import static com.tm.mtplayer.helpers.Utils.showSnackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tm.mtplayer.R;
import com.tm.mtplayer.helpers.MySettings;
import com.tm.mtplayer.helpers.Utils;
import com.tm.mtplayer.models.AppValidity;
import com.tm.mtplayer.retrofit.RetrofitClient;
import com.tm.mtplayer.retrofit.RetrofitInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String TAG = "SplashScreenActivity";

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    private MySettings MySettings;

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
            //startDelay();
            loadingAppValidity();
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
        MySettings = new MySettings(getApplicationContext());
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

    /**********************************(  Loading App Validity  )*************************************/
    public void loadingAppValidity() {

        String DeviceID = Utils.getDeviceId(getApplicationContext());
        String URL = "Security/GetApplicationValidite?";

        RetrofitInterface service = RetrofitClient.getClientApi().create(RetrofitInterface.class);
        Call<AppValidity> apiCall = service.getApplicationValidityQuery(URL, DeviceID);

        pbLoading.setVisibility(View.VISIBLE);
        tvSplashFooter.setVisibility(View.VISIBLE);
        tvSplashFooter.setText(getString(R.string.msg_loading_data));

        apiCall.enqueue(new Callback<AppValidity>() {
            @Override
            public void onResponse(Call<AppValidity> call, Response<AppValidity> response) {

                pbLoading.setVisibility(View.GONE);
                tvSplashFooter.setText("");
                tvSplashFooter.setVisibility(View.GONE);

                if (response.raw().code() == 200) {
                    APP_VALIDITY = response.body();
                }

                MySettings.setFirstStart(false);
                startDelay();

            }

            @Override
            public void onFailure(Call<AppValidity> call, Throwable t) {
                pbLoading.setVisibility(View.GONE);
                tvSplashFooter.setVisibility(View.GONE);
                tvSplashFooter.setText("");

                MySettings.setFirstStart(false);
                startDelay();
            }
        });

    }


}