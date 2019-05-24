package kkkj.android.esafety.menu.bill.contract;

import java.util.List;

import kkkj.android.esafety.bean.TaskBillModel;
import kkkj.android.esafety.menu.bill.model.DelTaskBillModel;
import kkkj.android.esafety.menu.bill.model.TaskBillOverModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class CurrentDocFContract {
    public interface View extends MvpView
    {
        void gettaskbillsSuc(List<TaskBillModel> data);
        void deltaskbillSuc();
        void taskbilloverSuc();
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void gettaskbills();
        public abstract void taskbillover(TaskBillOverModel.Request request);
        public abstract void deltaskbill(DelTaskBillModel.Request request);
    }
}
