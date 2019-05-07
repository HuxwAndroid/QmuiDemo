package com.example.qmuidemo.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.qmuidemo.R;
import com.example.qmuidemo.Utils.LogUtils;
import com.example.qmuidemo.ui.adapter.RvTestAdapter;

import butterknife.BindView;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link Test02Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Test02Fragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tv_fragment_tag)
    TextView tvFragmentTag;
    @BindView(R.id.rv_test)
    RecyclerView rvTest;
    Unbinder unbinder;


    private String mParam1;
    private int mParam2;


    public Test02Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Test01Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Test02Fragment newInstance(String param1, int param2) {
        Test02Fragment fragment = new Test02Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }


    @Override
    protected void initData() {
        tvFragmentTag.setText(mParam1 + mParam2);
        rvTest.setAdapter(new RvTestAdapter(){
            {
                for (int i = 0  ; i <= 500 ; i ++){
                    this.addTestData(mParam1 + mParam2 +" item" + i);
                }
            }
        });
    }

    @Override
    protected void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity() ,4);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTest.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_02;
    }

    @Override
    protected void onFragmemntResult(int requestCode, int resultCode, Intent data) {

    }


    @Override
    public void onDestroyView() {
        LogUtils.d(mParam1 , mParam2 +"");
        super.onDestroyView();
        if (unbinder!= null){
            unbinder.unbind();
        }
    }

    @Override
    public void onStart() {
        LogUtils.d(mParam1 , mParam2 +"");
        super.onStart();
    }

    @Override
    public void onResume() {
        LogUtils.d(mParam1 , mParam2 +"");
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtils.d(mParam1 , mParam2 +"");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtils.d(mParam1 , mParam2 +"");
        super.onStop();
    }

    @Override
    public void onDetach() {
        LogUtils.d(mParam1 , mParam2 +"");
        super.onDetach();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        LogUtils.d(mParam1 , mParam2 +"");
        super.setUserVisibleHint(isVisibleToUser);
    }
}
