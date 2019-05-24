package kkkj.android.esafety.menu.institution.contract;


import kkkj.android.esafety.bean.PhoneDocSolutionModelView;
import kkkj.android.esafety.menu.institution.model.GetdocSolutionModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class RPlanContentContract {
    public interface View extends MvpView
    {
        void getdocsolutionmodelSuc(PhoneDocSolutionModelView data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getdocsolutionmodel(GetdocSolutionModel.Request request);
    }
}
