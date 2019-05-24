package kkkj.android.esafety.menu.work.contract;

import java.util.List;

import kkkj.android.esafety.bean.OpreateBillByEmp;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class HistoricalWorkContract {
    public interface View extends MvpView
    {
        void getoverlistSuc(List<OpreateBillByEmp> data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getoverlist();
    }
}
