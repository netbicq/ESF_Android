package kkkj.android.esafety.bean;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import org.litepal.LitePal;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;
import org.litepal.crud.async.SaveExecutor;
import org.litepal.crud.callback.SaveCallback;

import java.util.ArrayList;
import java.util.List;

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

    List<SubStandard> SubStandards;


    String LastResult;

    public String getLastResult() {
        if(TextUtils.isEmpty(LastResult))
        {
            return "";
        }
        return LastResult;
    }

    public void setLastResult(String lastResult) {
        LastResult = lastResult;
    }

    @Override
    public SaveExecutor saveOrUpdateAsync(String... conditions) {
        for(int i = 0 ;i<getSubStandards().size();i++)
        {
            getSubStandards().get(i).setKeyID(getKeyID());
            getSubStandards().get(i).saveOrUpdateAsync("KeyID = ? and SubStandardID = ?",getKeyID(),getSubStandards().get(i).getSubStandardID()).listen(new SaveCallback() {
                @Override
                public void onFinish(boolean success) {
                    Logger.d("保存标准"+success);
                }
            });
        }
        return super.saveOrUpdateAsync(conditions);
    }

    @Override
    public int delete() {
        return super.delete();
    }

    public void setSubStandards(List<SubStandard> subStandards) {
        SubStandards = subStandards;
    }

    public List<SubStandard> getSubStandards() {
        if(SubStandards==null)
        {
            return new ArrayList<>();
        }
        return SubStandards;
    }

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
