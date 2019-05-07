package com.example.qmuidemo.ui.adapter;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qmuidemo.R;

import java.util.ArrayList;

public class RvTestAdapter extends BaseQuickAdapter<String ,BaseViewHolder> {

    protected RvTestAdapter() {
        super(R.layout.item_test);
        mData = new ArrayList<String>();
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_test , item);

    }

    public void addTestData(@NonNull String data) {
       mData.add(data);
    }
}
