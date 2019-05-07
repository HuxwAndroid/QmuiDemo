/**
 *
 */
package com.qmuiteam.qmui.widget.mView;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.R;

/**
 * @author L440-9
 */
public class LoadingDialog extends Dialog {

    private String TAG = "LoadingDialog";

    private LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    public LoadingDialog(Context context) {
        super(context, R.style.Dialog);
        this.context = context;
        initHandler();
        initDialog();
        setCanceledOnTouchOutside(false);
    }

    private void initHandler() {
        //Looper.prepare();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MSG_CHANGE_MESSAGE) {
                    String message = String.valueOf(msg.obj);
                    if (message != null && "null" != message) {
                        loadingMsg.setVisibility(View.VISIBLE);
                        loadingMsg.setText(message);
                    } else {
                        loadingMsg.setVisibility(View.GONE);
                    }
                }
            }
        };
    }

    private static final int MSG_CHANGE_MESSAGE = 1000;

    private Handler mHandler;
    private Context context;
    private TextView loadingMsg;

    public void setMessage(String message) {
        if (loadingMsg != null && mHandler != null) {
            Message msg = new Message();
            msg.what = MSG_CHANGE_MESSAGE;
            msg.obj = message;
            mHandler.sendMessage(msg);
        }
    }

    public void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(context);
        // instantiate the dialog with the custom Theme
        View layout = inflater.inflate(R.layout.progress_dialog, null);
        // set the content message
        loadingMsg = (TextView) layout.findViewById(R.id.tv_loadingMessage);

        this.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        //                    Window window = this.getWindow();
        //                    window.getDecorView().setPadding(0, 0, 0, 0);
        //
        //                    WindowManager m = window.getWindowManager();
        //                    Display defaultDisplay = m.getDefaultDisplay();
        //
        //                    WindowManager.LayoutParams lp = window.getAttributes();
        //                    lp.width = (int)(defaultDisplay.getWidth() * 0.90);   //设置宽度的95%
        //                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //
        //                    window.setAttributes(lp);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (null != mHandler) {
            mHandler.removeMessages(MSG_CHANGE_MESSAGE);
            mHandler = null;
        }
        Log.d(TAG, "LoadingDialog dismiss...");
    }
}
