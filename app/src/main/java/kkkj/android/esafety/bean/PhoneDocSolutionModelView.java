package kkkj.android.esafety.bean;

public class PhoneDocSolutionModelView {
    private String DocSolutionID = "";
    private String Name = "";
    private String SolutionType = "";
    private String IssueDate = "";
    private String DangerLevel = "";
    private String Content = "";

    public String getContent() {
        if(Content==null)
        {
            return "";
        }
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDocSolutionID() {
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
        return SolutionType;
    }

    public void setSolutionType(String solutionType) {
        SolutionType = solutionType;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(String issueDate) {
        IssueDate = issueDate;
    }

    public String getDangerLevel() {
        return DangerLevel;
    }

    public void setDangerLevel(String dangerLevel) {
        DangerLevel = dangerLevel;
    }
}
