package kkkj.android.esafety.bean;

import android.text.TextUtils;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class IsJumpModel extends LitePalSupport {
    @Column(unique = true, defaultValue = "unknow")
    String keyID;
    boolean isJump;

    public String getKeyID() {
        if(TextUtils.isEmpty(keyID))
        {
            return "";
        }
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public boolean isJump() {
        return isJump;
    }

    public void setJump(boolean jump) {
        isJump = jump;
    }
}
