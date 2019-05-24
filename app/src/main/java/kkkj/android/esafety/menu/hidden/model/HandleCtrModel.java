package kkkj.android.esafety.menu.hidden.model;

import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HandleCtrModel extends MvpModel<HandleCtrModel.Request, HandleCtrModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        apiApp.handleCtr(request)
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(HandleCtrModel.Response response) {
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
        boolean data;

        public boolean isData() {
            return data;
        }

        public void setData(boolean data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {
        String CtrID="";
        String ExecutorID="";
        String AcceptorID="";
        String FinishTime="";
        String ControlDescription="";

        public String getCtrID() {
            return CtrID;
        }

        public void setCtrID(String ctrID) {
            CtrID = ctrID;
        }

        public String getExecutorID() {
            return ExecutorID;
        }

        public void setExecutorID(String executorID) {
            ExecutorID = executorID;
        }

        public String getAcceptorID() {
            return AcceptorID;
        }

        public void setAcceptorID(String acceptorID) {
            AcceptorID = acceptorID;
        }

        public String getFinishTime() {
            return FinishTime;
        }

        public void setFinishTime(String finishTime) {
            FinishTime = finishTime;
        }

        public String getControlDescription() {
            return ControlDescription;
        }

        public void setControlDescription(String controlDescription) {
            ControlDescription = controlDescription;
        }
    }
}
