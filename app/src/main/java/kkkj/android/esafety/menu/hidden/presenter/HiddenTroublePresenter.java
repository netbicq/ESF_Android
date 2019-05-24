package kkkj.android.esafety.menu.hidden.presenter;

import kkkj.android.esafety.menu.hidden.contract.HiddenTroubleContract;
import kkkj.android.esafety.menu.hidden.model.AddTroubleCtrFlowModel;
import kkkj.android.esafety.menu.hidden.model.FiledModel;
import kkkj.android.esafety.menu.hidden.model.GetTroubleCtrModel;
import kkkj.android.esafety.menu.hidden.model.QuickHandleCtrModel;
import kkkj.android.esafety.menu.hidden.model.TransferPrincipalModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class HiddenTroublePresenter extends HiddenTroubleContract.Presenter {
    @Override
    public void getTroubleCtr() {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetTroubleCtrModel getTroubleCtrModel = new GetTroubleCtrModel();
        GetTroubleCtrModel.Request request  =new GetTroubleCtrModel.Request();
        getTroubleCtrModel.getResponse(request, new MvpCallback<GetTroubleCtrModel.Response>(getView()) {
            @Override
            public void onSuccess(GetTroubleCtrModel.Response data) {
                if (isViewAttached()){
                    getView().getTroubleCtrSuc(data.getData());
                }
            }
        });
    }

    @Override
    public void filed(FiledModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        FiledModel getTroubleCtrModel = new FiledModel();
        getTroubleCtrModel.getResponse(request, new MvpCallback<FiledModel.Response>(getView()) {
            @Override
            public void onSuccess(FiledModel.Response data) {
                if (isViewAttached()){
                    getView().filedSuc(data.isData());
                }
            }
        });
    }


    @Override
    public void transferPrincipal(TransferPrincipalModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        TransferPrincipalModel getTroubleCtrModel = new TransferPrincipalModel();
        getTroubleCtrModel.getResponse(request, new MvpCallback<TransferPrincipalModel.Response>(getView()) {
            @Override
            public void onSuccess(TransferPrincipalModel.Response data) {
                if (isViewAttached()){
                    getView().transferPrincipalSuc(data.isData());
                }
            }
        });
    }

    @Override
    public void quickHandleCtr(QuickHandleCtrModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        QuickHandleCtrModel getTroubleCtrModel = new QuickHandleCtrModel();
        getTroubleCtrModel.getResponse(request, new MvpCallback<QuickHandleCtrModel.Response>(getView()) {
            @Override
            public void onSuccess(QuickHandleCtrModel.Response data) {
                if (isViewAttached()){
                    getView().quickHandleCtrSuc(data.isData());
                }
            }
        });
    }

    @Override
    public void addTroubleCtrFlow(AddTroubleCtrFlowModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        AddTroubleCtrFlowModel getTroubleCtrModel = new AddTroubleCtrFlowModel();
        getTroubleCtrModel.getResponse(request, new MvpCallback<AddTroubleCtrFlowModel.Response>(getView()) {
            @Override
            public void onSuccess(AddTroubleCtrFlowModel.Response data) {
                if (isViewAttached()){
                    getView().addTroubleCtrFlowSuc(data.isData());
                }
            }
        });
    }
}
