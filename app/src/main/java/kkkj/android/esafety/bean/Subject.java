package kkkj.android.esafety.bean;
import android.text.TextUtils;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

public class Subject extends LitePalSupport{
    String SubjectID= "";
    String BillID= "";
    String DangerID= "";
    int SubjectType=0;
    int TaskResult = 0;
    String TaskResultMemo="";
    List<AttachFileNew> AttachFiles=new ArrayList<>();
    String time = "";


    /**
     * 2019/5/16新加参数
     */
    String Eval_WHYS = "";//危害因素
    String Eval_SGLX = "";//事故类型
    String Eval_SGJG = "";//事故结果
    String Eval_YXFW = "";//影响范围
    int Eval_Method =0;//评测方法
    String TroubleLevel = "";//隐患等级
    String DangerLevel = "";//危险等级
    String PrincipalID = "";//负责人id

    String LECD_L = "";
    String LECD_E = "";
    String LECD_C = "";
    String LSD_L = "";
    String LSD_S = "";
    int DValue ;

    public String getLECD_L() {
        return LECD_L;
    }

    public void setLECD_L(String LECD_L) {
        this.LECD_L = LECD_L;
    }

    public String getLECD_E() {
        return LECD_E;
    }

    public void setLECD_E(String LECD_E) {
        this.LECD_E = LECD_E;
    }

    public String getLECD_C() {
        return LECD_C;
    }

    public void setLECD_C(String LECD_C) {
        this.LECD_C = LECD_C;
    }

    public String getLSD_L() {
        return LSD_L;
    }

    public void setLSD_L(String LSD_L) {
        this.LSD_L = LSD_L;
    }

    public String getLSD_S() {
        return LSD_S;
    }

    public void setLSD_S(String LSD_S) {
        this.LSD_S = LSD_S;
    }

    public int getDValue() {
        return DValue;
    }

    public void setDValue(int DValue) {
        this.DValue = DValue;
    }

    public String getEval_WHYS() {
        if(TextUtils.isEmpty(Eval_WHYS))
        {
            return "";
        }
        return Eval_WHYS;
    }

    public String getEval_SGLX() {
        if(TextUtils.isEmpty(Eval_SGLX))
        {
            return "";
        }
        return Eval_SGLX;
    }

    public String getEval_SGJG() {
        if(TextUtils.isEmpty(Eval_SGJG))
        {
            return "";
        }
        return Eval_SGJG;
    }

    public String getEval_YXFW() {
        if(TextUtils.isEmpty(Eval_YXFW))
        {
            return "";
        }

        return Eval_YXFW;
    }

    public int getEval_Method() {

        return Eval_Method;
    }

    public String getTroubleLevel() {
        return TroubleLevel;
    }

    public String getDangerLevel() {
        if(TextUtils.isEmpty(DangerLevel))
        {
            return "";
        }
        return DangerLevel;
    }

    public String getPrincipalID() {
        if(TextUtils.isEmpty(PrincipalID))
        {
            return "";
        }
        return PrincipalID;
    }

    public void setEval_WHYS(String eval_WHYS) {

        Eval_WHYS = eval_WHYS;
    }

    public void setEval_SGLX(String eval_SGLX) {
        Eval_SGLX = eval_SGLX;
    }

    public void setEval_SGJG(String eval_SGJG) {
        Eval_SGJG = eval_SGJG;
    }

    public void setEval_YXFW(String eval_YXFW) {
        Eval_YXFW = eval_YXFW;
    }

    public void setEval_Method(int eval_Method) {
        Eval_Method = eval_Method;
    }

    public void setTroubleLevel(String troubleLevel) {
        TroubleLevel = troubleLevel;
    }

    public void setDangerLevel(String dangerLevel) {
        DangerLevel = dangerLevel;
    }

    public void setPrincipalID(String principalID) {
        PrincipalID = principalID;
    }



    public String getDangerID() {
        return DangerID;
    }

    public void setDangerID(String dangerID) {
        DangerID = dangerID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBillID() {
        return BillID;
    }

    public void setBillID(String billID) {
        BillID = billID;
    }

    public int getSubjectType() {
        return SubjectType;
    }

    public void setSubjectType(int subjectType) {
        SubjectType = subjectType;
    }

    public String getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(String subjectID) {
        SubjectID = subjectID;
    }

    public int getTaskResult() {
        return TaskResult;
    }

    public void setTaskResult(int taskResult) {
        TaskResult = taskResult;
    }

    public String getTaskResultMemo() {
        return TaskResultMemo;
    }

    public void setTaskResultMemo(String taskResultMemo) {
        TaskResultMemo = taskResultMemo;
    }

    public List<AttachFileNew> getAttachFiles() {
        return LitePal.where("SubjectID = ? and BillID = ?and Danger = ?",getSubjectID(),getBillID(),getDangerID()).find(AttachFileNew.class);
    }

    public void setAttachFiles(List<AttachFileNew> attachFiles) {
        for(int i = 0 ;i<attachFiles.size();i++)
        {
            attachFiles.get(i).saveOrUpdateAsync("FileUrl = ?",attachFiles.get(i).getFileUrl());
        }
    }

    @Override
    public int delete() {

        List<AttachFileNew>  files =  getAttachFiles();
        for(int i = 0 ;i<files.size();i++)
        {
            files.get(i).delete();
        }
        return super.delete();
    }
}
