package kkkj.android.esafety.bean;

public class InspectTaskSubjectNew {
    int SubjectType =0;
    String SubjectID="";
    String DangerID = "";

    public String getDangerID() {
        return DangerID;
    }

    public void setDangerID(String dangerID) {
        DangerID = dangerID;
    }

    public int getSubjectType() {
        return SubjectType;
    }

    public void setSubjectType(int subjectType) {
        SubjectType = subjectType;
    }

    public String getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(String subjectID) {
        SubjectID = subjectID;
    }
}
