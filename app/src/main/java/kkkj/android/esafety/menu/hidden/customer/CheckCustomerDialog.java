package kkkj.android.esafety.menu.hidden.customer;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import kkkj.android.esafety.R;


public class CheckCustomerDialog extends Dialog implements View.OnClickListener {
    public CheckCustomerDialog(Context context, int requestCode,
                               CheckCustomerDialog.OnDialogButtonClickListener listener) {
        super(context);
        this.context = context;
        this.requestCode = requestCode;
        this.listener = listener;
    }

    public interface OnDialogButtonClickListener {
        /**
         * 点击按钮的回调方法
         *
         * @param requestCode
         * @param isPositive
         */
        void onDialogButtonClick(int requestCode, boolean isPositive, String content, int result);
    }

    private Context context;
    private int requestCode;
    private CheckCustomerDialog.OnDialogButtonClickListener listener;
    private EditText ed_content;
    private TextView positiveBtn;
    private TextView negativeBtn;
    private TextView tv_title;
    private RadioButton rd_left;
    private RadioButton rd_right;
    int FlowResult=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_checkbox);
//        setCancelable(false);//设置点击对话框外部和按返回键都不可以取消
//        setCanceledOnTouchOutside(false);//设置点击对话框外部是否可以取消，默认是不可以取消（但是点返回键可以取消）
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        positiveBtn = (TextView) findViewById(R.id.positiveBtn);
        ed_content = (EditText) findViewById(R.id.content);
        negativeBtn = (TextView) findViewById(R.id.negativeBtn);
        tv_title = (TextView) findViewById(R.id.tv_title);
        rd_left = (RadioButton) findViewById(R.id.rd_left);
        rd_right = (RadioButton) findViewById(R.id.rd_right);
        rd_left.setChecked(true);
        rd_left.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    FlowResult=1;
                    rd_right.setChecked(false);
                }
            }
        });
        rd_right.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    FlowResult=2;
                    rd_left.setChecked(false);
                }
            }
        });
        positiveBtn.setOnClickListener(this);
        negativeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.negativeBtn:
                //确定按钮
                listener.onDialogButtonClick(requestCode, false, ed_content.getText().toString() + "",FlowResult);
                break;
            case R.id.positiveBtn:
                //确定按钮
                listener.onDialogButtonClick(requestCode, true, ed_content.getText().toString() + "",FlowResult);
                break;
        }
    }
}
