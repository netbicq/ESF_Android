package kkkj.android.esafety.menu.mine.view;

import kkkj.android.esafety.R;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.mvpInterface.MvpPresenter;

public class AboutActivity extends MvpBaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("关于");
    }
}
