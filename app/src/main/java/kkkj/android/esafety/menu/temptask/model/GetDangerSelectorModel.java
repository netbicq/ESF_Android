package kkkj.android.esafety.menu.temptask.model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.bean.CSub;
import kkkj.android.esafety.bean.Sub;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;

public class GetDangerSelectorModel extends MvpModel<GetDangerSelectorModel.Request, GetDangerSelectorModel.Response> {
    @Override
    public void getResponse(GetDangerSelectorModel.Request request, MvpCallback<Response> callback) {
        apiApp.getdangerselector(request).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GetDangerSelectorModel.Response response) {
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

    public static class Response extends ESafetyResponse {
        List<CSub> data = new ArrayList<>();

        public List<CSub> getData() {
            if(data==null)
            {
                return new ArrayList<>();
            }
            return data;
        }

        public void setData(List<CSub> data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {
        String DangerPointID = "";

        public String getDangerPointID() {
            if(DangerPointID==null)
            {
                return "";

            }
            return DangerPointID;
        }
        public void setDangerPointID(String dangerPointID) {
            DangerPointID = dangerPointID;
        }
    }
}
