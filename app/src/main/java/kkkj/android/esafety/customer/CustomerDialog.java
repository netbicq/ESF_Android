package kkkj.android.esafety.customer;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import kkkj.android.esafety.R;


public class CustomerDialog extends Dialog implements View.OnClickListener {


    public CustomerDialog(Context context, int requestCode, String content, String title,
                          String positive, String negative, CustomerDialog.OnDialogButtonClickListener listener) {
        super(context);
        this.context = context;
        this.requestCode=requestCode;
        this.content=content;
        this.positive=positive;
        this.negative=negative;
        this.title = title;
        this.listener=listener;
    }
    public interface OnDialogButtonClickListener {
        /**
         * 点击按钮的回调方法
         *
         * @param requestCode
         * @param isPositive
         */
        void onDialogButtonClick(int requestCode, boolean isPositive);
    }
    private Context context;
    private int requestCode;
    private CustomerDialog.OnDialogButtonClickListener listener;

    private TextView tv_content;

    private TextView positiveBtn;

    private TextView negativeBtn;
    private TextView tv_title;
    private String content;
    private String positive;
    private String negative;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_dialog);
//        setCancelable(false);//设置点击对话框外部和按返回键都不可以取消
//        setCanceledOnTouchOutside(false);//设置点击对话框外部是否可以取消，默认是不可以取消（但是点返回键可以取消）
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        positiveBtn= (TextView) findViewById(R.id.positiveBtn);
        tv_content= (TextView) findViewById(R.id.content);
        negativeBtn= (TextView) findViewById(R.id.negativeBtn);
        tv_title = (TextView)findViewById(R.id.tv_title);

        tv_content.setText(content);
        if(!TextUtils.isEmpty(positive))
        {
            positiveBtn.setText(positive);
            positiveBtn.setVisibility(View.VISIBLE);
        }else {
            positiveBtn.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(title))
        {
            tv_title.setText(title);
            tv_title.setVisibility(View.VISIBLE);
        }else {
            tv_title.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(negative))
        {
            negativeBtn.setText(negative);
            negativeBtn.setVisibility(View.VISIBLE);
        }else {
            negativeBtn.setVisibility(View.GONE);
        }
        positiveBtn.setOnClickListener(this);
        negativeBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.negativeBtn:
                //确定按钮
                listener.onDialogButtonClick(requestCode,false);
                break;
            case R.id.positiveBtn:
                //确定按钮
                listener.onDialogButtonClick(requestCode,true);
                break;
        }
    }
}
