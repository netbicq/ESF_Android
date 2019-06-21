package kkkj.android.esafety.bean;

import android.text.TextUtils;

public class TroubleCtrsPage {
    String BillEmp;
    String DangerLevel;
    String Principal;
    String TroubleLevel;

    String DangerPoint;
    String SubType;
    String CheckSub;
    String Danger;
    String AEmp;
    String EEmp;
    String CTime;

    public String getBillEmp() {
        if(TextUtils.isEmpty(BillEmp))
        {
            return "";
        }
        return BillEmp;
    }

    public String getDangerLevel() {
        if(TextUtils.isEmpty(DangerLevel))
        {
            return "";
        }
        return DangerLevel;
    }

    public String getPrincipal() {
        if(TextUtils.isEmpty(Principal))
        {
            return "";
        }
        return Principal;
    }

    public String getTroubleLevel() {
        if(TextUtils.isEmpty(TroubleLevel))
        {
            return "";
        }
        return TroubleLevel;
    }

    public String getDangerPoint() {
        return DangerPoint;
    }

    public String getSubType() {
        return SubType;
    }

    public String getCheckSub() {
        return CheckSub;
    }

    public String getDanger() {
        return Danger;
    }

    public String getAEmp() {
        return AEmp;
    }

    public String getEEmp() {
        return EEmp;
    }

    public String getCTime() {
        return CTime;
    }
}
