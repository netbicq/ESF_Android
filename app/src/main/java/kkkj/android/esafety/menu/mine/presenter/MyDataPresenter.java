package kkkj.android.esafety.menu.mine.presenter;

import kkkj.android.esafety.menu.mine.contract.MyDataContract;
import kkkj.android.esafety.menu.mine.model.UserSetProfileModel;
import kkkj.android.esafety.model.UpLoadFileModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class MyDataPresenter extends MyDataContract.Presenter{
    @Override
    public void uploadfile(UpLoadFileModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        UpLoadFileModel loginModel = new UpLoadFileModel();
//        return loginModel.getResponseSync(request);
        loginModel.getResponse(request, new MvpCallback<UpLoadFileModel.Response>(getView()) {
            @Override
            public void onSuccess(UpLoadFileModel.Response data) {
                if (isViewAttached()){
                    getView().uploadfileSuc(data);
                }

            }
        });
    }

    @Override
    public void setprofile(UserSetProfileModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        UserSetProfileModel upLoadFileModel = new UserSetProfileModel();
        upLoadFileModel.getResponse(request, new MvpCallback<UserSetProfileModel.Response>(getView()) {
            @Override
            public void onSuccess(UserSetProfileModel.Response data) {

                if (isViewAttached()){
                    getView().setProFileSuc();
                }
            }
        });
    }
}
