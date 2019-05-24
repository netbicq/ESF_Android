package kkkj.android.esafety.menu.statistics.contract;


import kkkj.android.esafety.bean.PagerOfTimeOutTask;
import kkkj.android.esafety.bean.PagerOfTroubleCtrsPage;
import kkkj.android.esafety.menu.statistics.model.GetTimeOutTaskModel;
import kkkj.android.esafety.menu.statistics.model.GetTroubleCtrsPageModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class TimeOutTaskContract {
    public interface View extends MvpView
    {
        void getTimeOutTaskSuc(PagerOfTimeOutTask data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getTimeOutTask(GetTimeOutTaskModel.Request request);
    }
}
