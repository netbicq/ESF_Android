package kkkj.android.esafety.home.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.menu.statistics.view.ControlsFm;
import kkkj.android.esafety.menu.statistics.view.DangerPointFm;
import kkkj.android.esafety.menu.statistics.view.TimeOutTaskFm;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;
import kkkj.android.esafety.mvpInterface.MvpPresenter;

public class TabStatistics extends MvpBaseFragment {
    @BindView(R.id.action_bar_title)
    TextView action_bar_title;
    @BindView(R.id.tl_1)
    SegmentTabLayout tabLayout_1;

    List<Fragment> fragments;
    @BindView(R.id.frame)
    FrameLayout frameLayout;
    int showPosition = 0;

    @Override
    protected int getLayout() {
        return R.layout.tab_statistics;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("统计");
        String[] mTitles = {"风险点", "管控项", "超期任务"};
        tabLayout_1.setTabData(mTitles);
        fragments = new ArrayList<>();
        fragments.add(new DangerPointFm());
        fragments.add(new ControlsFm());
        fragments.add(new TimeOutTaskFm());
        tabLayout_1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        FragmentManager manager;
        FragmentTransaction transaction;
        manager = getChildFragmentManager();
        transaction = manager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            transaction.add(R.id.frame, fragments.get(i));
            if (showPosition != i) {
                transaction.hide(fragments.get(i));
            }
        }
        transaction.commit();
    }

    public void switchFragment(int position) {
        FragmentManager manager;
        FragmentTransaction transaction;
        manager = getChildFragmentManager();
        transaction = manager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {

            if (position == i) {
                transaction.show(fragments.get(i));
            } else {
                transaction.hide(fragments.get(i));
            }

        }
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
