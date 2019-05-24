package kkkj.android.esafety.menu.temptask.presenter;


import kkkj.android.esafety.menu.temptask.contract.AddTemWorkContract;
import kkkj.android.esafety.menu.temptask.model.AddTempTaskModel;
import kkkj.android.esafety.menu.temptask.model.GetDangerSelectorModel;
import kkkj.android.esafety.menu.temptask.model.GetSelectorModel;
import kkkj.android.esafety.mvpInterface.MvpCallback;

public class AddTemWorkPresenter extends AddTemWorkContract.Presenter {
    @Override
    public void addtasksubject(AddTempTaskModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }

        if(!checkNetWork())
        {
            getView().showToast("请检查网络");
            return;
        }

        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        AddTempTaskModel addBillModel = new AddTempTaskModel();
        addBillModel.getResponse(request, new MvpCallback<AddTempTaskModel.Response>(getView()) {
            @Override
            public void onSuccess(AddTempTaskModel.Response data) {

                if (isViewAttached()){
                    getView().addtasksubjectSuc(data);
                }
            }
        });
    }

    @Override
    public void getselector() {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        if(!checkNetWork())
        {
            getView().showToast("请检查网络");
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        GetSelectorModel addBillModel = new GetSelectorModel();
        addBillModel.getResponse(null, new MvpCallback<GetSelectorModel.Response>(getView()) {
            @Override
            public void onSuccess(GetSelectorModel.Response data) {
                if (isViewAttached()){
                    getView().getselectorSuc(data);
                }

            }
        });
    }

    @Override
    public void getdangerselector(GetDangerSelectorModel.Request request) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
        if(!checkNetWork())
        {
            getView().showToast("请检查网络");
            return;
        }
        //显示正在加载进度条
        getView().showLoading();
        // 调用Model请求数据
        GetDangerSelectorModel addBillModel = new GetDangerSelectorModel();
        addBillModel.getResponse(request, new MvpCallback<GetDangerSelectorModel.Response>(getView()) {
            @Override
            public void onSuccess(GetDangerSelectorModel.Response data) {
                if (isViewAttached()){
                    getView().getdangerselectorSuc(data);
                }

            }
        });
    }
}
