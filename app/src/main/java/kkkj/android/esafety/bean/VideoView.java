package kkkj.android.esafety.bean;

public class VideoView {
    private String ID = "";
    private String Code = "";
    private String Site = "";
    private String Url = "";

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCode() {
        if(Code==null)
        {
            return "";
        }
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getSite() {
        if(Site==null)
        {
            return "";
        }
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }

    public String getUrl() {
        if(Url==null)
        {
            return "";
        }
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
