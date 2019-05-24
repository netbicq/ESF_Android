package kkkj.android.esafety.menu.bill.presenter;

import kkkj.android.esafety.menu.bill.contract.MyTaskDetailsContract;
import kkkj.android.esafety.menu.bill.model.AddTaskSubjectModel;
import kkkj.android.esafety.menu.bill.model.DelSubResultModel;
import kkkj.android.esafety.menu.bill.model.GetSubjectsModel;
import kkkj.android.esafety.menu.bill.model.GetTaskSuboverModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class MyTaskDetailsPresenter extends MyTaskDetailsContract.Presenter {

    @Override
    public void getsubjects(GetSubjectsModel.Request request) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetSubjectsModel getTaskListModel = new GetSubjectsModel();
        getTaskListModel.getResponse(request, new MvpCallback<GetSubjectsModel.Response>(getView()) {
            @Override
            public void onSuccess(GetSubjectsModel.Response data) {

                if (isViewAttached()){
                    getView().getsubjectsSuc(data.getData());
                }
            }
        });

    }

    @Override
    public void gettasksubover(GetTaskSuboverModel.Request request) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetTaskSuboverModel getTaskListModel = new GetTaskSuboverModel();
        getTaskListModel.getResponse(request, new MvpCallback<GetTaskSuboverModel.Response>(getView()) {
            @Override
            public void onSuccess(GetTaskSuboverModel.Response data) {
                if (isViewAttached()){
                    getView().gettasksuboverSuc(data.getData());
                }

            }
        });
    }

    @Override
    public void delsubresult(DelSubResultModel.Request request) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        DelSubResultModel getTaskListModel = new DelSubResultModel();
        getTaskListModel.getResponse(request, new MvpCallback<DelSubResultModel.Response>(getView()) {
            @Override
            public void onSuccess(DelSubResultModel.Response data) {

                if (isViewAttached()){
                    getView().delsubresultSuc();
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
//        getView().showLoading();
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
