package kkkj.android.esafety.menu.bill.contract;


import java.util.List;

import kkkj.android.esafety.bean.Bll_AttachFile;
import kkkj.android.esafety.bean.SubResultView;
import kkkj.android.esafety.menu.bill.model.AddTaskSubjectModel;
import kkkj.android.esafety.menu.bill.model.GetSubResultModel;
import kkkj.android.esafety.model.DownLoadFileModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class TaskResultContract {
    public interface View extends MvpView
    {
        void getfilesSuc(List<Bll_AttachFile> data);
        void addtasksubjectSuc();
        void getsubresultSuc(SubResultView data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getfiles(DownLoadFileModel.Request request);
        public abstract void getsubresult(GetSubResultModel.Request request);
        public abstract void addtasksubject(AddTaskSubjectModel.Request request);
    }
}
