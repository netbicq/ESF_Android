package kkkj.android.esafety.menu.vedio.contract;

import java.util.List;

import kkkj.android.esafety.bean.VideoView;
import kkkj.android.esafety.menu.vedio.model.GetVideoListModel;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.mvpInterface.MvpView;


public class VideoMonitorListContract {
    public interface View extends MvpView
    {
        void getvideolistSuc(List<VideoView> data);
    }
    public static abstract class Presenter extends MvpPresenter<View> {
        public abstract void getvideolist(GetVideoListModel.Request request);
    }
}
