package kkkj.android.esafety.menu.institution.model;

import kkkj.android.esafety.bean.PhoneDocSolutionModelView;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetdocSolutionModel extends MvpModel<GetdocSolutionModel.Request, GetdocSolutionModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        apiApp.getdocsolutionmodel(request.getDocsolutionid())
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GetdocSolutionModel.Response response) {
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
        PhoneDocSolutionModelView data = new PhoneDocSolutionModelView();

        public PhoneDocSolutionModelView getData() {
            if(data==null)
            {
                return new PhoneDocSolutionModelView();
            }
            return data;
        }

        public void setData(PhoneDocSolutionModelView data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {
        String docsolutionid = "";

        public String getDocsolutionid() {
            return docsolutionid;
        }

        public void setDocsolutionid(String docsolutionid) {
            this.docsolutionid = docsolutionid;
        }
    }
}
