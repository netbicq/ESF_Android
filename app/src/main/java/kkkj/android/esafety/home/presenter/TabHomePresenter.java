package kkkj.android.esafety.home.presenter;

import com.orhanobut.logger.Logger;

import kkkj.android.esafety.home.contract.TabHomeContract;
import kkkj.android.esafety.home.model.DownloadDataModel;
import kkkj.android.esafety.home.model.GetEmpTaskByQRCoderModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class TabHomePresenter extends TabHomeContract.Presenter {

    @Override
    public void downloaddata() {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            Logger.d("downloaddata"+isViewAttached());
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        DownloadDataModel getTaskListModel = new DownloadDataModel();
        DownloadDataModel.Request request = new DownloadDataModel.Request();
        getTaskListModel.getResponse(request, new MvpCallback<DownloadDataModel.Response>(getView()) {
            @Override
            public void onSuccess(DownloadDataModel.Response data) {
                if (isViewAttached()) {
                    getView().downloaddataSuc(data.getData());
                }
            }
        });
    }
    @Override
    public void getEmpTaskByQRCoder(GetEmpTaskByQRCoderModel.Request request) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        GetEmpTaskByQRCoderModel getTaskListModel = new GetEmpTaskByQRCoderModel();
        getTaskListModel.getResponse(request, new MvpCallback<GetEmpTaskByQRCoderModel.Response>(getView()) {
            @Override
            public void onSuccess(GetEmpTaskByQRCoderModel.Response data) {
                if (isViewAttached()) {
                    getView().getEmpTaskByQRCoderSuc(data);
                }
            }
        });
    }
}
