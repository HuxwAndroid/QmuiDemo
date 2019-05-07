package com.example.qmuidemo.ui.activity;

import android.view.View;
import android.widget.TextView;
import com.example.qmuidemo.R;
import com.qmuiteam.qmui.DialogStyle;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.mView.MsgDialog;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick({R.id.bt_01 ,R.id.bt_02 ,R.id.bt_03})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_01:
                new QMUIDialog.CheckBoxMessageDialogBuilder(this,R.drawable.qmui_m_checkbox )
                        .setTitle("退出后是否删除账号信息?")
                        .setMessage("删除账号信息")
                        .setChecked(true)
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .addAction("退出" , new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .create(DialogStyle.qumiStyle).show();
                break;
            case R.id.bt_02:
                msgDialog();
                break;
            case R.id.bt_03:
                new QMUIDialog.CheckBoxMessageDialogBuilder(this,R.drawable.qmui_m_checkbox )
                        .setTitle("退出后是否删除账号信息?")
                        .setMessage("删除账号信息")
                        .setChecked(true)
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .addAction("退出" , "#ff0000",new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .create(DialogStyle.mStyle).show();
                break;
        }
    }
    public void msgDialog()
    {
        TextView textView = new TextView(this);
        textView.setText("test");


        MsgDialog msgDialog = new MsgDialog.Builder(this)
                .setTitle("Title")
                .setPositiveButton("OK",null)
                .setContentView(textView)
                .setMessage("Message").create();

        msgDialog.show();
    }
    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }
}
