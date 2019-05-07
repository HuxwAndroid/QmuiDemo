package com.example.qmuidemo.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class DefinePagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragmentList;
    private List<String>  mTitles;

    protected DefinePagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList = new ArrayList<>();
        mTitles = new ArrayList<>();
    }

    protected DefinePagerAdapter(FragmentManager fm, ArrayList<String> titles) {
        super(fm);
        mFragmentList = new ArrayList<>();
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int arg0) {
        return mFragmentList.get(arg0);
    }

    @Override
    public int getCount() {
        if (mFragmentList != null) {
            return mFragmentList.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup arg0, int arg1) {
        return super.instantiateItem(arg0, arg1);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles != null ? mTitles.get(position) : super.getPageTitle(position);
    }

    public void add(Fragment fragment) {
        mFragmentList.add(fragment);
    }
    public void addTitle(String  title) {
        mTitles.add(title);
    }
}
