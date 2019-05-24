package kkkj.android.esafety.bean;

import android.text.TextUtils;

import com.contrarywind.interfaces.IPickerViewData;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class EMethod extends LitePalSupport implements IPickerViewData {
    @Column(unique = true, defaultValue = "unknow")
    String Caption = "";
    int Value;
    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public String getCaption() {
        if(TextUtils.isEmpty(Caption))
        {
            return "";
        }
        return Caption;
    }

    public void setCaption(String caption) {
        Caption = caption;
    }

    @Override
    public String getPickerViewText() {
        return getCaption();
    }
}
