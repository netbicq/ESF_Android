package kkkj.android.esafety.menu.institution.model;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.bean.PhoneDocInstitutionModelView;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;

public class GetdoCinsModel extends MvpModel<GetdoCinsModel.Request, GetdoCinsModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        apiApp.getdocins(request.getInsid())
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GetdoCinsModel.Response response) {
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
        PhoneDocInstitutionModelView data = new PhoneDocInstitutionModelView();

        public PhoneDocInstitutionModelView getData() {
            if(data==null)
            {
                return new PhoneDocInstitutionModelView();
            }
            return data;
        }

        public void setData(PhoneDocInstitutionModelView data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {
        String insid="";

        public String getInsid() {
            return insid;
        }

        public void setInsid(String insid) {
            this.insid = insid;
        }
    }
}
