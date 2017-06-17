package com.example.yxy.yxydemo.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.yxy.yxydemo.BaseActivity;
import com.example.yxy.yxydemo.R;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class TestFourActivity extends BaseActivity {

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        super.setContentLayout(savedInstanceState);
        setContentView(R.layout.activity_test_four);

//        LinearLayout layout_content = (LinearLayout) findViewById(R.id.layout_content);
//        layout_content.scrollTo(0,0);
    }
}
