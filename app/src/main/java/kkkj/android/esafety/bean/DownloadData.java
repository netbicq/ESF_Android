package kkkj.android.esafety.bean;
import java.util.ArrayList;
import java.util.List;

public class DownloadData {
    private int OverTimeTaskCount =0;

    private List<Dict> SGLXDicts;
    private List<Dict> SGHGDicts;
    private List<Dict> YXFWDicts;
    private List<DangerLevelDict> DangerLevels;
    private List<EnumItem> EvaluateMethod;
    private List<Dict> TroubleLevels;

    private List<Eval_Dict> LECD_Ls;
    private List<Eval_Dict> LECD_Es;
    private List<Eval_Dict> LECD_Cs;
    private List<Eval_Dict> LSD_Ls;
    private List<Eval_Dict> LSD_Ss;

    private List<BillData> BillDatas;
    private List<Emp> Emps;
    private List<Org> Orgs;

    public int getOverTimeTaskCount() {
        return OverTimeTaskCount;
    }

    public List<Dict> getSGLXDicts() {
        if(SGLXDicts==null)
        {
            return new ArrayList<>();
        }
        return SGLXDicts;
    }

    public List<Dict> getSGHGDicts() {
        if(SGHGDicts==null)
        {
            return new ArrayList<>();
        }
        return SGHGDicts;
    }

    public List<Dict> getYXFWDicts() {
        if(YXFWDicts==null)
        {
            return new ArrayList<>();
        }
        return YXFWDicts;
    }

    public List<DangerLevelDict> getDangerLevels() {
        return DangerLevels;
    }

    public List<EnumItem> getEvaluateMethod() {
        if(EvaluateMethod==null)
        {
            return new ArrayList<>();
        }
        return EvaluateMethod;
    }

    public List<Dict> getTroubleLevels() {
        if(TroubleLevels==null)
        {
            return new ArrayList<>();
        }
        return TroubleLevels;
    }

    public List<Eval_Dict> getLECD_Ls() {
        if(LECD_Ls==null)
        {
            return new ArrayList<>();
        }
        return LECD_Ls;
    }

    public List<Eval_Dict> getLECD_Es() {
        if(LECD_Es==null)
        {
            return new ArrayList<>();
        }
        return LECD_Es;
    }

    public List<Eval_Dict> getLECD_Cs() {
        if(LECD_Cs==null)
        {
            return new ArrayList<>();
        }
        return LECD_Cs;
    }

    public List<Eval_Dict> getLSD_Ls() {
        if(LSD_Ls==null)
        {
            return new ArrayList<>();
        }
        return LSD_Ls;
    }

    public List<Eval_Dict> getLSD_Ss() {
        if(LSD_Ss==null)
        {
            return new ArrayList<>();
        }
        return LSD_Ss;
    }

    public List<BillData> getBillDatas() {
        if(BillDatas==null)
        {
            return new ArrayList<>();
        }
        return BillDatas;
    }

    public List<Emp> getEmps() {
        if(Emps==null)
        {
            return new ArrayList<>();
        }
        return Emps;
    }

    public List<Org> getOrgs() {
        if(Orgs==null)
        {
            return new ArrayList<>();
        }
        return Orgs;
    }
}
