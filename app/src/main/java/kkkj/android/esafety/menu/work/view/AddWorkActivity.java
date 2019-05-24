package kkkj.android.esafety.menu.work.view;

import kkkj.android.esafety.R;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.utils.SoftHideKeyBoardUtil;

public class AddWorkActivity extends MvpBaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_addwork;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initMonitorAndData() {
        SoftHideKeyBoardUtil.assistActivity(mActivity);
        action_bar_title.setText("添加作业");
        action_bar_right.setText("添加");
    }
}
