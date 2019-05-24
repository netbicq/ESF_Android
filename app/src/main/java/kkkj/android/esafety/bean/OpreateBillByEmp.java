package kkkj.android.esafety.bean;

public class OpreateBillByEmp {
    private String OpreateBillID = "";
    private String OpreateBillName = "";
    private String StartTime = "";
    private String EndTime = "";
    private String Principal = "";
    private int BillLong = 0;
    private String Description = "";
    private int CurrentIndex = 0;
    private int AllCount = 0;

    public String getOpreateBillID() {
        if(OpreateBillID==null)
        {
            return "";
        }
        return OpreateBillID;
    }

    public void setOpreateBillID(String opreateBillID) {
        OpreateBillID = opreateBillID;
    }

    public String getOpreateBillName() {
        if(OpreateBillName==null)
        {
            return "";
        }
        return OpreateBillName;
    }

    public void setOpreateBillName(String opreateBillName) {
        OpreateBillName = opreateBillName;
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



    public String getDescription() {
        if(Description==null)
        {
            return "";
        }
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getBillLong() {
        return BillLong;
    }

    public void setBillLong(int billLong) {
        BillLong = billLong;
    }

    public int getCurrentIndex() {
        return CurrentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        CurrentIndex = currentIndex;
    }

    public int getAllCount() {
        return AllCount;
    }

    public void setAllCount(int allCount) {
        AllCount = allCount;
    }
}