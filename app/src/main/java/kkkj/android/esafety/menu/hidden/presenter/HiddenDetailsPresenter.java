package kkkj.android.esafety.menu.hidden.presenter;

import kkkj.android.esafety.menu.hidden.contract.HiddenDetailsContract;
import kkkj.android.esafety.model.DownLoadFileModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class HiddenDetailsPresenter extends HiddenDetailsContract.Presenter {
    @Override
    public void getfiles(DownLoadFileModel.Request request) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        DownLoadFileModel getTaskListModel = new DownLoadFileModel();
        getTaskListModel.getResponse(request, new MvpCallback<DownLoadFileModel.Response>(getView()) {
            @Override
            public void onSuccess(DownLoadFileModel.Response data) {

                if (isViewAttached()){
                    getView().getfilesSuc(data.getData());
                }
            }
        });
    }
}
