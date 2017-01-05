package com.gt.qiezikuaichuan.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gt.qiezikuaichuan.MainApp;

/**
 * Created by 巴巴 on 2017/1/4.
 */

public class ImageLoader {
    public static void loadImage(String path, ImageView imageView){
        Glide.with(MainApp.getAppContext()).load(path).into(imageView);
    }
}
