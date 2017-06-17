package com.example.yxy.yxydemo.activity;

import android.content.Intent;
import android.icu.util.TimeUnit;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.example.yxy.yxydemo.BaseActivity;
import com.example.yxy.yxydemo.R;
import com.example.yxy.yxydemo.TestTwoActivity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class WelcomeActivity extends BaseActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        super.setContentLayout(savedInstanceState);
        setTopBar(R.layout.activity_welcome,"welcome");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(mContext, TestFourActivity.class));
                finish();
            }
        },2000);
    }
}
