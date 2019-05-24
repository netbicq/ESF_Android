package kkkj.android.esafety.bean;

import android.text.TextUtils;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Emp extends LitePalSupport {
    @Column(unique = true, defaultValue = "unknow")
    String KeyID;
    String CNName;
    String OrgID;
    int level = 3;

    public int getLevel() {
        return level+2;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public void setKeyID(String keyID) {
        KeyID = keyID;
    }

    public void setCNName(String CNName) {
        this.CNName = CNName;
    }

    public void setOrgID(String orgID) {
        OrgID = orgID;
    }

    public String getKeyID() {
        if (TextUtils.isEmpty(KeyID)) {
            return "";
        }
        return KeyID;
    }

    public String getCNName() {
        if (TextUtils.isEmpty(CNName)) {
            return "";
        }
        return CNName;
    }

    public String getOrgID() {
        if (TextUtils.isEmpty(OrgID)) {
            return "";
        }
        return OrgID;
    }
}
