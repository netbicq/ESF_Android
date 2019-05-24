package kkkj.android.esafety.menu.institution.presenter;


import kkkj.android.esafety.menu.institution.contract.RPlanContentContract;
import kkkj.android.esafety.menu.institution.model.GetdocSolutionModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class RPlanContentPresenter extends RPlanContentContract.Presenter {

    @Override
    public void getdocsolutionmodel(GetdocSolutionModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        GetdocSolutionModel getCurrentListModel = new GetdocSolutionModel();
        getCurrentListModel.getResponse(request, new MvpCallback<GetdocSolutionModel.Response>(getView()) {
            @Override
            public void onSuccess(GetdocSolutionModel.Response data) {

                if (isViewAttached()){
                    getView().getdocsolutionmodelSuc(data.getData());
                }
            }
        });
    }
}
