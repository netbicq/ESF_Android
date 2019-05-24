package kkkj.android.esafety.menu.mine.view;

import kkkj.android.esafety.R;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.mvpInterface.MvpPresenter;

public class FeedbackActivity extends MvpBaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("意见反馈");
        action_bar_right.setText("保存");
    }
}
