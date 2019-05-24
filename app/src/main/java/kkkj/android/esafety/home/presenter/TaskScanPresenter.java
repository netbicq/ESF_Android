package kkkj.android.esafety.home.presenter;

import kkkj.android.esafety.home.contract.TaskScanContract;
import kkkj.android.esafety.home.model.AddBillModel;
import kkkj.android.esafety.home.model.GetEmpTaskByQRCoderModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class TaskScanPresenter extends TaskScanContract.Presenter {


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
