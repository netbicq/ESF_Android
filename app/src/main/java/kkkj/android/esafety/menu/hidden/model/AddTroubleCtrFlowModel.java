package kkkj.android.esafety.menu.hidden.model;

import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddTroubleCtrFlowModel extends MvpModel<AddTroubleCtrFlowModel.Request, AddTroubleCtrFlowModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        apiApp.addTroubleCtrFlow(request)
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AddTroubleCtrFlowModel.Response response) {
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
        String ControlID="";
        String FlowMemo="";
        int FlowType=1;
        int FlowResult=0;

        public String getControlID() {
            return ControlID;
        }

        public void setControlID(String controlID) {
            ControlID = controlID;
        }

        public String getFlowMemo() {
            return FlowMemo;
        }

        public void setFlowMemo(String flowMemo) {
            FlowMemo = flowMemo;
        }

        public int getFlowType() {
            return FlowType;
        }

        public void setFlowType(int flowType) {
            FlowType = flowType;
        }

        public int getFlowResult() {
            return FlowResult;
        }

        public void setFlowResult(int flowResult) {
            FlowResult = flowResult;
        }
    }
}
