package com.example.zhuzhuxia.crosscountryevents.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zhuzhuxia.crosscountryevents.CrossCountryApplication;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zhuzhuxia on 2018/5/21.
 */

public class ImgUtils {
    public static void load(String url, ImageView targetView) {
        Glide.with(CrossCountryApplication.getContext())
                .load(url)
                .into(targetView);
    }

    public static void load(int resId, ImageView targetView) {
        Glide.with(CrossCountryApplication.getContext())
                .load(resId)
                .into(targetView);
    }

    public static void loadRound(String url, ImageView targetView) {
        Glide.with(CrossCountryApplication.getContext())
                .load(url)
                .bitmapTransform(new CropCircleTransformation(CrossCountryApplication.getContext()))
                .into(targetView);
    }

    public static void loadRound(int resId, ImageView targetView) {
        Glide.with(CrossCountryApplication.getContext())
                .load(resId)
                .bitmapTransform(new CropCircleTransformation(CrossCountryApplication.getContext()))
                .into(targetView);
    }
}
