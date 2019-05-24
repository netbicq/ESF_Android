package kkkj.android.esafety.bean;

import android.text.TextUtils;

public class TimeOutTask {
    private String Name;
    private String EmpOrPost;
    private String OverHours;
    private String DangerLevel;
    private String DangerPoint;

    public String getName() {
        if(TextUtils.isEmpty(Name))
        {
            return "";
        }
        return Name;
    }

    public String getEmpOrPost() {
        if(TextUtils.isEmpty(EmpOrPost))
        {
            return "";
        }
        return EmpOrPost;
    }

    public String getOverHours() {
        if(TextUtils.isEmpty(OverHours))
        {
            return "";
        }
        return OverHours;
    }

    public String getDangerLevel() {
        if(TextUtils.isEmpty(DangerLevel))
        {
            return "";
        }
        return DangerLevel;
    }

    public String getDangerPoint() {
        if(TextUtils.isEmpty(DangerPoint))
        {
            return "";
        }
        return DangerPoint;
    }
}
