package kkkj.android.esafety.home.contract;

import kkkj.android.esafety.home.model.AddBillModel;
import kkkj.android.esafety.home.model.GetEmpTaskByQRCoderModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class TaskScanContract {
    public interface View extends MvpView
    {
        void addbillSuc(AddBillModel.Response response);
        void getEmpTaskByQRCoderSuc(GetEmpTaskByQRCoderModel.Response response);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void addbill(AddBillModel.Request request);
        public abstract void getEmpTaskByQRCoder(GetEmpTaskByQRCoderModel.Request request);
    }
}
