package kkkj.android.esafety.mvpInterface;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import kkkj.android.esafety.R;
import kkkj.android.esafety.app.ESafety;
import kkkj.android.esafety.customer.MyToasty;
import kkkj.android.esafety.login.model.SignInModel;
import kkkj.android.esafety.login.view.LoginActivity;
import kkkj.android.esafety.utils.SharedPreferenceUtil;

import static kkkj.android.esafety.utils.NetUtils.checkNetWork;

/**
 * MVP activity基类
 */
public abstract class MvpBaseActivity<T extends MvpPresenter> extends AppCompatActivity implements MvpView {

    MyToasty mToast;
    QMUITipDialog tLoading;
    public RxPermissions rxPermissions = new RxPermissions(this);
    private Unbinder unbinder;
    public T mPresenter;
    public FragmentActivity mActivity;
    public Context mContext;

    public TextView action_bar_title;
    public TextView action_bar_right;

    @Override
    public void startActivity(Intent intent) {
        //把默认启动模式改为singleTop
        if (intent.getFlags() != Intent.FLAG_ACTIVITY_CLEAR_TASK) {
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        super.startActivity(intent);
    }

    /**
     * 界面设置状态栏字体颜色
     */

    private void setAndroidNativeLightStatusBar(boolean dark) {
        View decor = mActivity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//继承AppCompatActivity使用
        setContentView(getLayout());
        /**
         * 强制竖屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        /*
         *透明状态栏
         */

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        mActivity = this;
        setAndroidNativeLightStatusBar(true);
        mContext = this;
        rxPermissions = new RxPermissions(mActivity);
        action_bar_title = findViewById(R.id.action_bar_title);
        action_bar_right = findViewById(R.id.action_bar_right);
        /*
         *初始化自定义toast
         **/
        unbinder = ButterKnife.bind(this);
        mToast = new MyToasty(mContext);
        tLoading= new QMUITipDialog.Builder(getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();

        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        ESafety.getInstance().addActivity(mActivity);
        initMonitorAndData();
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tLoading.isShowing()) {
            tLoading.dismiss();
        }

        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mPresenter != null)
            mPresenter.detachView();
        ESafety.getInstance().removeActivity(mActivity);
    }

    protected abstract int getLayout();

    protected abstract T getPresenter();

    protected abstract void initMonitorAndData();

    @Override
    public void showLoading() {
        if(tLoading==null)
            return;
        if (!tLoading.isShowing()) {
            tLoading.show();
        }
    }

    public void showLoading(String msg) {
        if(tLoading==null)
            return;
        if (!tLoading.isShowing()) {
            tLoading= new QMUITipDialog.Builder(getContext())
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord(msg)
                    .create();
            tLoading.show();
        }
    }

    @Override
    public void hideLoading() {
        if(tLoading==null)
            return;
        if (tLoading.isShowing()) {
            tLoading.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {

        if(Looper.myLooper()==null)
        {
            Looper.prepare();
        }
        if (tLoading.isShowing()) {
            tLoading.dismiss();
        }
        mToast.showInfo(msg);
    }

    @Override
    public void showErr(String msg) {
        if(Looper.myLooper()==null)
        {
            Looper.prepare();
        }
        if (tLoading.isShowing()) {
            tLoading.dismiss();
        }
        mToast.showError(msg);
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void onComplete() {
        hideLoading();
    }

    @Override
    public void goLogin() {
        if(mContext==null)
            return;
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
                    if(data.getData().getUserInfo()!=null)
                    {
                        if(!TextUtils.isEmpty(data.getData().getAccountID()))
                        {
                            ESafety.getInstance().setCommonParts(data.getData().getUserInfo().getToken(),data.getData().getAccountID());
                        }
                    }
                }
            });
        } else {
            startActivity(new Intent(mContext, LoginActivity.class));
        }
    }

    public void goBack(View view) {
        finish();
    }
}
