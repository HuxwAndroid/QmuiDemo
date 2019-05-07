package com.example.qmuidemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.example.qmuidemo.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    private FrameLayout content;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        content = (FrameLayout)findViewById(R.id.content);
        if (getLayoutId() > 0) {
            View childView = getLayoutInflater().inflate(getLayoutId(), null, false);
            if (childView.getParent() != content) {
                content.addView(childView);
            }
        }
        unbinder = ButterKnife.bind(this);
        initView();
        initData() ;
    }

    @Override
    protected void onResume() {
//        ToastUtils.showToast(getClass().getSimpleName());
        super.onResume();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
