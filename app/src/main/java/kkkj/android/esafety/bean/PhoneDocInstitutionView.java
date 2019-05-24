package kkkj.android.esafety.bean;

public class PhoneDocInstitutionView {
    private String InstitutionID = "";
    private String Name = "";
    private String InstitutionType = "";
    private String BigCode = "";
    private String IssueDate = "";

    public String getInstitutionID() {
        if(InstitutionID==null)
        {
            return "";
        }
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
        if(InstitutionType==null)
        {
            return "";
        }
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
        if(IssueDate==null)
        {
            return "";
        }
        return IssueDate;
    }

    public void setIssueDate(String issueDate) {
        IssueDate = issueDate;
    }
}
