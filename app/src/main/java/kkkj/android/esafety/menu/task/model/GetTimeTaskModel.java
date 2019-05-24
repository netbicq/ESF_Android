package kkkj.android.esafety.menu.task.model;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.bean.InsepctTaskByEmployee;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;
import kkkj.android.esafety.utils.NetUtils;

public class GetTimeTaskModel extends MvpModel<GetTimeTaskModel.Request, GetTimeTaskModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        if (!NetUtils.checkNetWork()) {
            //没有网络的话 就访问本地数据库
            //没有网络的话 就访问本地数据库
            try {
                GetTimeTaskModel.Response response = new GetTimeTaskModel.Response();
                List<InsepctTaskByEmployee> list= LitePal.where("TimeOutHours > ?","0").find(InsepctTaskByEmployee.class);
                if (list.size() > 0) {
                    response.setData(list);
                    callback.onSuccess(response);
                } else {
//                    callback.onFailure("未查询到相关数据");
                }
                callback.onComplete();
            } catch (Exception e) {
                callback.onError(e);
            }
        } else {
            apiApp.gettimetask()
                    .subscribeOn(Schedulers.io())//IO线程加载数据
                    .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                    .subscribe(new Observer<Response>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(GetTimeTaskModel.Response response) {
                            if (response.getState() == RESPONSE_OK) {
                                callback.onSuccess(response);
                            } else {
                                callback.onFailure(response.getMsg());
                            }
                        }

                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                        }

                        @Override
                        public void onComplete() {
                            callback.onComplete();
                        }
                    });
        }
    }

    public static class Response extends ESafetyResponse {
        List<InsepctTaskByEmployee> data = new ArrayList<>();

        public List<InsepctTaskByEmployee> getData() {
            if(data==null)
            {
                return new ArrayList<>();
            }
            return data;
        }

        public void setData(List<InsepctTaskByEmployee> data) {
            this.data = data;
        }
    }
    public static class Request extends ESafetyRequest {

    }
}
