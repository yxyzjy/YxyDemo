package com.example.yxy.yxydemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {


    TextView tv_title;
    LinearLayout ll_title_bar_left,ll_empty_content;
    public String TAG = "yxy";
    public Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(savedInstanceState);
        mContext = this;
        initView();
        initData();
        ButterKnife.bind(this);
    }

    public void initView(){
    }

    public void initData(){
    }
    public void setContentLayout(Bundle savedInstanceState) {

    }

    public void setTopBar(int layoutResID, String title) {
        View v = LayoutInflater.from(this).inflate(
                R.layout.activity_base, null);
        initMyView(v);
        RelativeLayout.LayoutParams p1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        p1.addRule(RelativeLayout.BELOW,v.getId());
        View contentView = LayoutInflater.from(this).inflate(layoutResID, null);
        ll_empty_content.addView(contentView, p1);
        setContentView(v);
        tv_title.setText(title);
    }

    public void initMyView(View v) {
        tv_title = (TextView) v.findViewById(R.id.tv_title_bar_center);
        ll_title_bar_left = (LinearLayout) v.findViewById(R.id.ll_title_bar_left);
        ll_empty_content  = (LinearLayout) v.findViewById(R.id.ll_empty_content);
        ll_title_bar_left.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    });
}

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
