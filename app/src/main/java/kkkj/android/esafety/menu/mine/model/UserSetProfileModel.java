package kkkj.android.esafety.menu.mine.model;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;

public class UserSetProfileModel extends MvpModel<UserSetProfileModel.Request, UserSetProfileModel.Response> {
    @Override
    public void getResponse(UserSetProfileModel.Request request, MvpCallback<Response> callback) {
        apiAuth.setprofile(request)
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(UserSetProfileModel.Response response) {
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
        boolean data =false;

        public boolean isData() {
            return data;
        }

        public void setData(boolean data) {
            this.data = data;
        }
    }

    public static class Request extends ESafetyRequest {
        String Login = "";
        String CNName = "";
        String Tel = "";
        String HeadIMG = "";

        public String getLogin() {
            return Login;
        }

        public void setLogin(String login) {
            Login = login;
        }

        public String getCNName() {
            return CNName;
        }

        public void setCNName(String CNName) {
            this.CNName = CNName;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String tel) {
            Tel = tel;
        }

        public String getHeadIMG() {
            return HeadIMG;
        }

        public void setHeadIMG(String headIMG) {
            HeadIMG = headIMG;
        }
    }
}
