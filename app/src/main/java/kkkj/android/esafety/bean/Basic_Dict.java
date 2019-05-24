package kkkj.android.esafety.bean;

public class Basic_Dict {
    private String DictName = "";
    private String ID = "";

    public String getDictName() {
        if(DictName==null)
        {
            return "";
        }
        return DictName;
    }

    public void setDictName(String dictName) {
        DictName = dictName;
    }

    public String getID() {
        if(ID==null)
        {
            return "";
        }
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
