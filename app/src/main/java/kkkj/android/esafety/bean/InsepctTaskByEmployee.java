package kkkj.android.esafety.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * 任务列表
 */
public class InsepctTaskByEmployee extends LitePalSupport {
    @Column(unique = true, defaultValue = "unknow")
    String TaskID = "";//任务ID
    String Name = "";//任务名称
    String DangerPointID = "";//风险点ID
    int TaskTypeID = 0;//任务类型 1同期任务 2临时任务
    String TaskDescription = "";//任务说明
    String DangerName = "";//风险点名称
    String TaskTypeName = "";//任务类型名称
    String LastTime = "";//最后执行时间
    String TimeOutHours = "";//超时小时数
    int CycleValue = 0;//频率值
    int CycleDateType = 0;//频率日期类型
    String CycleName = "";//执行频率
    boolean HasDone = false;//是否做过

    public boolean isHasDone() {
        return HasDone;
    }

    public int getCycleValue() {
        return CycleValue;
    }


    public String getCycleName() {
        if (CycleName == null) {
            return "";
        }
        return CycleName;
    }


    public String getTaskID() {
        if (TaskID == null) {
            return "";
        }
        return TaskID;
    }


    public String getTaskDescription() {
        if (TaskDescription == null) {
            return "";
        }
        return TaskDescription;
    }


    public String getName() {
        if (Name == null) {
            return "";
        }
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public String getDangerName() {
        if (DangerName == null) {
            return "";
        }
        return DangerName;
    }


    public String getLastTime() {
        if (LastTime == null) {
            return "";
        }
        return LastTime;
    }


    public String getTimeOutHours() {
        if (TimeOutHours == null) {
            return "";
        }
        return TimeOutHours;
    }


}
