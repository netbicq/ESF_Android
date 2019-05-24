package kkkj.android.esafety.menu.institution.presenter;


import kkkj.android.esafety.menu.institution.contract.InstitutionContentContract;
import kkkj.android.esafety.menu.institution.model.GetdoCinsModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class InstitutionContentPresenter extends InstitutionContentContract.Presenter {

    @Override
    public void getdocins(GetdoCinsModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        GetdoCinsModel getCurrentListModel = new GetdoCinsModel();
        getCurrentListModel.getResponse(request, new MvpCallback<GetdoCinsModel.Response>(getView()) {
            @Override
            public void onSuccess(GetdoCinsModel.Response data) {
                if (isViewAttached()){
                    getView().getdocinsSuc(data.getData());
                }
            }
        });
    }
}
