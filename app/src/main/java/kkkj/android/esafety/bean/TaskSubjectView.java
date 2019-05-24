package kkkj.android.esafety.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class TaskSubjectView extends LitePalSupport {
    @Column(unique = true, defaultValue = "unknow")
    String KeyID= "";
    String SubID= "";
    String BillID= "";
    String TaskID= "";
    String SubResultID= "";
    String SubName= "";
    String Principal= "";
    String PrincipalTel= "";
    String DangerLevel= "";
    int SubType=0;
    String SubTypeName= "";
    String DangerName = "";
    String DangerID = "";
    boolean IsControl = false;

    public boolean isControl() {
        return IsControl;
    }

    public void setControl(boolean control) {
        IsControl = control;
    }

    public String getDangerID() {
        return DangerID;
    }

    public void setDangerID(String dangerID) {
        DangerID = dangerID;
    }

    int state=0;//0未做 1已做但是未提交

    public String getDangerName() {
        if(DangerName==null)
        {
            return "";
        }
        return DangerName;
    }

    public void setDangerName(String dangerName) {
        DangerName = dangerName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    public String getKeyID() {
        if(KeyID==null)
        {
            return "";
        }
        return KeyID;
    }

    public void setKeyID(String keyID) {
        KeyID = keyID;
    }

    public String getBillID() {
        if(BillID==null)
        {
            return "";
        }
        return BillID;
    }

    public void setBillID(String billID) {
        BillID = billID;
    }

    public String getSubResultID() {
        if(SubResultID==null)
        {
            return "";
        }
        return SubResultID;
    }

    public void setSubResultID(String subResultID) {
        SubResultID = subResultID;
    }

    public String getSubID() {
        if(SubID==null)
        {
            return "";
        }
        return SubID;
    }

    public void setSubID(String subID) {
        SubID = subID;
    }

    public String getSubName() {
        if(SubName==null)
        {
            return "";
        }
        return SubName;
    }

    public void setSubName(String subName) {
        SubName = subName;
    }

    public String getPrincipal() {
        if(Principal==null)
        {
            return "";
        }
        return Principal;
    }

    public void setPrincipal(String principal) {
        Principal = principal;
    }

    public String getPrincipalTel() {
        if(PrincipalTel==null)
        {
            return "";
        }
        return PrincipalTel;
    }

    public void setPrincipalTel(String principalTel) {
        PrincipalTel = principalTel;
    }

    public String getDangerLevel() {
        if(DangerLevel==null)
        {
            return "";
        }
        return DangerLevel;
    }

    public void setDangerLevel(String dangerLevel) {
        DangerLevel = dangerLevel;
    }

    public int getSubType() {
        return SubType;
    }

    public void setSubType(int subType) {
        SubType = subType;
    }

    public String getSubTypeName() {
        if(SubTypeName==null)
        {
            return "";
        }
        return SubTypeName;
    }

    public void setSubTypeName(String subTypeName) {
        SubTypeName = subTypeName;
    }


}
