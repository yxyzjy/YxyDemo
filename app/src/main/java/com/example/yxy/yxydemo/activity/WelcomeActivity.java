package com.example.yxy.yxydemo.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.example.yxy.yxydemo.BaseActivity;
import com.example.yxy.yxydemo.R;

/**
 * 闪屏页也叫启动页  在里面可以做一些初识操作
 * eg：地图定位 加载本地数据库 获取版本信息等
 */
public class WelcomeActivity extends BaseActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        super.setContentLayout(savedInstanceState);
        setTopBar(R.layout.activity_welcome,"welcome");

        Handler handler = new Handler();
        //页面停留时间  开发中可以在初始化操作结束后跳转 还可以设置页面停留最长时间限制
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
            }
        },2000);
    }
}
