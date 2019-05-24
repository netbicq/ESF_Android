package kkkj.android.esafety.menu.bill.presenter;

import kkkj.android.esafety.menu.bill.contract.HistoricalDocContract;
import kkkj.android.esafety.menu.bill.model.GetTaskBillsOverModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class HistoricalDocPresenter extends HistoricalDocContract.Presenter {

    @Override
    public void gettaskbillsover() {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetTaskBillsOverModel myTaskBillModel = new GetTaskBillsOverModel();
        GetTaskBillsOverModel.Request request = new GetTaskBillsOverModel.Request();
        myTaskBillModel.getResponse(request, new MvpCallback<GetTaskBillsOverModel.Response>(getView()) {
            @Override
            public void onSuccess(GetTaskBillsOverModel.Response data) {
                if (isViewAttached()){
                    getView().gettaskbillsoverSuc(data.getData());
                }
            }
        });
    }

}
