package kkkj.android.esafety.bean;

import android.text.TextUtils;

import com.contrarywind.interfaces.IPickerViewData;

import org.litepal.crud.LitePalSupport;

public class WHYSDict extends LitePalSupport implements IPickerViewData {
    String KeyID = "";
    String BillID= "";
    String DictName = "";
    String Memo;
    int MinValue ;
    int MaxValue ;

    public String getMemo() {
        return Memo;
    }

    public String getBillID() {
        if(TextUtils.isEmpty(BillID))
        {
            return "";
        }
        return BillID;
    }

    public void setBillID(String billID) {
        BillID = billID;
    }

    public String getKeyID() {
        if(TextUtils.isEmpty(KeyID))
        {
            return "";
        }
        return KeyID;
    }

    public void setKeyID(String keyID) {
        KeyID = keyID;
    }

    public String getDictName() {
        if(TextUtils.isEmpty(DictName))
        {
            return "";
        }
        return DictName;
    }

    public void setDictName(String dictName) {
        DictName = dictName;
    }

    public int getMinValue() {
        return MinValue;
    }

    public void setMinValue(int minValue) {
        MinValue = minValue;
    }

    public int getMaxValue() {
        return MaxValue;
    }

    public void setMaxValue(int maxValue) {
        MaxValue = maxValue;
    }

    @Override
    public String getPickerViewText() {
        return getDictName();
    }
}
