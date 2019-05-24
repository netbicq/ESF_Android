package kkkj.android.esafety.menu.task.contract;

import java.util.List;

import kkkj.android.esafety.bean.InsepctTaskByEmployee;
import kkkj.android.esafety.home.model.AddBillModel;
import kkkj.android.esafety.home.model.GetEmpTaskByQRCoderModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;
import kkkj.android.esafety.menu.task.model.*;
public class NormalTaskFContract {
    public interface View extends MvpView
    {
        void gettasklistSuc(List<InsepctTaskByEmployee> data);
        void addbillSuc(AddBillModel.Response response);
        void getEmpTaskByQRCoderSuc(GetEmpTaskByQRCoderModel.Response response);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void gettasklist(GetTaskListModel.Request request);
        public abstract void addbill(AddBillModel.Request request);
        public abstract void getEmpTaskByQRCoder(GetEmpTaskByQRCoderModel.Request request);
    }
}
