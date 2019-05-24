package kkkj.android.esafety.menu.bill.presenter;
import kkkj.android.esafety.menu.bill.contract.TaskResultContract;
import kkkj.android.esafety.menu.bill.model.AddTaskSubjectModel;
import kkkj.android.esafety.menu.bill.model.GetSubResultModel;
import kkkj.android.esafety.model.DownLoadFileModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class TaskResultPresenter extends TaskResultContract.Presenter {

    @Override
    public void getfiles(DownLoadFileModel.Request request) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        DownLoadFileModel getTaskListModel = new DownLoadFileModel();
        getTaskListModel.getResponse(request, new MvpCallback<DownLoadFileModel.Response>(getView()) {
            @Override
            public void onSuccess(DownLoadFileModel.Response data) {

                if (isViewAttached()){
                    getView().getfilesSuc(data.getData());
                }
            }
        });
    }

    @Override
    public void getsubresult(GetSubResultModel.Request request) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        GetSubResultModel getTaskListModel = new GetSubResultModel();
        getTaskListModel.getResponse(request, new MvpCallback<GetSubResultModel.Response>(getView()) {
            @Override
            public void onSuccess(GetSubResultModel.Response data) {
                if (isViewAttached()){
                    getView().getsubresultSuc(data.getData());
                }

            }
        });
    }

    @Override
    public void addtasksubject(AddTaskSubjectModel.Request request) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        AddTaskSubjectModel getTaskListModel = new AddTaskSubjectModel();
        getTaskListModel.getResponse(request, new MvpCallback<AddTaskSubjectModel.Response>(getView()) {
            @Override
            public void onSuccess(AddTaskSubjectModel.Response data) {
                if (isViewAttached()){
                    getView().addtasksubjectSuc();
                }
            }
        });
    }
}
