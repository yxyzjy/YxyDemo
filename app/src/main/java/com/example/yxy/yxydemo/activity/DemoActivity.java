package com.example.yxy.yxydemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yxy.yxydemo.R;

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
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class DemoActivity extends ActionBarActivity implements View.OnClickListener, View.OnTouchListener {
    /**
     * 天气预报API地址
     */
    private static final String WEATHRE_API_URL="http://php.weather.sina.com.cn/xml.php?city=%s&password=DJOYnieT8234jlsK&day=0";
    private EditText cityET;     //城市
    private TextView queryTV;    //查询按钮
    private TextView weatherTV;  //天气结果

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_two);
        //获取控件实例
        cityET = (EditText) findViewById(R.id.city);
        queryTV = (TextView) findViewById(R.id.query);
        weatherTV = (TextView) findViewById(R.id.weather);
        //对查询按钮侦听点击事件
        queryTV.setOnClickListener(this);
        weatherTV.setOnTouchListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.query){
            weatherTV.setText("");
            String city = cityET.getText().toString();
            if(TextUtils.isEmpty(city)){
                Toast.makeText(this, "城市不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }
            //采用普通写法创建Observable
            observableAsNormal(city);
            //采用lambda写法创建Observable
//            observableAsLambda(city);
            //采用普通写法创建Observable,使用map操作符转换
//            observableMapAsNormal(city);
            //采用lambda写法创建Observable,使用map操作符转换
//            observableMapAsLambda(city);
        }
    }

    /**
     * 采用普通写法创建Observable
     * @param city
     */
    private void observableAsNormal(final String city){
        subscription = Observable.create(new Observable.OnSubscribe<Weather>() {
            @Override
            public void call(Subscriber<? super Weather> subscriber) {
                //1.如果已经取消订阅，则直接退出
                if(subscriber.isUnsubscribed()) return;
                try {
                    //2.开网络连接请求获取天气预报，返回结果是xml格式
                    String weatherXml = getWeather(city);
                    //3.解析xml格式，返回weather实例
                    Weather weather = parseWeather(weatherXml);
                    //4.发布事件通知订阅者
                    subscriber.onNext(weather);
                    //5.事件通知完成
                    subscriber.onCompleted();
                } catch(Exception e){
                    //6.出现异常，通知订阅者
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread())    //让Observable运行在新线程中
                .observeOn(AndroidSchedulers.mainThread())   //让subscriber运行在主线程中
                .subscribe(new Subscriber<Weather>() {
                    @Override
                    public void onCompleted() {
                        //对应上面的第5点：subscriber.onCompleted();
                        //这里写事件发布完成后的处理逻辑

                    }

                    @Override
                    public void onError(Throwable e) {
                        //对应上面的第6点：subscriber.onError(e);
                        //这里写出现异常后的处理逻辑
                        Toast.makeText(DemoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Weather weather) {
                        //对应上面的第4点：subscriber.onNext(weather);
                        //这里写获取到某一个事件通知后的处理逻辑
                        if(weather != null)
                            weatherTV.setText(weather.toString());
                    }
                });
    }

    /**
     * 采用lambda写法创建Observable
     * @param city
     */
    /*private void observableAsLambda(String city){
        subscription = Observable.create(subscriber->{
                    if(subscriber.isUnsubscribed()) return;
                    try {
                        String weatherXml = getWeather(city);
                        Weather weather = parseWeather(weatherXml);
                        subscriber.onNext(weather);
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
                                weatherTV.setText(weather.toString());
                        },
                        e->{
                            Toast.makeText(BaseActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
    }*/

    /**
     * 采用普通写法创建Observable,使用map操作符转换
     * @param city
     */
    private void observableMapAsNormal(final String city){
        subscription = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if(subscriber.isUnsubscribed()) return;
                try {
                    String weatherXml = getWeather(city);
                    subscriber.onNext(weatherXml);
                    subscriber.onCompleted();
                } catch(Exception e){
                    subscriber.onError(e);
                }
            }
        }).map(new Func1<String, Weather>() {
            @Override
            public Weather call(String weatherXml) {
                return parseWeather(weatherXml);
            }
        }).subscribeOn(Schedulers.newThread())    //让Observable运行在新线程中
                .observeOn(AndroidSchedulers.mainThread())   //让subscriber运行在主线程中
                .subscribe(new Subscriber<Weather>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(DemoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Weather weather) {
                        if(weather != null)
                            weatherTV.setText(weather.toString());
                    }
                });
    }

    /**
     * 采用lambda写法创建Observable,使用map操作符转换
     */
   /* private void observableMapAsLambda(String city){
        subscription = Observable.create(subscriber->{
                    if(subscriber.isUnsubscribed()) return;
                    try {
                        String weatherXml = getWeather(city);
                        subscriber.onNext(weatherXml);
                        subscriber.onCompleted();
                    } catch(Exception e){
                        subscriber.onError(e);
                    }
                }
        ).map(weatherXml->parseWeather(weatherXml.toString()))
                .subscribeOn(Schedulers.newThread())    //让Observable运行在新线程中
                .observeOn(AndroidSchedulers.mainThread())   //让subscriber运行在主线程中
                .subscribe(
                        weather->{
                            if(weather != null)
                                weatherTV.setText(weather.toString());
                        },
                        e->{
                            Toast.makeText(BaseActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
    }*/

    @Override
    protected void onDestroy() {
        //取消订阅
        if(subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v == weatherTV && event.getAction() == MotionEvent.ACTION_DOWN){
            //隐藏软键盘
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            View focusedView = getCurrentFocus();
            if(focusedView!=null && focusedView.getWindowToken()!=null){
                manager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
            }
        }
        return true;
    }

    /**
     * 天气情况类
     */
    private class Weather{
        /**
         * 城市
         */
        String city;
        /**
         * 日期
         */
        String date;
        /**
         * 温度
         */
        String temperature;
        /**
         * 风向
         */
        String direction;
        /**
         * 风力
         */
        String power;
        /**
         * 天气状况
         */
        String status;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("城市:" + city + "\r\n");
            builder.append("日期:" + date + "\r\n");
            builder.append("天气状况:" + status + "\r\n");
            builder.append("温度:" + temperature + "\r\n");
            builder.append("风向:" + direction + "\r\n");
            builder.append("风力:" + power + "\r\n");
            return builder.toString();
        }
    }

    /**
     * 解析xml获取天气情况
     * @param weatherXml
     * @return
     */
    private Weather parseWeather(String weatherXml){
        //采用Pull方式解析xml
        StringReader reader = new StringReader(weatherXml);
        XmlPullParser xmlParser = Xml.newPullParser();
        Weather weather = null;
        try {
            xmlParser.setInput(reader);
            int eventType = xmlParser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        weather = new Weather();
                        break;
                    case XmlPullParser.START_TAG:
                        String nodeName = xmlParser.getName();
                        if("city".equals(nodeName)){
                            weather.city = xmlParser.nextText();
                        } else if("savedate_weather".equals(nodeName)){
                            weather.date = xmlParser.nextText();
                        } else if("temperature1".equals(nodeName)) {
                            weather.temperature = xmlParser.nextText();
                        } else if("temperature2".equals(nodeName)){
                            weather.temperature += "-" + xmlParser.nextText();
                        } else if("direction1".equals(nodeName)){
                            weather.direction = xmlParser.nextText();
                        } else if("power1".equals(nodeName)){
                            weather.power = xmlParser.nextText();
                        } else if("status1".equals(nodeName)){
                            weather.status = xmlParser.nextText();
                        }
                        break;
                }
                eventType = xmlParser.next();
            }
            return weather;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            reader.close();
        }
    }

    /**
     * 获取指定城市的天气情况
     * @param city
     * @return
     * @throws
     */
    private String getWeather(String city) throws Exception{
        BufferedReader reader = null;
        HttpURLConnection connection=null;
        try {
            String urlString = String.format(WEATHRE_API_URL, URLEncoder.encode(city, "GBK"));
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            //连接
            connection.connect();

            //处理返回结果
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line="";
            while(!TextUtils.isEmpty(line = reader.readLine()))
                buffer.append(line);
            return buffer.toString();
        } finally {
            if(connection != null){
                connection.disconnect();
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
