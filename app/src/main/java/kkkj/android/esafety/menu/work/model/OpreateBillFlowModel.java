package kkkj.android.esafety.menu.work.model;

import java.util.ArrayList;
import java.util.List;

import kkkj.android.esafety.bean.OpreateBillFlow;

public class OpreateBillFlowModel {
    private List<OpreateBillFlow> BillFlows;
    private String ID = "";
    private String BillCode = "";
    private String OpreationID = "";
    private String OpreationName = "";
    private String BillName = "";
    private String StartTime = "";
    private String EndTime = "";
    private String PrincipalEmployeeID = "";
    private String PrincipalEmployeeName = "";
    private int BillLong = 0;
    private int State = 0;
    private String StateName = "";
    private String CreateMan = "";
    private String CreateDate = "";
    private String OrgID = "";
    private String Description = "";

    public List<OpreateBillFlow> getBillFlows() {
        if(BillFlows==null)
        {
            return new ArrayList<>();
        }
        return BillFlows;
    }

    public void setBillFlows(List<OpreateBillFlow> billFlows) {
        BillFlows = billFlows;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBillCode() {
        return BillCode;
    }

    public void setBillCode(String billCode) {
        BillCode = billCode;
    }

    public String getOpreationID() {
        return OpreationID;
    }

    public void setOpreationID(String opreationID) {
        OpreationID = opreationID;
    }

    public String getOpreationName() {
        return OpreationName;
    }

    public void setOpreationName(String opreationName) {
        OpreationName = opreationName;
    }

    public String getBillName() {
        if(BillName==null)
        {
            return "";
        }
        return BillName;
    }

    public void setBillName(String billName) {
        BillName = billName;
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

    public String getPrincipalEmployeeID() {
        return PrincipalEmployeeID;
    }

    public void setPrincipalEmployeeID(String principalEmployeeID) {
        PrincipalEmployeeID = principalEmployeeID;
    }

    public String getPrincipalEmployeeName() {
        if(PrincipalEmployeeName==null)
        {
            return "";
        }
        return PrincipalEmployeeName;
    }

    public void setPrincipalEmployeeName(String principalEmployeeName) {
        PrincipalEmployeeName = principalEmployeeName;
    }

    public int getBillLong() {
        return BillLong;
    }

    public void setBillLong(int billLong) {
        BillLong = billLong;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public String getStateName() {
        if(StateName==null)
        {
            return "";
        }
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getCreateMan() {
        return CreateMan;
    }

    public void setCreateMan(String createMan) {
        CreateMan = createMan;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String orgID) {
        OrgID = orgID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
