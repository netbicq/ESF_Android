package kkkj.android.esafety.menu.statistics.presenter;

import kkkj.android.esafety.menu.statistics.contract.DangerPointContract;
import kkkj.android.esafety.menu.statistics.model.GetDangerLevelsModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class DangerPointPresenter extends DangerPointContract.Presenter {
    @Override
    public void getDangerLevels(GetDangerLevelsModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        GetDangerLevelsModel myTaskBillModel = new GetDangerLevelsModel();
        myTaskBillModel.getResponse(request, new MvpCallback<GetDangerLevelsModel.Response>(getView()) {
            @Override
            public void onSuccess(GetDangerLevelsModel.Response data) {
                if (isViewAttached()){
                    getView().getDangerLevelsSuc(data.getData());
                }
            }
        });
    }
}
