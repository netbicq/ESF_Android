package kkkj.android.esafety.menu.work.model;

public class OpreateFlowUEModel {
    public boolean isFinishEnable() {
        return FinishEnable;
    }

    public void setFinishEnable(boolean finishEnable) {
        FinishEnable = finishEnable;
    }

    public boolean isStopEnable() {
        return StopEnable;
    }

    public void setStopEnable(boolean stopEnable) {
        StopEnable = stopEnable;
    }

    public boolean isReBackEnable() {
        return ReBackEnable;
    }

    public void setReBackEnable(boolean reBackEnable) {
        ReBackEnable = reBackEnable;
    }

    public boolean isLeftLine() {
        return LeftLine;
    }

    public void setLeftLine(boolean leftLine) {
        LeftLine = leftLine;
    }

    public boolean isRightLien() {
        return RightLien;
    }

    public void setRightLien(boolean rightLien) {
        RightLien = rightLien;
    }

    private boolean FinishEnable = false;//true 有完成按钮
    private boolean StopEnable = false;//true有终止按钮
    private boolean ReBackEnable = false;//true有回退按钮
    private boolean LeftLine = false;
    private boolean RightLien = false;//true有颜色
}
