package kkkj.android.esafety.menu.statistics.contract;

import kkkj.android.esafety.menu.statistics.model.GetDangerPointsPageModel;
import kkkj.android.esafety.bean.PagerOfAPPDangerPointView;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class DangerPointChildContract {
    public interface View extends MvpView
    {
        void getDangerLevelsSuc(PagerOfAPPDangerPointView data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getDangerPointsPage(GetDangerPointsPageModel.Request request);
    }
}
