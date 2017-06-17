package com.example.yxy.yxydemo.bean;

/**
 * Created by Administrator on 2017/3/4 0004.
 */

public class WeatherBean {

    public String city,date,temperature,direction,power,status;

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
