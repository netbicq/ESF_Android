package kkkj.android.esafety.bean;
import android.text.TextUtils;
import org.litepal.crud.LitePalSupport;
public class SubStandard extends LitePalSupport {
    String SubStandardID;
    String Name;
    String KeyID= "";
    String Controls;
    String Engineering;
    String Accident;
    String Individual;

    public String getSubStandardID() {
        if(TextUtils.isEmpty(SubStandardID))
        {
            return "";
        }
        return SubStandardID;
    }

    public void setSubStandardID(String subStandardID) {
        SubStandardID = subStandardID;
    }

    public void setKeyID(String keyID) {
        KeyID = keyID;
    }

    public String getKeyID() {
        if(TextUtils.isEmpty(KeyID))
        {
            return "";
        }
        return KeyID;
    }

    public String getName() {
        if(TextUtils.isEmpty(Name))
        {
            return "";
        }
        return Name;
    }

    public String getControls() {
        if(TextUtils.isEmpty(Controls))
        {
            return "";
        }
        return Controls;
    }

    public String getEngineering() {
        if(TextUtils.isEmpty(Engineering))
        {
            return "";
        }
        return Engineering;
    }

    public String getAccident() {
        if(TextUtils.isEmpty(Accident))
        {
            return "";
        }
        return Accident;
    }

    public String getIndividual() {
        if(TextUtils.isEmpty(Individual))
        {
            return "";
        }
        return Individual;
    }
}
