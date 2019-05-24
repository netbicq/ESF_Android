package kkkj.android.esafety.menu.hidden.contract;


import kkkj.android.esafety.menu.hidden.model.ChangeDangerLevelModel;
import kkkj.android.esafety.menu.hidden.model.ChangeLevelModel;
import kkkj.android.esafety.menu.hidden.model.DelayFinishTimeModel;
import kkkj.android.esafety.menu.hidden.model.HandleCtrModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class HandleCtrContract {
    public interface View extends MvpView
    {
        void delayFinishTimeSuc(boolean result);
        void changeLevelSuc(boolean result);
        void changeDangerLevelSuc(boolean result);
        void handleCtr(boolean result);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void delayFinishTime(DelayFinishTimeModel.Request request);
        public abstract void changeLevel(ChangeLevelModel.Request request);
        public abstract void changeDangerLevel(ChangeDangerLevelModel.Request request);
        public abstract void handleCtr(HandleCtrModel.Request request);
    }
}
