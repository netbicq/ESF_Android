package kkkj.android.esafety.bean;

public class PhoneDocInstitutionModelView {
    private String InstitutionID = "";
    private String Name = "";
    private String InstitutionType = "";
    private String BigCode = "";
    private String IssueDate = "";
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

    public String getInstitutionID() {
        return InstitutionID;
    }

    public void setInstitutionID(String institutionID) {
        InstitutionID = institutionID;
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

    public String getInstitutionType() {
        return InstitutionType;
    }

    public void setInstitutionType(String institutionType) {
        InstitutionType = institutionType;
    }

    public String getBigCode() {
        return BigCode;
    }

    public void setBigCode(String bigCode) {
        BigCode = bigCode;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(String issueDate) {
        IssueDate = issueDate;
    }
}
