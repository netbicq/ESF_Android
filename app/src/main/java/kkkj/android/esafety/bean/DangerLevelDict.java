package kkkj.android.esafety.bean;

import android.text.TextUtils;

import com.contrarywind.interfaces.IPickerViewData;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class DangerLevelDict extends LitePalSupport implements IPickerViewData {
    @Column(unique = true, defaultValue = "unknow")
    String KeyID;
    String DictName;
    String Memo;
    int LECD_DMaxValue;
    int LECD_DMinValue;
    int LSD_DMaxValue;
    int LSD_DMinValue;

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

    public String getMemo() {
        if(TextUtils.isEmpty(Memo))
        {
            return "";
        }
        return Memo;
    }

    public int getLECD_DMaxValue() {
        return LECD_DMaxValue;
    }

    public int getLECD_DMinValue() {
        return LECD_DMinValue;
    }

    public int getLSD_DMaxValue() {
        return LSD_DMaxValue;
    }

    public int getLSD_DMinValue() {
        return LSD_DMinValue;
    }

    @Override
    public String getPickerViewText() {
        return getDictName();
    }
}
