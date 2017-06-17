package com.example.yxy.yxydemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yxy.yxydemo.bean.WeatherBean;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yxy on 2017/3/4 0004.
 */

public class TestTwoActivity extends BaseActivity implements View.OnClickListener {

    Subscription subscription;
    EditText city;
    TextView query;
    TextView weather;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        super.setContentLayout(savedInstanceState);
        setTopBar(R.layout.activity_test_two, "RxjavaDemo1");
    }

    @Override
    public void initData() {
        super.initData();
        query = (TextView) findViewById(R.id.query);
        city = (EditText) findViewById(R.id.city);
        weather = (TextView) findViewById(R.id.weather);
        query.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.query:
                observableAsNormal(city.getText().toString().trim());
                break;
            case R.id.weather:
                break;
        }
    }



//    private void observableAslambda(String city){

//    }

    /*private void observableAsLambda(String city){
        Toast.makeText(TestTwoActivity.this, city, Toast.LENGTH_SHORT).show();
        subscription = Observable.create(subscriber->{
                    if(subscriber.isUnsubscribed()) return;
                    try {
                        String weatherXml = getWeather(city);
                        WeatherBean weather = parseWeather(weatherXml);
                        subscriber.onCompleted();
                    } catch(Exception e){
                        subscriber.onError(e);
                    }
                }
        ).subscribeOn(Schedulers.newThread())    //让Observable运行在新线程中
                .observeOn(AndroidSchedulers.mainThread())   //让subscriber运行在主线程中
                .subscribe(
                        weather->{
                            if(weather != null)
                                this.weather.setText(weather.toString());
                        },
                        e->{
                            Toast.makeText(TestTwoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
    }*/


    /**
     * 采用 普通写法创建Observable
     * @param city
     */
    private void observableAsNormal(final String city) {
        Toast.makeText(TestTwoActivity.this, city, Toast.LENGTH_SHORT).show();
//   使用Observable.create()方法创建Observable对象，需要传入一个参数，即回调接口
//        接口方法中处理网络请求和解析xml的工作
//        最后通过 onNext() onCompleted() onError()通知Subscriber(订阅者)
        subscription = Observable.create(new Observable.OnSubscribe<WeatherBean>() {
            @Override
            public void call(Subscriber<? super WeatherBean> subscriber) {
//                如果已经取消订阅，则直接退出
                if (subscriber.isUnsubscribed())
                    return;

                try {
//                    2.开网络连接请求获取天气预报，返回结果的xml格式
                    String weatherXml = getWeather(city);
//                    解析xml格式，返回weatherBean对象实例
                    WeatherBean weather = parseWeather(weatherXml);
//                    4.发布事件通知订阅者
                    subscriber.onNext(weather);
//                    5.事件通知完成
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
//                    6.出现异常，通知订阅者
                    subscriber.onError(e);
                }

            }
        })
//                若只有 SubscribeOn()方法而没有 ObserveOn方法 则Observable.creat()和subscriber()都运行在subscribeOn所指定的方法中
                .subscribeOn(Schedulers.newThread())    //让Observable运行在新线程中
                .observeOn(AndroidSchedulers.mainThread())   //让subscriber运行在主线程中
//                通过 Observable.subscribe()方法对Observable进行订阅
//                如果不调用 subscribe方法 Observable.creat()方法中的处理不会执行
//如果没有观察者 (Subscribers),Observable是不会发出任何事件
                .subscribe(new Subscriber<WeatherBean>() {
                    @Override
                    public void onCompleted() {
//                事件发布完成后的处理逻辑
//                对应第五条

                    }

                    @Override
                    public void onError(Throwable e) {
//                对应第六条
//                写出现异常后的处理逻辑
                        Toast.makeText(TestTwoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(WeatherBean weatherBean) {
//                对应第四条
//                写获取到某一个事件通知后的处理逻辑
                        if (weatherBean != null) {
                            weather.setText(weatherBean.toString());
                        }

                    }
                });
    }


    private String getWeather(String city) throws Exception {
        BufferedReader reader = null;
        HttpURLConnection connection = null;

        try {
            String urlStr = String.format(Contents.WEATHER_API_URL, URLEncoder.encode(city, "GBK"));
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
//连接
            connection.connect();
//处理返回
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while (!TextUtils.isEmpty(line = reader.readLine()))
                buffer.append(line);
            return buffer.toString();


        } finally {
            if (connection != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    /**
     * 解析xml获取天气情况
     *
     * @param weatherXml
     * @return
     */
    private WeatherBean parseWeather(String weatherXml) {
        //采用Pull方式解析xml
        StringReader reader = new StringReader(weatherXml);
        XmlPullParser xmlParser = Xml.newPullParser();
        WeatherBean weather = null;
        try {
            xmlParser.setInput(reader);
            int eventType = xmlParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        weather = new WeatherBean();
                        break;
                    case XmlPullParser.START_TAG:
                        String nodeName = xmlParser.getName();
                        if ("city".equals(nodeName)) {
                            weather.city = xmlParser.nextText();
                        } else if ("savedate_weather".equals(nodeName)) {
                            weather.date = xmlParser.nextText();
                        } else if ("temperature1".equals(nodeName)) {
                            weather.temperature = xmlParser.nextText();
                        } else if ("temperature2".equals(nodeName)) {
                            weather.temperature += "-" + xmlParser.nextText();
                        } else if ("direction1".equals(nodeName)) {
                            weather.direction = xmlParser.nextText();
                        } else if ("power1".equals(nodeName)) {
                            weather.power = xmlParser.nextText();
                        } else if ("status1".equals(nodeName)) {
                            weather.status = xmlParser.nextText();
                        }
                        break;
                }
                eventType = xmlParser.next();
            }
            return weather;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            reader.close();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
//        subscription.unsubscribe();
    }
}
