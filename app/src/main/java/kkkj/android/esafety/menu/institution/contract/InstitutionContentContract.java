package kkkj.android.esafety.menu.institution.contract;

import kkkj.android.esafety.bean.PhoneDocInstitutionModelView;
import kkkj.android.esafety.menu.institution.model.GetdoCinsModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class InstitutionContentContract {
    public interface View extends MvpView
    {
        void getdocinsSuc(PhoneDocInstitutionModelView data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getdocins(GetdoCinsModel.Request request);
    }
}
