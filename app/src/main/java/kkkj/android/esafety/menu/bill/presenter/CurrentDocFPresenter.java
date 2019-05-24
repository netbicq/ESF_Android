package kkkj.android.esafety.menu.bill.presenter;

import kkkj.android.esafety.menu.bill.contract.CurrentDocFContract;
import kkkj.android.esafety.menu.bill.model.DelTaskBillModel;
import kkkj.android.esafety.menu.bill.model.GetTaskBillsModel;
import kkkj.android.esafety.menu.bill.model.TaskBillOverModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class CurrentDocFPresenter extends CurrentDocFContract.Presenter {

    @Override
    public void gettaskbills() {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetTaskBillsModel myTaskBillModel = new GetTaskBillsModel();
        GetTaskBillsModel.Request request = new GetTaskBillsModel.Request();
        myTaskBillModel.getResponse(request, new MvpCallback<GetTaskBillsModel.Response>(getView()) {
            @Override
            public void onSuccess(GetTaskBillsModel.Response data) {

                if (isViewAttached()){
                    getView().gettaskbillsSuc(data.getData());
                }
            }
        });
    }

    @Override
    public void taskbillover(TaskBillOverModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        TaskBillOverModel myTaskBillModel = new TaskBillOverModel();
        myTaskBillModel.getResponse(request, new MvpCallback<TaskBillOverModel.Response>(getView()) {
            @Override
            public void onSuccess(TaskBillOverModel.Response data) {

                if (isViewAttached()){
                    getView().taskbilloverSuc();
                }
            }
        });
    }

    @Override
    public void deltaskbill(DelTaskBillModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        DelTaskBillModel myTaskBillModel = new DelTaskBillModel();
        myTaskBillModel.getResponse(request, new MvpCallback<DelTaskBillModel.Response>(getView()) {
            @Override
            public void onSuccess(DelTaskBillModel.Response data) {
                if (isViewAttached()){
                    getView().deltaskbillSuc();
                }
            }
        });
    }
}
