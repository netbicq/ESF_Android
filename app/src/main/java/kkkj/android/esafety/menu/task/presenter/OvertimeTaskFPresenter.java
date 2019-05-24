package kkkj.android.esafety.menu.task.presenter;

import kkkj.android.esafety.home.model.AddBillModel;
import kkkj.android.esafety.menu.task.contract.OvertimeTaskFContract;
import kkkj.android.esafety.menu.task.model.GetEmpTaskTimeOutByQRCoderModel;
import kkkj.android.esafety.menu.task.model.GetTimeTaskModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class OvertimeTaskFPresenter extends OvertimeTaskFContract.Presenter {

    @Override
    public void gettimetask(GetTimeTaskModel.Request request) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetTimeTaskModel getTimeTaskModel = new GetTimeTaskModel();
        getTimeTaskModel.getResponse(request, new MvpCallback<GetTimeTaskModel.Response>(getView()) {
            @Override
            public void onSuccess(GetTimeTaskModel.Response data) {

                if (isViewAttached()){
                    getView().gettimetaskSuc(data.getData());
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
    public void getEmpTaskTimeOutByQRCoder(GetEmpTaskTimeOutByQRCoderModel.Request request) {
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
        GetEmpTaskTimeOutByQRCoderModel addBillModel = new GetEmpTaskTimeOutByQRCoderModel();
        addBillModel.getResponse(request, new MvpCallback<GetEmpTaskTimeOutByQRCoderModel.Response>(getView()) {
            @Override
            public void onSuccess(GetEmpTaskTimeOutByQRCoderModel.Response data) {
                if (isViewAttached()){
                    getView().getEmpTaskTimeOutByQRCoderSuc(data);
                }

            }
        });
    }
}
