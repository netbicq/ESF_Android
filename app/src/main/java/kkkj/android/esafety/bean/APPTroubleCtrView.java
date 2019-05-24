package kkkj.android.esafety.bean;
import android.text.TextUtils;

import java.io.Serializable;

public class APPTroubleCtrView implements Serializable {
    String KeyID = "";
    String Principal = "";//负责人
    String Executor = "";//隐患管控执行人
    String ExecutorID = "";
    String Acceptor = "";//隐患管控验收人
    String AcceptorID = "";
    String SubName = "";//主体名
    String DangerName = "";//风控项
    int State = 1;//管控项状态  1管控中  2申请验收 3已验收  4已归档
    String EstimatedDate = "";//预计完成时间
    String TroubleLevel = "";//1 一般风险  2较大风险  3严重风险  4 重大风险
    String TroubleLevelName = "";
    String CDangerLevel = "";//风险等级ID
    String CDangerLevelName = "";//风险等级
    String CtrTarget = "";//管控目标
    String TroubleDetails = "";//问题详情
    String DangerPoint = "";//风险点
    int Cuser = 1;
    //当前人
    // 1负责人 处理 归档 转让责任人 快速处理
    // 2执行人 申请验收
    // 3验收人 验收

    public String getExecutorID() {
        if(TextUtils.isEmpty(ExecutorID))
        {
            return "";
        }
        return ExecutorID;
    }

    public void setExecutorID(String executorID) {
        ExecutorID = executorID;
    }

    public String getAcceptorID() {
        if(TextUtils.isEmpty(AcceptorID))
        {
            return "";
        }
        return AcceptorID;
    }

    public void setAcceptorID(String acceptorID) {
        AcceptorID = acceptorID;
    }

    public String getTroubleLevelName() {
        if(TextUtils.isEmpty(TroubleLevelName))
        {
            return "";
        }
        return TroubleLevelName;
    }

    public void setTroubleLevelName(String troubleLevelName) {
        TroubleLevelName = troubleLevelName;
    }

    public String getKeyID() {
        return KeyID;
    }

    public void setKeyID(String keyID) {
        KeyID = keyID;
    }

    public int getCuser() {
        return Cuser;
    }

    public void setCuser(int cuser) {
        Cuser = cuser;
    }

    public String getCDangerLevelName() {
        if(TextUtils.isEmpty(CDangerLevelName))
        {
            return "";
        }
        return CDangerLevelName;
    }

    public void setCDangerLevelName(String CDangerLevelName) {
        this.CDangerLevelName = CDangerLevelName;
    }

    public String getDangerPoint() {
        if(TextUtils.isEmpty(DangerPoint))
        {
            return "";
        }
        return DangerPoint;
    }

    public void setDangerPoint(String dangerPoint) {
        DangerPoint = dangerPoint;
    }

    public String getPrincipal() {
        if(TextUtils.isEmpty(Principal))
        {
            return "";
        }
        return Principal;
    }

    public void setPrincipal(String principal) {
        Principal = principal;
    }

    public String getExecutor() {
        if(TextUtils.isEmpty(Executor))
        {
            return "";
        }
        return Executor;
    }

    public void setExecutor(String executor) {
        Executor = executor;
    }

    public String getAcceptor() {
        if(TextUtils.isEmpty(Acceptor))
        {
            return "";
        }
        return Acceptor;
    }

    public void setAcceptor(String acceptor) {
        Acceptor = acceptor;
    }

    public String getSubName() {
        if(TextUtils.isEmpty(SubName))
        {
            return "";
        }
        return SubName;
    }

    public void setSubName(String subName) {
        SubName = subName;
    }

    public String getDangerName() {
        if(TextUtils.isEmpty(DangerName))
        {
            return "";
        }
        return DangerName;
    }

    public void setDangerName(String dangerName) {
        DangerName = dangerName;
    }

    public int getState() {

        return State;
    }

    public void setState(int state) {

        State = state;
    }

    public String getEstimatedDate() {
        if(TextUtils.isEmpty(EstimatedDate))
        {
            return "";
        }
        return EstimatedDate;
    }

    public void setEstimatedDate(String estimatedDate) {
        EstimatedDate = estimatedDate;
    }

    public String getTroubleLevel() {
        return TroubleLevel;
    }

    public void setTroubleLevel(String troubleLevel) {
        TroubleLevel = troubleLevel;
    }

    public String getCDangerLevel() {
        if(TextUtils.isEmpty(CDangerLevel))
        {
            return "";
        }
        return CDangerLevel;
    }

    public void setCDangerLevel(String CDangerLevel) {
        this.CDangerLevel = CDangerLevel;
    }

    public String getCtrTarget() {
        if(TextUtils.isEmpty(CtrTarget))
        {
            return "";
        }
        return CtrTarget;
    }

    public void setCtrTarget(String ctrTarget) {
        CtrTarget = ctrTarget;
    }

    public String getTroubleDetails() {
        if(TextUtils.isEmpty(TroubleDetails))
        {
            return "";
        }
        return TroubleDetails;
    }

    public void setTroubleDetails(String troubleDetails) {
        TroubleDetails = troubleDetails;
    }
}
