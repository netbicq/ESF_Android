package kkkj.android.esafety.menu.statistics.contract;


import kkkj.android.esafety.menu.statistics.model.GetTroubleCtrsPageModel;
import kkkj.android.esafety.bean.PagerOfTroubleCtrsPage;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class ControlsChildContract {
    public interface View extends MvpView
    {
        void getTroubleCtrsPageSuc(PagerOfTroubleCtrsPage data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getTroubleCtrsPage(GetTroubleCtrsPageModel.Request request);
    }
}
