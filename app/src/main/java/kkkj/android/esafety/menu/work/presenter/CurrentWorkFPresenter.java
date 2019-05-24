package kkkj.android.esafety.menu.work.presenter;


import kkkj.android.esafety.menu.work.contract.CurrentWorkFContract;
import kkkj.android.esafety.menu.work.model.GetCurrentListModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class CurrentWorkFPresenter extends CurrentWorkFContract.Presenter {
    @Override
    public void getcurrentlist() {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetCurrentListModel getCurrentListModel = new GetCurrentListModel();
        getCurrentListModel.getResponse(null, new MvpCallback<GetCurrentListModel.Response>(getView()) {
            @Override
            public void onSuccess(GetCurrentListModel.Response data) {

                if (isViewAttached()){
                    getView().getcurrentlistSuc(data.getData());
                }
            }
        });
    }
}
