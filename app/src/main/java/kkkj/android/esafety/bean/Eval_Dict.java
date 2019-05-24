package kkkj.android.esafety.bean;

import android.text.TextUtils;

import com.contrarywind.interfaces.IPickerViewData;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Eval_Dict extends LitePalSupport implements IPickerViewData {
    @Column(unique = true, defaultValue = "unknow")
    String KeyID;
    String DictName;
    double Value;
    String Memo;
    String Name;

    public void setName(String name) {
        Name = name;
    }

    public String getKeyID() {
        if(TextUtils.isEmpty(KeyID))
        {
            return "";
        }
        return KeyID;
    }

    public String getDictName() {
        if(TextUtils.isEmpty(DictName))
        {
            return "";
        }
        return DictName;
    }

    public double getValue() {

        return Value;
    }

    public String getMemo() {
        if(TextUtils.isEmpty(Memo))
        {
            return "";
        }
        return Memo;
    }

    @Override
    public String getPickerViewText() {
        return getDictName();
    }
}
