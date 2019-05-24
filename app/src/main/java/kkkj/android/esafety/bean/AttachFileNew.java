package kkkj.android.esafety.bean;

import org.litepal.crud.LitePalSupport;

import java.io.File;

public class AttachFileNew extends LitePalSupport {
    String FileTitle = "";
    String FileUrl = "";
    String FileType = "";
    String SubjectID = "";
    String BillID = "";
    String Danger = "";

    public String getDanger() {
        return Danger;
    }

    public void setDanger(String danger) {
        Danger = danger;
    }

    public String getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(String subjectID) {
        SubjectID = subjectID;
    }

    public String getBillID() {
        return BillID;
    }

    public void setBillID(String billID) {
        BillID = billID;
    }

    public String getFileTitle() {
        return FileTitle;
    }

    public void setFileTitle(String fileTitle) {
        FileTitle = fileTitle;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String fileUrl) {
        FileUrl = fileUrl;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    @Override
    public int delete() {
        //不含http 为本地地址 要删除
        if(!FileUrl.contains("http"))
        {
            File file = new File(FileUrl);
            if(file.exists())
            {
                file.delete();
            }
        }
        return super.delete();
    }
}
