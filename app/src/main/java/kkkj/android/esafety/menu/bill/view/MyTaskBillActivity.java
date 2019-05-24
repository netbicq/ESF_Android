package kkkj.android.esafety.menu.bill.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.mvpInterface.MvpPresenter;

/**
 * 任务单据
 */
public class MyTaskBillActivity extends MvpBaseActivity {
    @BindView(R.id.tl_1)
    SegmentTabLayout tabLayout_1;
    @BindView(R.id.frame)
    FrameLayout frameLayout;
    List<Fragment> fragments;
    int showPosition = 0;
    @Override
    protected int getLayout() {
        return R.layout.activity_taskbill2;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return new MvpPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("任务详情");
        String[] mTitles = {"当前单据", "历史单据"};
        tabLayout_1.setTabData(mTitles);
        tabLayout_1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        fragments = new ArrayList<>();
        fragments.add(new CurrentDocFragment());
        fragments.add(new HistoricalDocFragment());
        FragmentManager manager;
        FragmentTransaction transaction ;
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        for(int i = 0 ;i<fragments.size();i++)
        {
            transaction.add(R.id.frame, fragments.get(i));
            if(showPosition!=i)
            {
                transaction.hide(fragments.get(i));
            }
        }
        transaction.commit();
    }
    public void switchFragment(int position)
    {
        FragmentManager manager;
        FragmentTransaction transaction ;
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        for(int i = 0 ;i<fragments.size();i++)
        {

            if(position==i)
            {
                transaction.show(fragments.get(i));
            }
            else
            {
                transaction.hide(fragments.get(i));
            }

        }
        transaction.commit();
    }
}
