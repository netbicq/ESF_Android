package kkkj.android.esafety.menu.institution.contract;

import java.util.List;

import kkkj.android.esafety.bean.PhoneDocInstitutionView;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;


public class InstitutioFContract {
    public interface View extends MvpView
    {
        void getdocinslistSuc(List<PhoneDocInstitutionView> data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getdocinslist();
    }
}
