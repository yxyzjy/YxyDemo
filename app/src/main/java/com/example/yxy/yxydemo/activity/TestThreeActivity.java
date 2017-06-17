package com.example.yxy.yxydemo.activity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.yxy.yxydemo.Api.HttpService;
import com.example.yxy.yxydemo.Api.MyApiEndpointInterface;
import com.example.yxy.yxydemo.BaseActivity;
import com.example.yxy.yxydemo.R;
import com.example.yxy.yxydemo.TestTwoActivity;
import com.example.yxy.yxydemo.bean.RetrofitEntity;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/11 0011.
 */

public class TestThreeActivity extends BaseActivity {

    @Bind(R.id.btn_click)
    Button btnClick;
    String BASE_URL = "http://www.izaodao.com/Api/";

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        super.setContentLayout(savedInstanceState);
        setContentView(R.layout.activity_test_three);
    }

    @OnClick(R.id.btn_click)
    public void onClick() {

        Application application = this.getApplication();
        Intent intent = new Intent(this, TestTwoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);

//        Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
//        getTestOne();
//        getTestTwo();


    }

    public void getTestTwo() {

        Retrofit retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        HttpService httpService = retrofit1.create(HttpService.class);
        Observable<RetrofitEntity> observable = httpService.getAllVedioBy(true);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RetrofitEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG,"onCompleted-->Yes");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"onError-->"+e.toString());
                    }

                    @Override
                    public void onNext(RetrofitEntity retrofitEntity) {
//                        Toast.makeText(mContext,"onNext-->"+retrofitEntity.toString(),Toast.LENGTH_SHORT
//                        ).show();
                        Log.e(TAG,"onNext-->"+retrofitEntity.getData().toString());
                    }
                });

    }

    public void getTestOne() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        MyApiEndpointInterface apiEndpointInterface = retrofit.create(MyApiEndpointInterface.class);
        Call<RetrofitEntity> call = apiEndpointInterface.getAllVedio(true);
        call.enqueue(new Callback<RetrofitEntity>() {
            @Override
            public void onResponse(Call<RetrofitEntity> call, Response<RetrofitEntity> response) {
                RetrofitEntity entity = response.body();
                Log.e(TAG, "onResponse---->" + entity.getMsg());
            }

            @Override
            public void onFailure(Call<RetrofitEntity> call, Throwable t) {

                Log.e(TAG, "onFailure--->" + t.toString());
            }
        });
    }
}
