package kkkj.android.esafety.home.model;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.bean.DownloadData;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;

import static kkkj.android.esafety.mvpInterface.MvpModel.RESPONSE_OK;

public class DownloadDataModel extends MvpModel<DownloadDataModel.Request, DownloadDataModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        apiApp.downloaddata().subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DownloadDataModel.Response response) {
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
        DownloadData data = new DownloadData();

        public DownloadData getData() {
            if(data==null)
            {
                return new DownloadData();
            }
            return data;
        }

        public void setData(DownloadData data) {
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
