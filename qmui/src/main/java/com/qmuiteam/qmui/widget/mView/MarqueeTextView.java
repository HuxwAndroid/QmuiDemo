package com.qmuiteam.qmui.widget.mView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Thinkpad on 2017/12/4.
 */

public class MarqueeTextView extends AppCompatTextView {
    public MarqueeTextView(Context context) {
        super(context);
        initView();
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        setSingleLine(true);//因为文字只能显示一行，在一行内实现跑马灯。所以设置属性单行模式
    }

    //跑马灯属性只有在获得焦点的情况下才有动画。
    //所以，这里我们让这个textview自动获得焦点
    @Override
    public boolean isFocused() {
        return true;
    }

    //还可以设置文本中的图片，类似通知跑马灯前的小喇叭图标
    public void getLeftDrawable(Drawable leftDrawable) {
        setCompoundDrawables(leftDrawable, null, null, null);
    }
}
