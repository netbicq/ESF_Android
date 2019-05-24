package kkkj.android.esafety.home.contract;


import kkkj.android.esafety.home.model.GetTaskBillMastersOverByQRCoderModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class WindControlSContract {
    public interface View extends MvpView
    {
        void getTaskBillMastersOverByQRCoderSuc(GetTaskBillMastersOverByQRCoderModel.Response response);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getTaskBillMastersOverByQRCoder(GetTaskBillMastersOverByQRCoderModel.Request request);
    }
}
