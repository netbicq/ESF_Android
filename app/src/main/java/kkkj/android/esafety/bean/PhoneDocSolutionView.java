package kkkj.android.esafety.bean;

public class PhoneDocSolutionView {
    private String DocSolutionID = "";
    private String Name = "";
    private String SolutionType = "";
    private String IssueDate = "";
    private String DangerLevel = "";

    public String getDocSolutionID() {
        if(DocSolutionID==null)
        {
            return "";
        }
        return DocSolutionID;
    }

    public void setDocSolutionID(String docSolutionID) {
        DocSolutionID = docSolutionID;
    }

    public String getName() {
        if(Name==null)
        {
            return "";
        }
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSolutionType() {
        if(SolutionType==null)
        {
            return "";
        }
        return SolutionType;
    }

    public void setSolutionType(String solutionType) {
        SolutionType = solutionType;
    }

    public String getIssueDate() {
        if(IssueDate==null)
        {
            return "";
        }
        return IssueDate;
    }

    public void setIssueDate(String issueDate) {
        IssueDate = issueDate;
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
}
