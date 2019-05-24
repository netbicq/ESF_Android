package kkkj.android.esafety.bean;

import android.text.TextUtils;

public class StatisticsDangerLevel {
    String LevelID;
    String LevelName;
    int Count;

    public String getLevelID() {
        if(TextUtils.isEmpty(LevelID))
        {
            return "";
        }
        return LevelID;
    }

    public String getLevelName() {
        if(TextUtils.isEmpty(LevelName))
        {
            return "";
        }
        return LevelName;
    }

    public int getCount() {
        return Count;
    }
}
