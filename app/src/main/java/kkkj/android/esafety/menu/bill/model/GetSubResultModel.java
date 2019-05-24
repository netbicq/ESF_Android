package kkkj.android.esafety.menu.bill.model;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.bean.SubResultView;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;

public class GetSubResultModel extends MvpModel<GetSubResultModel.Request, GetSubResultModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        apiApp.getsubresult(request.getSubresultid()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GetSubResultModel.Response response) {
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
        SubResultView data = new SubResultView();

        public SubResultView getData() {
            if(data==null)
            {
                return new SubResultView();
            }
            return data;
        }

        public void setData(SubResultView data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {
        String subresultid= "";

        public String getSubresultid() {
            return subresultid;
        }

        public void setSubresultid(String subresultid) {
            this.subresultid = subresultid;
        }
    }
}
