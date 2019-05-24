package kkkj.android.esafety.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.ArrayList;
import java.util.List;

public class Sub implements IPickerViewData {
    String SubName = "";
    String SubID = "";
    List<Danger> Dangers;

    public String getSubName() {
        if(SubName==null)
        {
            return "";
        }
        return SubName;
    }

    public void setSubName(String subName) {
        SubName = subName;
    }

    public String getSubID() {
        if(SubID==null)
        {
            return "";
        }
        return SubID;
    }

    public void setSubID(String subID) {
        SubID = subID;
    }

    public List<Danger> getDangers() {
        if(Dangers==null)
        {
            return new ArrayList<>();
        }
        return Dangers;
    }

    public void setDangers(List<Danger> dangers) {
        Dangers = dangers;
    }

    @Override
    public String getPickerViewText() {
        return getSubName();
    }

    public class Danger implements IPickerViewData
    {
        String DangerID="";
        String DangerName="";

        public String getDangerID() {
            if(DangerID==null)
            {
                return "";
            }
            return DangerID;
        }

        public void setDangerID(String dangerID) {
            DangerID = dangerID;
        }

        public String getDangerName() {
            if(DangerName==null)
            {
                return "";
            }
            return DangerName;
        }

        public void setDangerName(String dangerName) {
            DangerName = dangerName;
        }

        @Override
        public String getPickerViewText() {
            return getDangerName();
        }
    }
}
