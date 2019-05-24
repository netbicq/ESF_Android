package kkkj.android.esafety.login.model;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.bean.UserView;
import kkkj.android.esafety.http.ESafetyRequest;
import kkkj.android.esafety.http.ESafetyResponse;
import kkkj.android.esafety.mvpInterface.MvpCallback;
import kkkj.android.esafety.mvpInterface.MvpModel;
import kkkj.android.esafety.utils.SharedPreferenceUtil;

import static kkkj.android.esafety.utils.NetUtils.checkNetWork;


public class SignInModel extends MvpModel<SignInModel.Request, SignInModel.Response> {
    @Override
    public void getResponse(Request request, final MvpCallback<Response> callback) {
        if(!checkNetWork())
        {
            try {
                SignInModel.Response response = new SignInModel.Response();
                callback.onSuccess(response);
                callback.onComplete();
            } catch (Exception e) {
                callback.onError(e);
            }
        }
        else {
            apiAuth.signin(request)
                    .subscribeOn(Schedulers.io())//IO线程加载数据
                    .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                    .subscribe(new Observer<Response>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Response response) {
                            if (response.getState() == RESPONSE_OK) {
                                SharedPreferenceUtil.setString(SharedPreferenceUtil.SP_Commonparts_Token, response.getData().getUserInfo().getToken());
                                SharedPreferenceUtil.setString(SharedPreferenceUtil.SP_Commonparts_AccountID, response.getData().getAccountID());
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
    }

    public static class Response extends ESafetyResponse {
        UserView data;

        public UserView getData() {
            if(data==null)
            {
                return new UserView();
            }
            return data;
        }
    }

    public static class Request extends ESafetyRequest {
        private String AccountCode = "";
        private String Login="";
        private String Pwd="";

        public String getAccountCode() {
            return AccountCode;
        }

        public String getLogin() {
            return Login;
        }

        public String getPwd() {
            return Pwd;
        }

        public void setAccountCode(String accountCode) {
            AccountCode = accountCode;
        }

        public void setLogin(String login) {
            Login = login;
        }

        public void setPwd(String pwd) {
            Pwd = pwd;
        }
    }
}
