package com.qmuiteam.qmui.widget.mView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.R;

/***
 * 停车记录的照片展示
 */
public class ParkPhotoViewGroup extends HorizontalScrollView {

    private Context mContext;
    // horizeontalScrollView 下面的 子布局
    private LinearLayout layout;
    // imageView个数
    private int num = 3;
    // 宽
    private float imageWidth;
    // 高
    private float imageHeight;
    // 图片间距
    private float imageBetween;

    private onImageViewClickEvent clickEvent;

    private ImageView[] imageView;

    public ParkPhotoViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public ParkPhotoViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParkPhotoViewGroup(Context context) {
        this(context, null);
    }

    // 初始化参数
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.photoView, defStyleAttr, 0);
        imageBetween = array.getDimension(R.styleable.photoView_imageview_margin_between,
                DensityUtil.dip2px(mContext, 20));
        imageWidth = array.getDimension(R.styleable.photoView_imageview_width, DensityUtil.dip2px(mContext, 60));
        imageHeight = array.getDimension(R.styleable.photoView_imageview_height, DensityUtil.dip2px(mContext, 60));
        array.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (layout == null) {
            layout = new LinearLayout(mContext);
            //
            layout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    (int) imageHeight);
            layout.setLayoutParams(params);
            addView(layout);
        }
        addImageViewIntoViewGroup();
    }

    // 初始話imageView的个数
    private void initImageView() {
        // 目前layout中有的imageView数量
        int nowCount = 0;
        if (layout != null) {
            nowCount = layout.getChildCount();
        }
        // 子view 数量小于 num
        if (nowCount == 0) {
            imageView = new ImageView[num];
            for (int i = nowCount; i < num; i++) {
                ImageView view = new ImageView(mContext);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) imageWidth,
                        (int) imageHeight);
                if (i != 0) {
                    layoutParams.leftMargin = (int) imageBetween;
                }
                view.setLayoutParams(layoutParams);
                view.setImageResource(R.drawable.btn_cancel_bg);
//                view.setBackgroundColor(new Color().parseColor("#DDDDDD"));
                final int index = i;
                // 设置imageView的点击事件
                if (clickEvent != null) {
                    view.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            clickEvent.imageViewClick(index);
                        }
                    });
                }
                imageView[i] = view;
            }
        }
    }

    // 添加imageview到布局中
    private void addImageViewIntoViewGroup() {
        if (layout != null) {
            int nowCount = layout.getChildCount();
            // 只添加一次子view
            if (nowCount == 0) {
                for (int i = 0; i < imageView.length; i++) {
                    layout.addView(imageView[i]);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    // 根据index获取对应的imageView
    public ImageView getImageViewByIndex(int index) {
        try {
            return imageView[index];
        } catch (Exception e) {

        }
        return null;
    }

    public ImageView[] getimaImageViewArray() {
        return imageView;
    }

    public int getNum() {
        return num;
    }

    // 设置imageView的个数
    public void setNum(int num) {
        this.num = num;
        initImageView();
    }

    public onImageViewClickEvent getClickEvent() {
        return clickEvent;
    }

    // 子ImageView的点击事件的回调 setNum前调用
    public void setClickEvent(onImageViewClickEvent clickEvent) {
        this.clickEvent = clickEvent;
    }

    // 子ImageView的点击事件的回调
    public interface onImageViewClickEvent {
        void imageViewClick(int i);
    }

}
