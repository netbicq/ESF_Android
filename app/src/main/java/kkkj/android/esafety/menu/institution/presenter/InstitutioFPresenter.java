package kkkj.android.esafety.menu.institution.presenter;


import kkkj.android.esafety.menu.institution.contract.InstitutioFContract;
import kkkj.android.esafety.menu.institution.model.GetDocinsListModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class InstitutioFPresenter extends InstitutioFContract.Presenter {
    @Override
    public void getdocinslist() {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetDocinsListModel getCurrentListModel = new GetDocinsListModel();
        getCurrentListModel.getResponse(null, new MvpCallback<GetDocinsListModel.Response>(getView()) {
            @Override
            public void onSuccess(GetDocinsListModel.Response data) {

                if (isViewAttached()) {
                    getView().getdocinslistSuc(data.getData());
                }
            }
        });
    }
}
