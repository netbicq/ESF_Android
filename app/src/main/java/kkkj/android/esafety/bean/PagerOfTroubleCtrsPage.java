package kkkj.android.esafety.bean;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class PagerOfTroubleCtrsPage {
    List<TroubleCtrsPage> Data;
    int Pages;
    int Items;
    String ExcelResult;

    public List<TroubleCtrsPage> getData() {
        if(Data==null)
        {
            return new ArrayList<>();
        }
        return Data;
    }

    public int getPages() {
        return Pages;
    }

    public int getItems() {
        return Items;
    }

    public String getExcelResult() {
        if(TextUtils.isEmpty(ExcelResult))
        {
            return "";
        }
        return ExcelResult;
    }
}
