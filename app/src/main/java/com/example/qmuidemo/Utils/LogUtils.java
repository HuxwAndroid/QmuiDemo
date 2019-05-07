package com.example.qmuidemo.Utils;

import android.util.Log;

/**
 * 统一的打印日志入口，便于后面维护
 */
public class LogUtils {
    // 日志开关，默认打开
    private static boolean isDebug = true;

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Object... args) {
        if (isDebug)
            Log.d(tag, String.format(msg, args));
    }

    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (isDebug)
            Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void e(String tag, Throwable ex) {
        if (isDebug) {
            Log.e(tag, "LogUtil", ex);
        }
    }

    public static void info(String msg) {
        if (isDebug)
            Log.e("LogUtil", msg);
    }
}
