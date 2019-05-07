package com.example.qmuidemo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qmuidemo.Utils.ToastUtils;
import com.example.qmuidemo.helper.BackHandlerHelper;
import com.example.qmuidemo.helper.FragmentBackHandler;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements FragmentBackHandler {
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected  abstract  void onFragmemntResult(int requestCode, int resultCode, Intent data);

    @Override
    public void onResume() {
        ToastUtils.showToast(getClass().getSimpleName());
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public boolean onBackPressed() {
         return BackHandlerHelper.handleBackPress(this);
    }
}
