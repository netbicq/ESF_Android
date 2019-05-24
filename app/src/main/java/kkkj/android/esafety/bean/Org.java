package kkkj.android.esafety.bean;

import android.text.TextUtils;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;


public class Org extends LitePalSupport {
    @Column(unique = true, defaultValue = "unknow")
    String KeyID;
    String OrgName;
    String ParentID;


    public void setKeyID(String keyID) {
        KeyID = keyID;
    }

    public void setOrgName(String orgName) {
        OrgName = orgName;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public String getKeyID() {
        if(TextUtils.isEmpty(KeyID))
        {
            return "";
        }
        return KeyID;
    }

    public String getOrgName() {
        if(TextUtils.isEmpty(OrgName))
        {
            return "";
        }
        return OrgName;
    }

    public String getParentID() {
        if(TextUtils.isEmpty(ParentID))
        {
            return "";
        }
        return ParentID;
    }
}
