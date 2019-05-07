package com.example.qmuidemo.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.qmuidemo.R;
import com.example.qmuidemo.ui.adapter.DefinePagerAdapter;
import com.example.qmuidemo.ui.fragment.Test02Fragment;

import butterknife.BindView;

public class VpFgActivity extends BaseActivity {
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.tbl_vg)
    TabLayout tblVg;

    @Override
    protected void initData() {
        DefinePagerAdapter definePagerAdapter = new DefinePagerAdapter(getSupportFragmentManager()) {
            {
                for (int i = 0 ; i <= 3  ; i ++){
                    this.add(Test02Fragment.newInstance("我是fragment", i));
                    this.addTitle( i +"");
                }
            }
        };

        vpMain.setAdapter(definePagerAdapter);
        vpMain.setOffscreenPageLimit(0);
        tblVg.setupWithViewPager(vpMain);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vg_fg;
    }


}
