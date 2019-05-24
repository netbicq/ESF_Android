package kkkj.android.esafety.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Bll_AttachFile extends LitePalSupport {
    @Column(unique = true, defaultValue = "unknow")
    String FileUrl= "";
    String BusinessID= "";
    String FileTitle= "";
    String FileType= "";
    String ID= "";

    public String getBusinessID() {
        return BusinessID;
    }

    public void setBusinessID(String businessID) {
        BusinessID = businessID;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
