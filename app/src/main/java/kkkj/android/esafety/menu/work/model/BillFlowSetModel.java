package kkkj.android.esafety.menu.work.model;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;

public class BillFlowSetModel extends MvpModel<BillFlowSetModel.Request, BillFlowSetModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        apiApp.billflowset(request)
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BillFlowSetModel.Response response) {
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
        boolean data=false;

        public boolean isData() {
            return data;
        }

        public void setData(boolean data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {
        String BillID= "";
        String OpreationFlowID= "";
        int FlowResult=0;
        String FlowMemo= "";

        public String getBillID() {
            return BillID;
        }

        public void setBillID(String billID) {
            BillID = billID;
        }

        public String getOpreationFlowID() {
            return OpreationFlowID;
        }

        public void setOpreationFlowID(String opreationFlowID) {
            OpreationFlowID = opreationFlowID;
        }

        public int getFlowResult() {
            return FlowResult;
        }

        public void setFlowResult(int flowResult) {
            FlowResult = flowResult;
        }

        public String getFlowMemo() {
            return FlowMemo;
        }

        public void setFlowMemo(String flowMemo) {
            FlowMemo = flowMemo;
        }
    }
}
