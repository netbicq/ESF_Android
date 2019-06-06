package kkkj.android.esafety.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sub implements IPickerViewData , Serializable {
    String SubName = "";
    String SubID = "";
    List<Danger> Dangers;
    int SubTypeID;

    public int getSubTypeID() {
        return SubTypeID;
    }

    public void setSubTypeID(int subTypeID) {
        SubTypeID = subTypeID;
    }

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

    public class Danger implements IPickerViewData,Serializable
    {
        String DangerID="";
        String SubID="";
        String DangerName="";
        boolean checked = false;


        public String getSubID() {
            return SubID;
        }

        public void setSubID(String subID) {
            SubID = subID;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

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
