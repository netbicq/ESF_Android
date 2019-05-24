package kkkj.android.esafety.menu.task.model;
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


public class GetEmpTaskTimeOutByQRCoderModel extends MvpModel<GetEmpTaskTimeOutByQRCoderModel.Request, GetEmpTaskTimeOutByQRCoderModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        apiApp.getEmpTaskTimeOutByQRCoder(request.getDangerPointID())
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GetEmpTaskTimeOutByQRCoderModel.Response response) {
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
        List<InsepctTaskByEmployee> data = new ArrayList<>();

        public List<InsepctTaskByEmployee> getData() {
            if (data == null) {
                return new ArrayList<>();
            }
            return data;
        }

        public void setData(List<InsepctTaskByEmployee> data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {
        String dangerPointID = "";

        public String getDangerPointID() {
            return dangerPointID;
        }

        public void setDangerPointID(String dangerPointID) {
            this.dangerPointID = dangerPointID;
        }
    }
}
