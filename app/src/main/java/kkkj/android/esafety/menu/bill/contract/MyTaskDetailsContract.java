package kkkj.android.esafety.menu.bill.contract;

import java.util.List;

import kkkj.android.esafety.bean.TaskSubjectView;
import kkkj.android.esafety.menu.bill.model.AddTaskSubjectModel;
import kkkj.android.esafety.menu.bill.model.DelSubResultModel;
import kkkj.android.esafety.menu.bill.model.GetSubjectsModel;
import kkkj.android.esafety.menu.bill.model.GetTaskSuboverModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class MyTaskDetailsContract {
    public interface View extends MvpView
    {
        void getsubjectsSuc(List<TaskSubjectView> data);
        void gettasksuboverSuc(List<TaskSubjectView> data);
        void delsubresultSuc();
        void addtasksubjectSuc();
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getsubjects(GetSubjectsModel.Request request);
        public abstract void gettasksubover(GetTaskSuboverModel.Request request);
        public abstract void delsubresult(DelSubResultModel.Request request);
        public abstract void addtasksubject(AddTaskSubjectModel.Request request);
    }
}
