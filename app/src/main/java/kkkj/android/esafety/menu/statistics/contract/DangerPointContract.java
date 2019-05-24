package kkkj.android.esafety.menu.statistics.contract;

import java.util.List;

import kkkj.android.esafety.menu.statistics.model.GetDangerLevelsModel;
import kkkj.android.esafety.bean.StatisticsDangerLevel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class DangerPointContract {
    public interface View extends MvpView
    {
        void getDangerLevelsSuc(List<StatisticsDangerLevel> data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getDangerLevels(GetDangerLevelsModel.Request request);
    }
}
