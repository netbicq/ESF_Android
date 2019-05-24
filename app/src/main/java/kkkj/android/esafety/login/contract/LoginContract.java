package kkkj.android.esafety.login.contract;


import kkkj.android.esafety.login.model.SignInModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;

public class LoginContract {
    public interface View extends MvpView
    {
        void signSuc(SignInModel.Response response);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void signin(SignInModel.Request request);
    }
}
