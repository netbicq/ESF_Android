package kkkj.android.esafety.menu.institution.model;
import java.util.ArrayList;
import java.util.List;

import kkkj.android.esafety.bean.PhoneDocSolutionView;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetdocSolutionListModel extends MvpModel<GetdocSolutionListModel.Request, GetdocSolutionListModel.Response> {
    @Override
    public void getResponse(Request request, MvpCallback<Response> callback) {
        apiApp.getdocsolutionlist(request.getDangerlevelid())
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GetdocSolutionListModel.Response response) {
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
        List<PhoneDocSolutionView> data = new ArrayList<>();

        public List<PhoneDocSolutionView> getData() {
            if(data ==null)
            {
                return new ArrayList<>();
            }
            return data;
        }

        public void setData(List<PhoneDocSolutionView> data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {
        String dangerlevelid = "00000000-0000-0000-0000-000000000000";

        public String getDangerlevelid() {
            return dangerlevelid;
        }

        public void setDangerlevelid(String dangerlevelid) {
            this.dangerlevelid = dangerlevelid;
        }
    }
}
