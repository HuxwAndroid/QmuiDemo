package com.example.qmuidemo;

import android.app.Application;
import android.content.Context;

import com.qmuiteam.qmui.InitUi;


/**
 * 全局应用程序类
 */
public class App extends Application {

    private static Context mApplication;
    private static App sInstance = null;

    public App() {
        super();
        sInstance = this;
    }

    public static App instance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        InitUi.initThemeColor("#008577");
    }


    public static Context getAppContext() {
        if (mApplication != null) {
            return mApplication.getApplicationContext();
        }
        throw new NullPointerException("you should initialize first");
    }

}
