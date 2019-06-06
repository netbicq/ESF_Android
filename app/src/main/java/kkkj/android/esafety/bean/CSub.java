package kkkj.android.esafety.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CSub implements Serializable {
    int SubTypeID;
    String SubType;
    List<Sub> Subs;

    public int getSubTypeID() {
        return SubTypeID;
    }

    public void setSubTypeID(int subTypeID) {
        SubTypeID = subTypeID;
    }

    public String getSubType() {
        if(TextUtils.isEmpty(SubType))
        {
            return "";
        }
        return SubType;
    }

    public List<Sub> getSubs() {
        if(Subs==null)
        {
            return new ArrayList<>();
        }
        return Subs;
    }
}
