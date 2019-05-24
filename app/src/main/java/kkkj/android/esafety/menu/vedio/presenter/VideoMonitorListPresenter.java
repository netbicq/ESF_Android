package kkkj.android.esafety.menu.vedio.presenter;


import kkkj.android.esafety.menu.vedio.contract.VideoMonitorListContract;
import kkkj.android.esafety.menu.vedio.model.GetVideoListModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class VideoMonitorListPresenter extends VideoMonitorListContract.Presenter {

    @Override
    public void getvideolist(GetVideoListModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetVideoListModel myTaskBillModel = new GetVideoListModel();
        myTaskBillModel.getResponse(request, new MvpCallback<GetVideoListModel.Response>(getView()) {
            @Override
            public void onSuccess(GetVideoListModel.Response data) {

                if (isViewAttached()){
                    getView().getvideolistSuc(data.getData());
                }
            }
        });
    }
}
