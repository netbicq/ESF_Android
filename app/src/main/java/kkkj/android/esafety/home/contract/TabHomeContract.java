package kkkj.android.esafety.home.contract;


import kkkj.android.esafety.bean.DownloadData;
import kkkj.android.esafety.home.model.GetEmpTaskByQRCoderModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class TabHomeContract {
    public interface View extends MvpView
    {
        void downloaddataSuc(DownloadData data);
        void getEmpTaskByQRCoderSuc(GetEmpTaskByQRCoderModel.Response response);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void downloaddata();
        public abstract void getEmpTaskByQRCoder(GetEmpTaskByQRCoderModel.Request request);
    }
}
