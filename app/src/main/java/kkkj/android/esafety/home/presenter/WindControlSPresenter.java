package kkkj.android.esafety.home.presenter;

import kkkj.android.esafety.home.contract.WindControlSContract;
import kkkj.android.esafety.home.model.GetTaskBillMastersOverByQRCoderModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class WindControlSPresenter extends WindControlSContract.Presenter {
    @Override
    public void getTaskBillMastersOverByQRCoder(GetTaskBillMastersOverByQRCoderModel.Request request) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
//        getView().showLoading();
        // 调用Model请求数据
        GetTaskBillMastersOverByQRCoderModel getTaskListModel = new GetTaskBillMastersOverByQRCoderModel();
        getTaskListModel.getResponse(request, new MvpCallback<GetTaskBillMastersOverByQRCoderModel.Response>(getView()) {
            @Override
            public void onSuccess(GetTaskBillMastersOverByQRCoderModel.Response data) {
                if (isViewAttached()) {
                    getView().getTaskBillMastersOverByQRCoderSuc(data);
                }
            }
        });
    }
}
