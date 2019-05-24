package kkkj.android.esafety.menu.hidden.contract;

import java.util.List;

import kkkj.android.esafety.bean.APPTroubleCtrView;
import kkkj.android.esafety.menu.hidden.model.AddTroubleCtrFlowModel;
import kkkj.android.esafety.menu.hidden.model.FiledModel;
import kkkj.android.esafety.menu.hidden.model.QuickHandleCtrModel;
import kkkj.android.esafety.menu.hidden.model.TransferPrincipalModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class HiddenTroubleContract {
    public interface View extends MvpView
    {
        void getTroubleCtrSuc(List<APPTroubleCtrView> data);
        void filedSuc(boolean result);
        void transferPrincipalSuc(boolean result);
        void quickHandleCtrSuc(boolean result);
        void addTroubleCtrFlowSuc(boolean result);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getTroubleCtr();
        public abstract void filed(FiledModel.Request request);
        public abstract void transferPrincipal(TransferPrincipalModel.Request request);//转让责任人
        public abstract void quickHandleCtr(QuickHandleCtrModel.Request request);//快速处理
        public abstract void addTroubleCtrFlow(AddTroubleCtrFlowModel.Request request);//验收/申请验收
    }
}
