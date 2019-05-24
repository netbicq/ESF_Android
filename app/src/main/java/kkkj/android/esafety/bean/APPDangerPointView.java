package kkkj.android.esafety.bean;

import android.text.TextUtils;

public class APPDangerPointView {
    String DangerPoint;
    String Principal;
    String DangerLevel;

    public String getDangerPoint() {
        if(TextUtils.isEmpty(DangerPoint))
        {
            return "";
        }
        return DangerPoint;
    }

    public String getPrincipal() {
        if(TextUtils.isEmpty(Principal))
        {
            return "";
        }
        return Principal;
    }

    public String getDangerLevel() {
        if(TextUtils.isEmpty(DangerLevel))
        {
            return "";
        }
        return DangerLevel;
    }
}
