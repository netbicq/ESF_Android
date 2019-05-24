package kkkj.android.esafety.menu.work.contract;

import kkkj.android.esafety.menu.work.model.BillFlowSetModel;
import kkkj.android.esafety.menu.work.model.GetOpreateFlowModel;
import kkkj.android.esafety.menu.work.model.OpreateBillFlowModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class WorkDetailsContract {
    public interface View extends MvpView
    {
        void getopreateflowmodelSuc(OpreateBillFlowModel data);
        void billflowsetSuc(BillFlowSetModel.Response response);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getopreateflowmodel(GetOpreateFlowModel.Request request);
        public abstract void billflowset(BillFlowSetModel.Request request);

    }
}
