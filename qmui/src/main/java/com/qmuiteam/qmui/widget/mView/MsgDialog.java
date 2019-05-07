/**
 *
 */
package com.qmuiteam.qmui.widget.mView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.R;

/**
 * @author L440-9
 */
public class MsgDialog extends Dialog {

    public static final int BUTTON_COLOR_DEFAULT = Color.parseColor("#0179FE");
    public static final int BUTTON_COLOR_HIGH_LIGHT = Color.parseColor("#FE1A01");

    public static class ButtonStyle {
        private int color = BUTTON_COLOR_DEFAULT;
        private Typeface typeface = Typeface.DEFAULT;

        public ButtonStyle(int color, int typeface) {
            Typeface font = Typeface.create(Typeface.DEFAULT, typeface);
            this.color = color;
            this.typeface = font;
        }

        public ButtonStyle(int color, Typeface typeface) {
            this.color = color;
            this.typeface = typeface;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public Typeface getTypeface() {
            return typeface;
        }

        public void setTypeface(Typeface typeface) {
            this.typeface = typeface;
        }
    }

    public MsgDialog(Context context, int theme) {
        super(context, theme);
    }

    public MsgDialog(Context context) {
        super(context);
    }

    public static class Builder {
        // PositiveButton：正极性； NeutralButton：中性按钮；NegativeButton：负极性；

        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private String neutralButtonText;
        private View contentView;

        private ButtonStyle positiveButtonStyle, negativeButtonStyle, neutralButtonStyle;

        private OnClickListener positiveButtonClickListener, negativeButtonClickListener, neutralButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitle(int resId) {
            this.title = context.getString(resId);
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
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

        public Builder setNeutralButton(String neutralButtonText, OnClickListener listener) {
            this.neutralButtonText = neutralButtonText;
            this.neutralButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, ButtonStyle buttonStyle, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            this.positiveButtonStyle = buttonStyle;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, ButtonStyle buttonStyle, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            this.negativeButtonStyle = buttonStyle;
            return this;
        }

        public Builder setNeutralButton(String neutralButtonText, ButtonStyle buttonStyle, OnClickListener listener) {
            this.neutralButtonText = neutralButtonText;
            this.neutralButtonClickListener = listener;
            this.neutralButtonStyle = buttonStyle;
            return this;
        }

        public MsgDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final MsgDialog dialog = new MsgDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_msg, null);
            dialog.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title

            View line = layout.findViewById(R.id.line);
            View line1 = layout.findViewById(R.id.view_line_1);
            View line2 = layout.findViewById(R.id.view_line_2);
            View buttonsContainer = layout.findViewById(R.id.buttons_container);

            TextView positiveButton = (TextView) layout.findViewById(R.id.tv_positive);
            TextView neutralButton = (TextView) layout.findViewById(R.id.tv_neutral);
            TextView negativeButton = (TextView) layout.findViewById(R.id.tv_negative);

            if (title != null) {
                TextView tvTitle = ((TextView) layout.findViewById(R.id.dialog_title));
                tvTitle.setText(title);
                tvTitle.setVisibility(View.VISIBLE);
            }
            if (positiveButtonText != null) {
                positiveButton.setText(positiveButtonText);
                positiveButton.setBackgroundResource(R.drawable.btn_confirm_bg);

                if (positiveButtonStyle != null) {
                    positiveButton.setTextColor(positiveButtonStyle.getColor());
                    positiveButton.setTypeface(positiveButtonStyle.getTypeface());
                }

                if (positiveButtonClickListener != null) {
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                positiveButton.setVisibility(View.GONE);
                line1.setVisibility(View.GONE);
            }

            if (neutralButtonText != null) {
                neutralButton.setText(neutralButtonText);
                neutralButton.setBackgroundResource(R.drawable.btn_middle_bg);
                if (neutralButtonStyle != null) {
                    neutralButton.setTextColor(neutralButtonStyle.getColor());
                    neutralButton.setTypeface(neutralButtonStyle.getTypeface());
                }

                if (positiveButtonText == null) {
                    neutralButton.setBackgroundResource(R.drawable.btn_confirm_bg);
                }

                if (neutralButtonClickListener != null) {
                    neutralButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            neutralButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEUTRAL);
                        }
                    });
                }
            } else {
                neutralButton.setVisibility(View.GONE);
                line2.setVisibility(View.GONE);
            }

            if (negativeButtonText != null) {
                negativeButton.setText(negativeButtonText);
                negativeButton.setBackgroundResource(R.drawable.btn_cancel_bg);
                if (negativeButtonStyle != null) {
                    negativeButton.setTextColor(negativeButtonStyle.getColor());
                    negativeButton.setTypeface(negativeButtonStyle.getTypeface());
                }

                if (negativeButtonClickListener != null) {
                    negativeButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            } else {
                negativeButton.setVisibility(View.GONE);
            }

            // set the content message
            if (contentView != null) {
                // if no message set add the contentView to the dialog body
                ((LinearLayout) layout.findViewById(R.id.dialog_container)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.dialog_container)).addView(contentView,
                        new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            } else if (message != null) {
                ((TextView) layout.findViewById(R.id.dialog_message)).setText(message);
            }

           if (positiveButtonText != null || negativeButtonText != null || neutralButtonText != null) {
                line.setVisibility(View.VISIBLE);
                buttonsContainer.setVisibility(View.VISIBLE);
            }

            // 设置仅一个按钮时背景，需要知道具体的按钮
            if (positiveButtonText != null && negativeButtonText == null && neutralButtonText == null) {
                positiveButton.setBackgroundResource(R.drawable.btn_one_bg);
                line1.setVisibility(View.GONE);
                line2.setVisibility(View.GONE);
            }

            if (negativeButtonText != null && positiveButtonText == null && neutralButtonText == null) {
                negativeButton.setBackgroundResource(R.drawable.btn_one_bg);
                line1.setVisibility(View.GONE);
                line2.setVisibility(View.GONE);
            }

            if (neutralButtonText != null && positiveButtonText == null && negativeButtonText == null) {
                neutralButton.setBackgroundResource(R.drawable.btn_one_bg);
                line1.setVisibility(View.GONE);
                line2.setVisibility(View.GONE);
            }

            dialog.setContentView(layout);

            Window window = dialog.getWindow();
            window.getDecorView().setPadding(0, 0, 0, 0);

            WindowManager m = window.getWindowManager();
            Display defaultDisplay = m.getDefaultDisplay();

            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = (int) (defaultDisplay.getWidth() * 0.90);   //设置宽度的95%
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            window.setAttributes(lp);

            return dialog;
        }

    }

}
