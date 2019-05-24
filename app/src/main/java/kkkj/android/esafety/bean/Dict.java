package kkkj.android.esafety.bean;

import com.contrarywind.interfaces.IPickerViewData;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Dict extends LitePalSupport implements IPickerViewData {
    @Column(unique = true, defaultValue = "unknow")
    String KeyID;
    String DictName;
    String Memo;
    String Name;

    public void setName(String name) {
        Name = name;
    }

    public String getKeyID() {
        return KeyID;
    }

    public String getDictName() {
        return DictName;
    }

    public String getMemo() {
        return Memo;
    }

    @Override
    public String getPickerViewText() {
        return getDictName();
    }
}
