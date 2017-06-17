package com.example.yxy.yxydemo.Api;

import com.example.yxy.yxydemo.bean.RetrofitEntity;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/11 0011.
 */

public interface MyApiEndpointInterface {


    @POST("AppFiftyToneGraph/videoLink")
    Call<RetrofitEntity> getAllVedio(@Body boolean once_no);
}
