package kkkj.android.esafety.menu.institution.contract;

import java.util.List;

import kkkj.android.esafety.bean.Basic_Dict;
import kkkj.android.esafety.bean.PhoneDocSolutionView;
import kkkj.android.esafety.menu.institution.model.GetdocSolutionListModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;


public class ReservePlanContract {
    public interface View extends MvpView
    {
        void getdocsolutionlistSuc(List<PhoneDocSolutionView> data);
        void getdangerdict(List<Basic_Dict> data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getdocsolutionlist(GetdocSolutionListModel.Request request);
        public abstract void getdangerdict();

    }
}
