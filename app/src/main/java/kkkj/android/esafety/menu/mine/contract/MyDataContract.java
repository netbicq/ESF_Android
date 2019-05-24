package kkkj.android.esafety.menu.mine.contract;

import kkkj.android.esafety.menu.mine.model.UserSetProfileModel;
import kkkj.android.esafety.model.UpLoadFileModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class MyDataContract {
    public interface View extends MvpView
    {
        void uploadfileSuc(UpLoadFileModel.Response response);
        void setProFileSuc();
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void uploadfile(UpLoadFileModel.Request request);
        public abstract void setprofile(UserSetProfileModel.Request request);
    }
}
