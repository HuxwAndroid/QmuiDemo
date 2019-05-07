package com.example.qmuidemo.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;
import com.example.qmuidemo.App;
import com.example.qmuidemo.R;


/**
 * Toast 提示
 * UI线程/非UI线程均可调用 显示 Toast
 */
public class ToastUtils {
    private static Toast toast = null;

    /**
     * 居中显示toast
     * @param str
     */
    public static void showToast(String str) {
        showToast(App.getAppContext(), str, Gravity.CENTER);
    }

    /**
     * 自定义toast显示位置
     * @param str
     * @param gravity
     */
    public static void showToast(String str , int gravity) {
        showToast(App.getAppContext(), str, gravity);
    }

    /**
     * 自定义toast位置并设置x，y偏移量
     * @param str
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    public static void showToast(String str , int gravity , int xOffset , int yOffset) {
        showToast(App.getAppContext(), str, gravity ,xOffset ,yOffset);
    }
    private static void showToast(final Context context, final String str, final int gravity) {
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
            TextView toastView = (TextView) LayoutInflater.from(context).inflate(R.layout.toast_view, null);
            toastView.setText(str);
            toast.setView(toastView);
            if (gravity == Gravity.BOTTOM) {
                toast.setGravity(gravity, 0, 100);
            } else {
                toast.setGravity(gravity, 0, 0);
            }
            toast.show();
        } else {
            toast.cancel();
            toast = null;
            showToast(context, str, gravity);
        }
    }
    private static void showToast(final Context context, final String str, final int gravity , final int xOffset ,final int yOffset) {
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
            TextView toastView = (TextView) LayoutInflater.from(context).inflate(R.layout.toast_view, null);
            toastView.setText(str);
            toast.setView(toastView);
            toast.setGravity(gravity, xOffset, yOffset);
            toast.show();
        } else {
            toast.cancel();
            toast = null;
            showToast(context, str, gravity,xOffset ,yOffset);
        }
    }
}
