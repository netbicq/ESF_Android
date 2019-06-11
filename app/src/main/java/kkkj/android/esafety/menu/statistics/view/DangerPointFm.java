package kkkj.android.esafety.menu.statistics.view;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.menu.statistics.contract.DangerPointContract;
import kkkj.android.esafety.menu.statistics.model.GetDangerLevelsModel;
import kkkj.android.esafety.bean.StatisticsDangerLevel;
import kkkj.android.esafety.menu.statistics.presenter.DangerPointPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;
import kkkj.android.esafety.utils.NetUtils;

public class DangerPointFm extends MvpBaseFragment<DangerPointPresenter> implements DangerPointContract.View {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;
    List<DangerPointChildFm> fragments;
    List<StatisticsDangerLevel> mList;

    StatisticsPagerAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fm_dangerpoint;
    }

    @Override
    public void onResume() {
        mPresenter.getDangerLevels(new GetDangerLevelsModel.Request());
        super.onResume();
    }

    @Override
    protected DangerPointPresenter getPresenter() {

        return new DangerPointPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        tabLayout.setTabIndicatorFullWidth(false);
        fragments = new ArrayList<>();
        mList = new ArrayList<>();
        tabLayout.setupWithViewPager(vp);
        adapter = new StatisticsPagerAdapter(getChildFragmentManager());
        vp.setAdapter(adapter);

    }

    @Override
    public void getDangerLevelsSuc(List<StatisticsDangerLevel> data) {
        mList.clear();
        mList.addAll(data);
        fragments.clear();
        if (mList.size() > 3) {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        }
        for (int i = 0; i < mList.size(); i++) {
            fragments.add(DangerPointChildFm.getInstance(mList.get(i).getLevelID()));
        }
        adapter.notifyDataSetChanged();
    }

    private class StatisticsPagerAdapter extends FragmentPagerAdapter {

        public StatisticsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mList.get(position).getLevelName() + "(" + mList.get(position).getCount() + ")";
        }
    }
}
