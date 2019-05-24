package kkkj.android.esafety.menu.statistics.contract;

import java.util.List;

import kkkj.android.esafety.menu.statistics.model.GetCtrMenuModel;
import kkkj.android.esafety.bean.TroubleCtrMenu;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class ControlsContract {
    public interface View extends MvpView
    {
        void getCtrMenuSuc(List<TroubleCtrMenu> data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getCtrMenu(GetCtrMenuModel.Request request);
    }
}
