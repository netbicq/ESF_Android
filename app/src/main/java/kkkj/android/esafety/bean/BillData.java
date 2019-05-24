package kkkj.android.esafety.bean;

import java.util.ArrayList;
import java.util.List;

public class BillData {
    private List<TaskSubjectView> CheckSubs = new ArrayList<>();
    private String BillID= "";
    private String TaskName= "";
    private String EmployeeName= "";
    private String State= "";
    private String StartTime= "";
    private String EndTime= "";
    private String DangerPointName= "";
    private int SubCount= 0;
    private  int SubCheckedCount=0;
    private  List<WHYSDict> WHYSDicts;

    public List<WHYSDict> getWHYSDicts() {
        if(WHYSDicts==null)
        {
            return new ArrayList<>();
        }
        return WHYSDicts;
    }

    public void setWHYSDicts(List<WHYSDict> WHYSDicts) {
        this.WHYSDicts = WHYSDicts;
    }

    public List<TaskSubjectView> getCheckSubs() {
        if(CheckSubs==null)
        {
            return new ArrayList<>();
        }
        return CheckSubs;
    }

    public void setCheckSubs(List<TaskSubjectView> checkSubs) {
        CheckSubs = checkSubs;
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
}
