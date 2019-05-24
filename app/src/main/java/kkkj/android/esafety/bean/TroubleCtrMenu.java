package kkkj.android.esafety.bean;

import android.text.TextUtils;

public class TroubleCtrMenu {
    int MenuValue;
    String MemuDesc;
    int Count;

    public int getMenuValue() {
        return MenuValue;
    }

    public String getMemuDesc() {
        if(TextUtils.isEmpty(MemuDesc))
        {
            return "";
        }
        return MemuDesc;
    }

    public int getCount() {
        return Count;
    }
}
