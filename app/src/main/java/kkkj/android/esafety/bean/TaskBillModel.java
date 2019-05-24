package kkkj.android.esafety.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

public class TaskBillModel extends LitePalSupport {
    @Column(unique = true, defaultValue = "unknow")
    String BillID = "";
    String TaskName= "";
    String EmployeeName= "";
    String State= "";
    String StartTime= "";
    String EndTime= "";
    String DangerPointName= "";
    int SubCount=0;
    int SubCheckedCount=0;
    int TaskType = 1;
    private List<WHYSDict> WHYSDicts;

    public List<WHYSDict> getWHYSDicts() {
        if(WHYSDicts==null)
        {
            return new ArrayList<>();
        }
        return WHYSDicts;
    }

    public int getTaskType() {
        return TaskType;
    }

    public void setTaskType(int taskType) {
        TaskType = taskType;
    }

    public int getSubCount() {
        return SubCount;
    }

    public void setSubCount(int subCount) {
        SubCount = subCount;
    }

    public int getSubCheckedCount() {
        return SubCheckedCount;
    }

    public void setSubCheckedCount(int subCheckedCount) {
        SubCheckedCount = subCheckedCount;
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

    public String getTaskName() {
        if(TaskName==null)
        {
            return "";
        }
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getEmployeeName() {
        if(EmployeeName==null)
        {
            return "";
        }
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getState() {
        if(State==null)
        {
            return "";
        }
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getStartTime() {
        if(StartTime==null)
        {
            return "";
        }
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        if(EndTime==null)
        {
            return "";
        }
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getDangerName() {
        if(DangerPointName==null)
        {
            return "";
        }
        return DangerPointName;
    }

    public void setDangerName(String dangerName) {
        DangerPointName = dangerName;
    }
}
