package kkkj.android.esafety.customer;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.IsJumpModel;


public class NewBillDialog extends Dialog implements View.OnClickListener {
    public NewBillDialog(Context context, int requestCode, String keyID, NewBillDialog.OnDialogButtonClickListener listener) {
        super(context);
        this.context = context;
        this.listener=listener;
        this.requestCode = requestCode;
        this.keyID = keyID;
    }
    public interface OnDialogButtonClickListener {
        /**
         * 点击按钮的回调方法
         *
         * @param requestCode
         */
        void onDialogButtonClick(int requestCode, String startt, String endt, boolean isJump);
    }
    private Context context;
    private int requestCode;
    private String keyID;
    private NewBillDialog.OnDialogButtonClickListener listener;

    private EditText ed_startt;

    private EditText ed_endt;

    private QMUIRoundButton btn_sub;

    private CheckBox isJump;

    IsJumpModel jumpModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbill_dialog);
//        setCancelable(false);//设置点击对话框外部和按返回键都不可以取消
//        setCanceledOnTouchOutside(false);//设置点击对话框外部是否可以取消，默认是不可以取消（但是点返回键可以取消）
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ed_startt = findViewById(R.id.ed_startt);
        ed_endt = findViewById(R.id.ed_endt);
        isJump= findViewById(R.id.isJump);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        ed_startt.setText(df.format(new Date()));
        ed_endt.setText(df.format(new Date()));

        btn_sub= findViewById(R.id.btn_sub);
        btn_sub.setOnClickListener(this);

        List<IsJumpModel> jumpModels = LitePal.where("keyID = ?",keyID).find(IsJumpModel.class);

        if(jumpModels.size()>0)
        {
            jumpModel = jumpModels.get(0);
        }
        else {
            jumpModel = new IsJumpModel();
            jumpModel.setKeyID(keyID);
            jumpModel.setJump(true);
        }
        isJump.setChecked(jumpModel.isJump());
        isJump.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                jumpModel.setJump(isChecked);
                jumpModel.saveOrUpdate("keyID = ?", keyID);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sub:
                String startt = "";
                String endt = "";
                if(!TextUtils.isEmpty(ed_startt.getText().toString().trim()))
                {
                    startt = ed_startt.getText().toString();
                }
                else {
                    Toast.makeText(context,"请输入开始时间",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!TextUtils.isEmpty(ed_endt.getText().toString().trim()))
                {
                    endt=ed_endt.getText().toString();
                }
                else {
                    Toast.makeText(context,"请输入预计完成时间",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onDialogButtonClick(requestCode,startt,endt,isJump.isChecked());
                dismiss();
                break;
        }
    }
}
