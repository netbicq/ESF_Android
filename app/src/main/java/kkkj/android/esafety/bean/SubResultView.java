package kkkj.android.esafety.bean;

import android.text.TextUtils;

public class SubResultView {
    private String ResultTime= "";
    private int TaskResult=0;
    private String TaskResultMemo;

    private String WHYSDict;
    private String SGLXDict;
    private String SGJGDict;
    private String YXFWDict;
    private String Method;
    private String TLevel;
    private String DLevel;
    private String CtrPrincipal;

    private String LECD_L;
    private String LECD_E;
    private String LECD_C;
    private String LSD_L;
    private String LSD_S;

    public String getLECD_L() {
        if(TextUtils.isEmpty(LECD_L))
        {
            return "";
        }
        return LECD_L;
    }

    public String getLECD_E() {
        if(TextUtils.isEmpty(LECD_E))
        {
            return "";
        }
        return LECD_E;
    }

    public String getLECD_C() {
        if(TextUtils.isEmpty(LECD_C))
        {
            return "";
        }
        return LECD_C;
    }

    public String getLSD_L() {
        if(TextUtils.isEmpty(LSD_L))
        {
            return "";
        }
        return LSD_L;
    }

    public String getLSD_S() {
        if(TextUtils.isEmpty(LSD_S))
        {
            return "";
        }
        return LSD_S;
    }

    public String getWHYSDict() {
        if(TextUtils.isEmpty(WHYSDict))
        {
            return "";
        }
        return WHYSDict;
    }

    public String getSGLXDict() {
        if(TextUtils.isEmpty(SGLXDict))
        {
            return "";
        }
        return SGLXDict;
    }

    public String getSGJGDict() {
        if(TextUtils.isEmpty(SGJGDict))
        {
            return "";
        }
        return SGJGDict;
    }

    public String getYXFWDict() {
        if(TextUtils.isEmpty(YXFWDict))
        {
            return "";
        }
        return YXFWDict;
    }

    public String getMethod() {
        if(TextUtils.isEmpty(Method))
        {
            return "";
        }
        return Method;
    }

    public String getTLevel() {
        if(TextUtils.isEmpty(TLevel))
        {
            return "";
        }
        return TLevel;
    }

    public String getDLevel() {
        if(TextUtils.isEmpty(DLevel))
        {
            return "";
        }
        return DLevel;
    }

    public String getCtrPrincipal() {
        if(TextUtils.isEmpty(CtrPrincipal))
        {
            return "";
        }
        return CtrPrincipal;
    }

    public String getResultTime() {
        if(ResultTime==null)
        {
            return "";
        }
        return ResultTime;
    }

    public void setResultTime(String resultTime) {
        ResultTime = resultTime;
    }

    public int getTaskResult() {
        return TaskResult;
    }

    public void setTaskResult(int taskResult) {
        TaskResult = taskResult;
    }

    public String getTaskResultMemo() {
        if(TaskResultMemo==null)
        {
            return "";
        }
        return TaskResultMemo;
    }

    public void setTaskResultMemo(String taskResultMemo) {
        TaskResultMemo = taskResultMemo;
    }


}
