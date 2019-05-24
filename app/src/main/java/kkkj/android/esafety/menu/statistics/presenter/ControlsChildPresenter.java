package kkkj.android.esafety.menu.statistics.presenter;


import kkkj.android.esafety.menu.statistics.contract.ControlsChildContract;
import kkkj.android.esafety.menu.statistics.model.GetTroubleCtrsPageModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class ControlsChildPresenter extends ControlsChildContract.Presenter {
    @Override
    public void getTroubleCtrsPage(GetTroubleCtrsPageModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetTroubleCtrsPageModel myTaskBillModel = new GetTroubleCtrsPageModel();
        myTaskBillModel.getResponse(request, new MvpCallback<GetTroubleCtrsPageModel.Response>(getView()) {
            @Override
            public void onSuccess(GetTroubleCtrsPageModel.Response data) {
                if (isViewAttached()){
                    getView().getTroubleCtrsPageSuc(data.getData());
                }
            }
        });
    }
}
