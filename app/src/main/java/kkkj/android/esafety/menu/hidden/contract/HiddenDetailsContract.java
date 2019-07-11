package kkkj.android.esafety.menu.hidden.contract;

import java.util.List;
import kkkj.android.esafety.bean.Bll_AttachFile;
import kkkj.android.esafety.menu.bill.contract.TaskResultContract;
import kkkj.android.esafety.model.DownLoadFileModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class HiddenDetailsContract {
    public interface View extends MvpView
    {
        void getfilesSuc(List<Bll_AttachFile> data);
    }
    public static abstract class Presenter extends MvpPresenter<HiddenDetailsContract.View> {
        public abstract void getfiles(DownLoadFileModel.Request request);
    }
}

