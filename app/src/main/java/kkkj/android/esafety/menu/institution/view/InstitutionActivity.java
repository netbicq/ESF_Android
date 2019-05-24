package kkkj.android.esafety.menu.institution.view;

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
import kkkj.android.esafety.menu.bill.presenter.MyTaskDetailsPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;

/**
 * 制度和预案
 */
public class InstitutionActivity extends MvpBaseActivity {
    @BindView(R.id.tl_1)
    SegmentTabLayout tabLayout_1;
    @BindView(R.id.frame)
    FrameLayout frameLayout;
    List<Fragment> fragments;
    int showPosition = 0;
    @Override
    protected int getLayout() {
        return R.layout.activity_mywork;
    }

    @Override
    protected MyTaskDetailsPresenter getPresenter() {
        return new MyTaskDetailsPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("制度和预案");
        String[] mTitles = {"制度", "预案"};
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
        fragments.add(new InstitutioFragment());
        fragments.add(new ReservePlanFragment());
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
