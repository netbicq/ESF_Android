package kkkj.android.esafety.menu.temptask.contract;

import kkkj.android.esafety.menu.temptask.model.AddTempTaskModel;
import kkkj.android.esafety.menu.temptask.model.GetDangerSelectorModel;
import kkkj.android.esafety.menu.temptask.model.GetSelectorModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class AddTemWorkContract {
    public interface View extends MvpView
    {
        void addtasksubjectSuc(AddTempTaskModel.Response response);
        void getselectorSuc(GetSelectorModel.Response response);
        void getdangerselectorSuc(GetDangerSelectorModel.Response response);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void addtasksubject(AddTempTaskModel.Request request);
        public abstract void getselector();
        public abstract void getdangerselector(GetDangerSelectorModel.Request request);
    }
}
