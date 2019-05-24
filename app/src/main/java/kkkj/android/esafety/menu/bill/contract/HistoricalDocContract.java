package kkkj.android.esafety.menu.bill.contract;

import java.util.List;

import kkkj.android.esafety.bean.TaskBillModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class HistoricalDocContract {
    public interface View extends MvpView
    {
        void gettaskbillsoverSuc(List<TaskBillModel> data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void gettaskbillsover();
    }
}
