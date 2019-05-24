package kkkj.android.esafety.menu.statistics.presenter;

import kkkj.android.esafety.menu.statistics.contract.DangerPointChildContract;
import kkkj.android.esafety.menu.statistics.model.GetDangerPointsPageModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class DangerPointChildPresenter extends DangerPointChildContract.Presenter {
    @Override
    public void getDangerPointsPage(GetDangerPointsPageModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetDangerPointsPageModel myTaskBillModel = new GetDangerPointsPageModel();
        myTaskBillModel.getResponse(request, new MvpCallback<GetDangerPointsPageModel.Response>(getView()) {
            @Override
            public void onSuccess(GetDangerPointsPageModel.Response data) {
                if (isViewAttached()){
                    getView().getDangerLevelsSuc(data.getData());
                }
            }
        });
    }
}
