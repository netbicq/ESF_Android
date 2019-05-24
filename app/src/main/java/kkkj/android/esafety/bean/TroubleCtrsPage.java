package kkkj.android.esafety.bean;

import android.text.TextUtils;

public class TroubleCtrsPage {
    String BillEmp;
    String DangerLevel;
    String Principal;
    String TroubleLevel;

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
}
