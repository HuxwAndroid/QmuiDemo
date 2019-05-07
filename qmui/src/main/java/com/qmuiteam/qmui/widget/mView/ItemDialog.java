/**
 *
 */
package com.qmuiteam.qmui.widget.mView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qmuiteam.qmui.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @author L440-9
 */
public class ItemDialog extends Dialog {

    private Context context;

    public ItemDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public ItemDialog(Context context) {
        super(context);
        this.context = context;
    }

    public enum ChoiceMode {
        ITEM,
        MULTI,
        SINGLE
    }


    public static class Builder {
        // PositiveButton：正极性； NeutralButton：中性按钮；NegativeButton：负极性；
        Map<Integer, Boolean> selectedItems = new HashMap<Integer, Boolean>();
        private ChoiceMode multiChoice = ChoiceMode.ITEM;
        private Context context;
        private String title;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private View dialogContainer;
        private View btnContainer;

        private OnClickListener positiveButtonClickListener, negativeButtonClickListener;

        private OnClickListener listViewOnClickListener;
        private OnMultiChoiceClickListener multiChoiceClickListener;
        private String[] items;
        private boolean[] checkedItems;
        private int checkedItem;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
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

        public Builder setMultiChoiceItems(String[] items, boolean[] checkedItems, final OnMultiChoiceClickListener listener) {
            this.multiChoiceClickListener = listener;
            this.items = items;
            this.checkedItems = checkedItems;
            this.multiChoice = ChoiceMode.MULTI;
            return this;
        }

        public Builder setSingleChoiceItems(String[] items, int checkedItem, final OnClickListener listener) {
            this.listViewOnClickListener = listener;
            this.items = items;
            this.multiChoice = ChoiceMode.SINGLE;
            this.checkedItem = checkedItem;
            return this;
        }

        public Builder setItems(String[] items, final OnClickListener listener) {
            this.listViewOnClickListener = listener;
            this.items = items;
            this.multiChoice = ChoiceMode.ITEM;
            return this;
        }


        public ItemDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final ItemDialog dialog = new ItemDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_list, null);
            dialog.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title

            View line1 = layout.findViewById(R.id.view_line_1);
            dialogContainer = layout.findViewById(R.id.dialog_container);
            btnContainer = layout.findViewById(R.id.btn_container);
            TextView titleView = (TextView) layout.findViewById(R.id.dialog_title);
            TextView positiveButton = (TextView) layout.findViewById(R.id.tv_positive);
            final ListView listView = (ListView) layout.findViewById(R.id.list_view);
            TextView negativeButton = (TextView) layout.findViewById(R.id.tv_negative);

            if (title != null) {
                titleView.setText(title);
                titleView.setVisibility(View.VISIBLE);
            }
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

            } else {
                positiveButton.setVisibility(View.GONE);
                line1.setVisibility(View.GONE);
            }

            if (negativeButtonText != null) {
                negativeButton.setText(negativeButtonText);
                negativeButton.setBackgroundResource(R.drawable.btn_cancel_bg);
                btnContainer.setVisibility(View.VISIBLE);
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

            if (multiChoice == ChoiceMode.MULTI) {
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_multiple_choice, items) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        if (checkedItems != null) {
                            boolean isItemChecked = checkedItems[position];
                            if (isItemChecked) {
                                listView.setItemChecked(position, true);
                                selectedItems.put(position, true);
//                                if (multiChoiceClickListener != null) {
//                                    multiChoiceClickListener.onClick(dialog, position, true);
//                                }
                            }
                        }
                        return view;
                    }
                };
                listView.setAdapter(arrayAdapter);
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

                if (multiChoiceClickListener != null) {
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //没有操作过选择,初始化状态（默认未选中）
                            if (!selectedItems.containsKey(position)) {
                                selectedItems.put(position, false);
                            }
                            boolean selectedStatus = !selectedItems.get(position);
                            selectedItems.put(position, selectedStatus);
                            multiChoiceClickListener.onClick(dialog, position, selectedStatus);
                        }
                    });
                }
            }

            if (multiChoice == ChoiceMode.ITEM) {
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, items);
                listView.setAdapter(arrayAdapter);
                if (listViewOnClickListener != null) {
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            listViewOnClickListener.onClick(dialog, position);
                        }
                    });
                }
            }

            if (multiChoice == ChoiceMode.SINGLE) {
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_single_choice, items);
                listView.setAdapter(arrayAdapter);
                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

                if (checkedItem != -1) {
                    listView.setItemChecked(checkedItem, true);
                }

                if (listViewOnClickListener != null) {
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            listViewOnClickListener.onClick(dialog, position);
                        }
                    });
                }
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
                btnContainer.setVisibility(View.VISIBLE);
                positiveButton.setBackgroundResource(R.drawable.btn_one_bg);
                line1.setVisibility(View.GONE);
            }

            if (negativeButtonText != null && positiveButtonText == null) {
                btnContainer.setVisibility(View.VISIBLE);
                negativeButton.setBackgroundResource(R.drawable.btn_one_bg);
                line1.setVisibility(View.GONE);
            }

            dialog.setContentView(layout);

            Window window = dialog.getWindow();
            window.getDecorView().setPadding(0, 0, 0, 0);

            WindowManager m = window.getWindowManager();
            Display defaultDisplay = m.getDefaultDisplay();

            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = (int)(defaultDisplay.getWidth() * 0.90);   //设置宽度的90%
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            window.setAttributes(lp);

            return dialog;
        }

    }

}
