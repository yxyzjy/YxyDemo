package com.example.yxy.yxydemo.Api;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/11 0011.
 */

public class SubjectPostApi extends BaseApi {
    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpService service = retrofit.create(HttpService.class);
        return service.getAllVedioBy(true);
    }
}
