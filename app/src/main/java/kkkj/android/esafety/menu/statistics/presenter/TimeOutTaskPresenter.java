package kkkj.android.esafety.menu.statistics.presenter;


import kkkj.android.esafety.menu.statistics.contract.TimeOutTaskContract;
import kkkj.android.esafety.menu.statistics.model.GetTimeOutTaskModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class TimeOutTaskPresenter extends TimeOutTaskContract.Presenter {

    @Override
    public void getTimeOutTask(GetTimeOutTaskModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetTimeOutTaskModel myTaskBillModel = new GetTimeOutTaskModel();
        myTaskBillModel.getResponse(request, new MvpCallback<GetTimeOutTaskModel.Response>(getView()) {
            @Override
            public void onSuccess(GetTimeOutTaskModel.Response data) {
                if (isViewAttached()){
                    getView().getTimeOutTaskSuc(data.getData());
                }
            }
        });
    }
}
