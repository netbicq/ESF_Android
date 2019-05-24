package kkkj.android.esafety.menu.task.presenter;

import kkkj.android.esafety.home.model.AddBillModel;
import kkkj.android.esafety.home.model.GetEmpTaskByQRCoderModel;
import kkkj.android.esafety.menu.task.contract.NormalTaskFContract;
import kkkj.android.esafety.menu.task.model.*;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class NormalTaskFPresenter extends NormalTaskFContract.Presenter {

    @Override
    public void gettasklist(GetTaskListModel.Request request) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetTaskListModel getTaskListModel = new GetTaskListModel();
        getTaskListModel.getResponse(request, new MvpCallback<GetTaskListModel.Response>(getView()) {
            @Override
            public void onSuccess(GetTaskListModel.Response data) {
                if (isViewAttached()) {
                    getView().gettasklistSuc(data.getData());
                }
            }
        });
    }

    @Override
    public void addbill(AddBillModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }

        if(!checkNetWork())
        {
            getView().showToast("请检查网络");
            return;
        }

        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        AddBillModel addBillModel = new AddBillModel();
        addBillModel.getResponse(request, new MvpCallback<AddBillModel.Response>(getView()) {
            @Override
            public void onSuccess(AddBillModel.Response data) {

                if (isViewAttached()){
                    getView().addbillSuc(data);
                }
            }
        });
    }

    @Override
    public void getEmpTaskByQRCoder(GetEmpTaskByQRCoderModel.Request request) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        GetEmpTaskByQRCoderModel getTaskListModel = new GetEmpTaskByQRCoderModel();
        getTaskListModel.getResponse(request, new MvpCallback<GetEmpTaskByQRCoderModel.Response>(getView()) {
            @Override
            public void onSuccess(GetEmpTaskByQRCoderModel.Response data) {
                if (isViewAttached()) {
                    getView().getEmpTaskByQRCoderSuc(data);
                }
            }
        });
    }
}
