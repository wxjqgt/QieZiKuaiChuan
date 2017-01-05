package com.gt.qiezikuaichuan;

import android.app.Application;
import android.content.Context;

/**
 * Created by 巴巴 on 2017/1/1.
 */

public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

    }

    public static Context getAppContext(){
        return context;
    }

    private static Context context;

}
