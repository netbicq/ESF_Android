package kkkj.android.esafety.mvpInterface;


import kkkj.android.esafety.utils.NetUtils;

/**
 * MVP Presenter 基类
 * @param <V 需绑定的MvpView的子类约束>
 */
public class MvpPresenter<V extends MvpView>{
    /**
     * 绑定的view
     */
    private V mvpView;
    /**
     * 绑定view，一般在初始化中调用该方法
     */
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }
    /**
     * 断开view，一般在onDestroy中调用
     */
    public void detachView() {
        this.mvpView = null;
    }
    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接 增加检查网络连接
     */
    public boolean isViewAttached(){
        return mvpView != null;
    }
    public boolean checkNetWork()
    {
        return NetUtils.checkNetWork();
    }
    /**
     * 获取连接的view
     */
    public V getView(){
        return mvpView;
    }

}
