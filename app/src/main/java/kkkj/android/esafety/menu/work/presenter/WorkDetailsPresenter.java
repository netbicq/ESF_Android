package kkkj.android.esafety.menu.work.presenter;

import kkkj.android.esafety.menu.work.contract.WorkDetailsContract;
import kkkj.android.esafety.menu.work.model.BillFlowSetModel;
import kkkj.android.esafety.menu.work.model.GetOpreateFlowModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class WorkDetailsPresenter extends WorkDetailsContract.Presenter {

    @Override
    public void getopreateflowmodel(GetOpreateFlowModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        GetOpreateFlowModel getCurrentListModel = new GetOpreateFlowModel();
        getCurrentListModel.getResponse(request, new MvpCallback<GetOpreateFlowModel.Response>(getView()) {
            @Override
            public void onSuccess(GetOpreateFlowModel.Response data) {

                if (isViewAttached()){
                    getView().getopreateflowmodelSuc(data.getData());
                }
            }
        });
    }

    @Override
    public void billflowset(BillFlowSetModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        BillFlowSetModel getCurrentListModel = new BillFlowSetModel();
        getCurrentListModel.getResponse(request, new MvpCallback<BillFlowSetModel.Response>(getView()) {
            @Override
            public void onSuccess(BillFlowSetModel.Response data) {

                if (isViewAttached()){
                    getView().billflowsetSuc(data);
                }
            }
        });
    }
}
