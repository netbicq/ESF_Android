package kkkj.android.esafety.menu.statistics.presenter;

import kkkj.android.esafety.menu.statistics.contract.ControlsContract;
import kkkj.android.esafety.menu.statistics.model.GetCtrMenuModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class ControlsPresenter extends ControlsContract.Presenter {
    @Override
    public void getCtrMenu(GetCtrMenuModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        GetCtrMenuModel myTaskBillModel = new GetCtrMenuModel();
        myTaskBillModel.getResponse(request, new MvpCallback<GetCtrMenuModel.Response>(getView()) {
            @Override
            public void onSuccess(GetCtrMenuModel.Response data) {
                if (isViewAttached()){
                    getView().getCtrMenuSuc(data.getData());
                }
            }
        });
    }
}
