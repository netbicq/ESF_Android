package kkkj.android.esafety.customer;

import android.content.Context;
import android.graphics.Color;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

public class MyOptionsPicker {
    public MyOptionsPicker() {
    }
    public OptionsPickerView<Object> getPicker(Context mContext, String title, OnOptionsSelectListener listener)
    {
        return new OptionsPickerBuilder(mContext, listener).setCancelText("0000")//取消按钮文字 0000显示X图标
                .setSubmitText("确定")//确认按钮文字
                .setTitleSize(14)//标题文字大小
                .setTitleText(title)//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setTextColorCenter(Color.parseColor("#5CACEE"))
                .setDividerColor(Color.parseColor("#5CACEE"))
                .setSubCalSize(14)
                .setLineSpacingMultiplier(2f)
                .setTitleColor(Color.parseColor("#515151"))//标题文字颜色
                .setSubmitColor(Color.parseColor("#5CACEE"))//确定按钮文字颜色
                .setCancelColor(Color.RED)//取消按钮文字颜色
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setTitleBgColor(Color.WHITE)
                .build();
    }
}
