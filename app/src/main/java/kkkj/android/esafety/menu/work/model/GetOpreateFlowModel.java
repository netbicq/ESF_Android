package kkkj.android.esafety.menu.work.model;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;

public class GetOpreateFlowModel extends MvpModel<GetOpreateFlowModel.Request, GetOpreateFlowModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        apiApp.getopreateflowmodel(request.getOpreateid())
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GetOpreateFlowModel.Response response) {
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
        OpreateBillFlowModel data = new OpreateBillFlowModel();

        public OpreateBillFlowModel getData() {
            if(data==null)
            {
                return new OpreateBillFlowModel();
            }
            return data;
        }

        public void setData(OpreateBillFlowModel data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {
        String opreateid= "";

        public String getOpreateid() {
            return opreateid;
        }

        public void setOpreateid(String opreateid) {
            this.opreateid = opreateid;
        }
    }
}
