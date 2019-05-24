package kkkj.android.esafety.menu.work.presenter;

import kkkj.android.esafety.menu.work.contract.HistoricalWorkContract;
import kkkj.android.esafety.menu.work.model.GetOverListModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class HistoricalWorkPresenter extends HistoricalWorkContract.Presenter {
    @Override
    public void getoverlist() {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetOverListModel myTaskBillModel = new GetOverListModel();
        myTaskBillModel.getResponse(null, new MvpCallback<GetOverListModel.Response>(getView()) {
            @Override
            public void onSuccess(GetOverListModel.Response data) {

                if (isViewAttached()){
                    getView().getoverlistSuc(data.getData());
                }
            }
        });
    }
}
