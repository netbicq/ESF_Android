package kkkj.android.esafety.menu.task.contract;

import java.util.List;

import kkkj.android.esafety.bean.InsepctTaskByEmployee;
import kkkj.android.esafety.home.model.AddBillModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;
import kkkj.android.esafety.menu.task.model.*;
public class OvertimeTaskFContract {
    public interface View extends MvpView
    {
        void addbillSuc(AddBillModel.Response response);
        void gettimetaskSuc(List<InsepctTaskByEmployee> data);
        void getEmpTaskTimeOutByQRCoderSuc(GetEmpTaskTimeOutByQRCoderModel.Response response);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void gettimetask(GetTimeTaskModel.Request request);
        public abstract void addbill(AddBillModel.Request request);
        public abstract void getEmpTaskTimeOutByQRCoder(GetEmpTaskTimeOutByQRCoderModel.Request request);
    }
}
