package kkkj.android.esafety.menu.hidden.presenter;


import kkkj.android.esafety.menu.hidden.contract.HandleCtrContract;
import kkkj.android.esafety.menu.hidden.model.ChangeDangerLevelModel;
import kkkj.android.esafety.menu.hidden.model.ChangeLevelModel;
import kkkj.android.esafety.menu.hidden.model.DelayFinishTimeModel;
import kkkj.android.esafety.menu.hidden.model.HandleCtrModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class HandleCtrPresenter extends HandleCtrContract.Presenter {
    @Override
    public void delayFinishTime(DelayFinishTimeModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        DelayFinishTimeModel getTroubleCtrModel = new DelayFinishTimeModel();
        getTroubleCtrModel.getResponse(request, new MvpCallback<DelayFinishTimeModel.Response>(getView()) {
            @Override
            public void onSuccess(DelayFinishTimeModel.Response data) {
                if (isViewAttached()){
                    getView().delayFinishTimeSuc(data.isData());
                }
            }
        });
    }

    @Override
    public void changeLevel(ChangeLevelModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        ChangeLevelModel getTroubleCtrModel = new ChangeLevelModel();
        getTroubleCtrModel.getResponse(request, new MvpCallback<ChangeLevelModel.Response>(getView()) {
            @Override
            public void onSuccess(ChangeLevelModel.Response data) {
                if (isViewAttached()){
                    getView().changeLevelSuc(data.isData());
                }
            }
        });
    }

    @Override
    public void changeDangerLevel(ChangeDangerLevelModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        ChangeDangerLevelModel getTroubleCtrModel = new ChangeDangerLevelModel();
        getTroubleCtrModel.getResponse(request, new MvpCallback<ChangeDangerLevelModel.Response>(getView()) {
            @Override
            public void onSuccess(ChangeDangerLevelModel.Response data) {
                if (isViewAttached()){
                    getView().changeDangerLevelSuc(data.isData());
                }
            }
        });
    }

    @Override
    public void handleCtr(HandleCtrModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        HandleCtrModel getTroubleCtrModel = new HandleCtrModel();
        getTroubleCtrModel.getResponse(request, new MvpCallback<HandleCtrModel.Response>(getView()) {
            @Override
            public void onSuccess(HandleCtrModel.Response data) {
                if (isViewAttached()){
                    getView().handleCtr(data.isData());
                }
            }
        });
    }
}
