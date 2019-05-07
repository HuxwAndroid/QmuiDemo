/**
 *
 */
package com.qmuiteam.qmui.widget.mView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.qmuiteam.qmui.R;

/**
 * @author L440-9
 */
public class InputDialog extends Dialog {

    public interface Callback {
        public void onClick(DialogInterface dialog, int which, EditText editText);
    }

    public InputDialog(Context context, int theme) {
        super(context, theme);
    }

    public InputDialog(Context context) {
        super(context);
    }

    private static TextView tipsView;

    public void setTips(String tips) {
        if (tipsView != null) {
            if (tips != null) {
                tipsView.setText(tips);
                tipsView.setVisibility(View.VISIBLE);
            } else {
                tipsView.setText(tips);
                tipsView.setVisibility(View.VISIBLE);
            }
        }
    }

    public static class Builder {
        // PositiveButton：正极性； NeutralButton：中性按钮；NegativeButton：负极性；

        private Context context;
        private String title;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private String hint;
        private String defaultValue;
        private String tips;
        private boolean singleLine;
        private boolean numberFlag;
        private OnClickListener positiveButtonClickListener, negativeButtonClickListener;
        private Callback positiveButtonCallBack, negativeButtonCallback;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public Builder setHint(String hint) {
            this.hint = hint;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setSingleLine(boolean singleLine) {
            this.singleLine = singleLine;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, Callback callback) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonCallBack = callback;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, Callback callback) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonCallback = callback;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setTips(String tips) {
            this.tips = tips;
            return this;
        }
        public Builder setNumber(boolean flag)
        {
            this.numberFlag = flag;
            return  this;
        }

        public InputDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final InputDialog dialog = new InputDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_input, null);
            dialog.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title

            View line1 = layout.findViewById(R.id.view_line_1);

            TextView positiveButton = (TextView) layout.findViewById(R.id.tv_positive);
            final EditText editText = (EditText) layout.findViewById(R.id.edit_text);
            TextView negativeButton = (TextView) layout.findViewById(R.id.tv_negative);
            tipsView = (TextView) layout.findViewById(R.id.tv_tips);

            if (singleLine) {
                editText.setSingleLine(true);
            }
            if(numberFlag)
            {
                String digists = ".0123456789";
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setKeyListener(DigitsKeyListener.getInstance(digists));
            }

            if (defaultValue != null) {
                editText.setText(defaultValue);
                editText.setSelection(defaultValue.length());
            }
            if (hint != null) {
                editText.setHint(hint);
            }

            if (tips == null) {
                tipsView.setText("");
                tipsView.setVisibility(View.GONE);
            } else {
                tipsView.setText(tips);
                tipsView.setVisibility(View.VISIBLE);
            }

            ((TextView) layout.findViewById(R.id.dialog_title)).setText(title);
            if (positiveButtonText != null) {
                positiveButton.setText(positiveButtonText);
                positiveButton.setBackgroundResource(R.drawable.btn_confirm_bg);
                if (positiveButtonClickListener != null) {
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }

                if (positiveButtonCallBack != null) {
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonCallBack.onClick(dialog, DialogInterface.BUTTON_POSITIVE,
                                    editText);
                        }
                    });
                }
            } else {
                positiveButton.setVisibility(View.GONE);
                line1.setVisibility(View.GONE);
            }

            if (negativeButtonText != null) {
                negativeButton.setText(negativeButtonText);
                negativeButton.setBackgroundResource(R.drawable.btn_cancel_bg);

                if (negativeButtonClickListener != null) {
                    negativeButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }

                if (negativeButtonCallback != null) {
                    negativeButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonCallback.onClick(dialog, DialogInterface.BUTTON_NEGATIVE,
                                    editText);
                        }
                    });
                }
            } else {
                negativeButton.setVisibility(View.GONE);
            }

            if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                // ((LinearLayout)
                // layout.findViewById(R.id.content)).removeAllViews();
                // ((LinearLayout)
                // layout.findViewById(R.id.content)).addView(contentView, new
                // LayoutParams(LayoutParams.WRAP_CONTENT,
                // LayoutParams.WRAP_CONTENT));
            }

            // 设置仅一个按钮时背景，需要知道具体的按钮
            if (positiveButtonText != null && negativeButtonText == null) {
                positiveButton.setBackgroundResource(R.drawable.btn_one_bg);
                line1.setVisibility(View.GONE);
            }

            if (negativeButtonText != null && positiveButtonText == null) {
                negativeButton.setBackgroundResource(R.drawable.btn_one_bg);
                line1.setVisibility(View.GONE);
            }

            dialog.setContentView(layout);

            Window window = dialog.getWindow();
            window.getDecorView().setPadding(0, 0, 0, 0);

            WindowManager m = window.getWindowManager();
            Display defaultDisplay = m.getDefaultDisplay();

            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = (int) (defaultDisplay.getWidth() * 0.90); // 设置宽度的95%
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            window.setAttributes(lp);

            return dialog;
        }

    }



}
