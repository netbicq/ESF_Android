package kkkj.android.esafety.mvpInterface;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;


import butterknife.ButterKnife;
import butterknife.Unbinder;

import kkkj.android.esafety.app.ESafety;
import kkkj.android.esafety.customer.MyToasty;
import kkkj.android.esafety.login.model.SignInModel;
import kkkj.android.esafety.login.view.LoginActivity;
import kkkj.android.esafety.utils.SharedPreferenceUtil;
import kkkj.android.esafety.utils.SystemUtils;

import static kkkj.android.esafety.utils.NetUtils.checkNetWork;

/**
 * MVP activity基类
 */
public abstract class MvpBaseFragment<T extends MvpPresenter> extends Fragment implements MvpView {
    public RxPermissions rxPermissions;
    MyToasty mToast;
    QMUITipDialog tLoading;

    private Unbinder unbinder;
    public T mPresenter;
    public FragmentActivity mActivity;
    public Context mContext;
    public View mView;
    public Bundle mSavedInstanceState;

    @Override
    public void onAttach(Context context) {
        mActivity = (FragmentActivity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayout(), null);
        return mView;
    }


    public void showLoading(String msg) {
        if (getUserVisibleHint()) {
            if (!tLoading.isShowing()) {
                tLoading = new QMUITipDialog.Builder(getContext())
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord(msg)
                        .create();
                tLoading.show();
            }
        }
    }

    @Override
    public void goLogin() {
        Logger.d("重新登录");
        SignInModel.Request request = new SignInModel.Request();
        String AccountCode = SharedPreferenceUtil.getString(SharedPreferenceUtil.SP_USER_ACCOUNTCODE);
        String Login = SharedPreferenceUtil.getString(SharedPreferenceUtil.SP_USER_NAME);
        String Pwd = SharedPreferenceUtil.getString(SharedPreferenceUtil.SP_USER_PWD);

        request.setLogin(Login);
        request.setPwd(Pwd);
        request.setAccountCode(AccountCode);

        if (!TextUtils.isEmpty(request.getPwd())) {
            if (!checkNetWork()) {
                showErr("请检查网络");
                return;
            }
            //显示正在加载进度条
            showLoading("正在为您重新登录");
            // 调用Model请求数据
            SignInModel loginModel = new SignInModel();
            loginModel.getResponse(request, new MvpCallback<SignInModel.Response>(this) {
                @Override
                public void onSuccess(SignInModel.Response data) {
                    showToast("登录成功,请重新发起请求");
                    ESafety.getInstance().setUserProfile(data.getData().getUserProfile());
                    if (data.getData().getUserInfo() != null) {
                        if (!TextUtils.isEmpty(data.getData().getAccountID())) {
                            ESafety.getInstance().setCommonParts(data.getData().getUserInfo().getToken(), data.getData().getAccountID());
                        }
                    }
                }
            });
        } else {
            startActivity(new Intent(mContext, LoginActivity.class));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }
        this.init();
    }

    @Override
    public void startActivity(Intent intent) {
        //把默认启动模式改为singleTop
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        super.startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mPresenter != null)
            mPresenter.detachView();
    }

    protected void init() {
        /*
         *初始化自定义toast
         **/
        mToast = new MyToasty(mContext);
        rxPermissions = new RxPermissions(mActivity);
        tLoading = new QMUITipDialog.Builder(getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initMonitorAndData();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected abstract int getLayout();

    protected abstract T getPresenter();

    protected abstract void initMonitorAndData();

    @Override
    public void showLoading() {
        Logger.d(getUserVisibleHint()+"--------------------");
        if (getUserVisibleHint()) {
            if (!tLoading.isShowing()) {
                tLoading.show();
            }
        }

    }

    @Override
    public void hideLoading() {
        if (tLoading.isShowing()) {
            tLoading.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        if (getUserVisibleHint()) {
            if (tLoading.isShowing()) {
                tLoading.dismiss();
            }
            mToast.showInfo(msg);
        }


    }

    @Override
    public void showErr(String msg) {
        if (getUserVisibleHint()) {
            if (tLoading.isShowing()) {
                tLoading.dismiss();
            }
            mToast.showError(msg);
        }


    }

    @Override
    public boolean getUserVisibleHint() {
        return checkIsVisible(mContext,getView());
    }

    /**
     * 判断视图是否显示在屏幕上
     * @param context
     * @param view
     * @return
     */
    public static boolean checkIsVisible(Context context, View view) {
        int screenWidth = SystemUtils.getScreenMetrics(context).widthPixels;
        int screenHeight = SystemUtils.getScreenMetrics(context).heightPixels;
        Rect rect = new Rect(0, 0, screenWidth, screenHeight);
        int[] location = new int[2];
        view.getLocationInWindow(location);
        if (view.getLocalVisibleRect(rect)) {
            return true;
        } else {
            //view已不在屏幕可见区域;
            return false;
        }
    }
    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void onComplete() {
        hideLoading();
    }

    public void reserve(String remarks) {
    }
}
