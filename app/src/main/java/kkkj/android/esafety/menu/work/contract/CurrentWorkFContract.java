package kkkj.android.esafety.menu.work.contract;

import java.util.List;

import kkkj.android.esafety.bean.OpreateBillByEmp;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class CurrentWorkFContract {
    public interface View extends MvpView
    {
        void getcurrentlistSuc(List<OpreateBillByEmp> data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getcurrentlist();
    }
}
