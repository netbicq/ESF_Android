package kkkj.android.esafety.bean;

import android.text.TextUtils;

import com.contrarywind.interfaces.IPickerViewData;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class EnumItem extends LitePalSupport implements IPickerViewData {
    @Column(unique = true, defaultValue = "unknow")
    int Value;
    String Caption;

    public int getValue() {
        return Value;
    }

    public String getCaption() {
        if(TextUtils.isEmpty(Caption))
        {
            return "";
        }
        return Caption;
    }

    @Override
    public String getPickerViewText() {
        return getCaption();
    }
}
