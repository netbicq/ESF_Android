package kkkj.android.esafety.menu.institution.presenter;


import kkkj.android.esafety.menu.institution.contract.ReservePlanContract;
import kkkj.android.esafety.menu.institution.model.GetDangerDictModel;
import kkkj.android.esafety.menu.institution.model.GetdocSolutionListModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class ReservePlanPresenter extends ReservePlanContract.Presenter {
    @Override
    public void getdocsolutionlist(GetdocSolutionListModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetdocSolutionListModel myTaskBillModel = new GetdocSolutionListModel();
        myTaskBillModel.getResponse(request, new MvpCallback<GetdocSolutionListModel.Response>(getView()) {
            @Override
            public void onSuccess(GetdocSolutionListModel.Response data) {

                if (isViewAttached()){
                    getView().getdocsolutionlistSuc(data.getData());
                }
            }
        });
    }

    @Override
    public void getdangerdict() {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetDangerDictModel myTaskBillModel = new GetDangerDictModel();
        myTaskBillModel.getResponse(null, new MvpCallback<GetDangerDictModel.Response>(getView()) {
            @Override
            public void onSuccess(GetDangerDictModel.Response data) {

                if (isViewAttached()){
                    getView().getdangerdict(data.getData());
                }
            }
        });
    }
}
