package com.example.qmuidemo.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.qmuidemo.R;
import com.example.qmuidemo.ui.adapter.RvTestAdapter;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link Test01Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Test01Fragment extends BaseFragment  {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tv_fragment_tag)
    TextView tvFragmentTag;
    @BindView(R.id.bt_01)
    QMUIRoundButton bt01;
    Unbinder unbinder;
    @BindView(R.id.bt_02)
    QMUIRoundButton bt02;
    Unbinder unbinder1;
    @BindView(R.id.rv_test)
    RecyclerView rvTest;

    private String mParam1;
    private int mParam2;


    public Test01Fragment() {
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
    public static Test01Fragment newInstance(String param1, int param2) {
        Test01Fragment fragment = new Test01Fragment();
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
        RvTestAdapter rvTestAdapter = new RvTestAdapter() {
            {
                for (int i = 0  ; i <= 100 ; i ++){
                    this.addTestData(mParam1 + mParam2 +" item" + i);
                }
            }
        };
        rvTest.setAdapter(rvTestAdapter);
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTest.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_01;
    }

    @Override
    protected void onFragmemntResult(int requestCode, int resultCode, Intent data) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @OnClick({R.id.bt_01, R.id.bt_02, R.id.bt_03})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_01:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.bt_02:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.addToBackStack(null);

                break;
            case R.id.bt_03:
                getActivity().getSupportFragmentManager().popBackStack();
                FragmentTransaction fragmentTransaction1 = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction1.addToBackStack(null);

                break;
        }
    }

}
