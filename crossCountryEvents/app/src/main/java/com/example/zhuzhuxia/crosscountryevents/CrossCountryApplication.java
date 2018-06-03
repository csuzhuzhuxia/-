package com.example.zhuzhuxia.crosscountryevents;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.zhuzhuxia.crosscountryevents.model.userInfo;
import com.example.zhuzhuxia.crosscountryevents.service.LocationService;

/**
 * Created by zhuzhuxia on 2018/5/21.
 */

public class CrossCountryApplication extends Application {
    private static CrossCountryApplication app;
    private static Context appContext;

//    private static final String ip = "192.168.1.104";
    private static final String ip = "10.0.2.2";

    public LocationService locationService;
    public Vibrator mVibrator;

    private userInfo userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appContext = getApplicationContext();
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());

//        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

    }

    public static Context getContext() {
        return appContext;
    }

    public static CrossCountryApplication getApplication() {
        return app;
    }

    public void setSelfProfile(userInfo userProfile) {
        userInfo = userProfile;
    }

    public userInfo getSelfProfile() {
        return userInfo;
    }


    public static  String getIp() {
        return ip;
    }
}

