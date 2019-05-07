package com.qmuiteam.qmui.widget.mView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.R;

/**
 * Created by Thinkpad on 2017/11/30.
 */

public class CheckBox extends LinearLayout {

    protected final String NAME_SPACE = "http://schemas.android.com/apk/res/android";

    public int mDefaultSize;

    private TextView mTextView;
    private CheckView mCheckView;

    private OnCheckedChangeListener mListener;

    private boolean isInterceptEvent = false;

    public CheckBox(Context context) {
        this(context, null);
    }

    public CheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        mDefaultSize = dp2px(context, 10);
        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {
        boolean clickable = attrs.getAttributeBooleanValue(NAME_SPACE, "clickable", true);
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setClickable(clickable);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CheckBox);
        String text = ta.getString(R.styleable.CheckBox_text);
        int textColor = ta.getColor(R.styleable.CheckBox_textColor, Color.BLACK);
        float textSize = ta.getDimension(R.styleable.CheckBox_textSize, dp2px(context, 17));
        int middlePadding = ta.getDimensionPixelOffset(R.styleable.CheckBox_middlePadding, mDefaultSize);
        int checkBoxWidth = ta.getDimensionPixelOffset(R.styleable.CheckBox_checkBoxWidth, mDefaultSize * 2);
        int checkBoxHeight = ta.getDimensionPixelOffset(R.styleable.CheckBox_checkBoxHeight, mDefaultSize * 2);
        ta.recycle();

        LayoutParams mCheckParams = new LayoutParams(checkBoxWidth, checkBoxHeight);
        mCheckView = new CheckView(context, attrs);
        mCheckView.setClickable(true);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mTextView = new TextView(context);
        params.leftMargin = middlePadding;
        mTextView.setLayoutParams(params);
        mTextView.setText(text);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mTextView.setTextColor(textColor);
        if (text == null || text.length() == 0) {
            params.leftMargin = 0;
        }

        addView(mCheckView, mCheckParams);
        addView(mTextView, params);

        if (!isClickable()) return;

        mCheckView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox.this.performClick();
            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckView.toggle();
                if (mListener != null) {
                    mListener.onCheckedChanged(CheckBox.this, mCheckView.isChecked());
                }
            }
        });
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
//        int defaultPadding = dp2px(getContext(), 10);
//        if (left == 0) left = defaultPadding;
//        if (top == 0) top = defaultPadding;
//        if (right == 0) right = defaultPadding;
//        if (bottom == 0) bottom = defaultPadding;
        super.setPadding(left, top, right, bottom);
    }

    public int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    //    public void toggle() {
    //        toggle(false);
    //    }

    public boolean isChecked() {
        return mCheckView.isChecked();
    }

    public void setChecked(boolean checked) {
        mCheckView.setChecked(checked);
        if (mListener != null) {
            mListener.onCheckedChanged(this, mCheckView.isChecked());
        }
    }

    //    public void setCheckedColor(int checkedColor) {
    //        mCheckView.setCheckedColor(checkedColor);
    //    }

    public void setShape(int shape) {
        mCheckView.setShape(shape);
    }

    public void setText(CharSequence text) {
        mTextView.setText(text);
    }

    public String getText(String text) {
        return mTextView.getText().toString();
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.mListener = listener;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(CheckBox checkBox, boolean isChecked);
    }

    public boolean isInterceptEvent() {
        return isInterceptEvent;
    }

    public void setInterceptEvent(boolean interceptEvent) {
        isInterceptEvent = interceptEvent;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isInterceptEvent) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }
}