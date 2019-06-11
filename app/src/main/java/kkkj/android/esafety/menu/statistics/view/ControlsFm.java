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
import kkkj.android.esafety.bean.TroubleCtrMenu;
import kkkj.android.esafety.menu.statistics.contract.ControlsContract;
import kkkj.android.esafety.menu.statistics.model.GetCtrMenuModel;
import kkkj.android.esafety.menu.statistics.presenter.ControlsPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;
import kkkj.android.esafety.utils.NetUtils;

public class ControlsFm extends MvpBaseFragment<ControlsPresenter> implements ControlsContract.View {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;
    List<ControlsChildFm> fragments;
    List<TroubleCtrMenu> mList;

    StatisticsPagerAdapter adapter;
    @Override
    protected int getLayout() {
        return R.layout.fm_control;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getCtrMenu(new GetCtrMenuModel.Request());

    }

    @Override
    protected ControlsPresenter getPresenter() {
        return new ControlsPresenter();
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
    public void getCtrMenuSuc(List<TroubleCtrMenu> data) {
        mList.clear();
        mList.addAll(data);
        fragments.clear();
        if(mList.size()>5)
        {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        else {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        }
        for(int i = 0 ;i<mList.size();i++)
        {
            fragments.add(ControlsChildFm.getInstance(mList.get(i).getMenuValue()));
        }
        adapter.notifyDataSetChanged();
    }

    private class StatisticsPagerAdapter extends FragmentPagerAdapter
    {

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
            return mList.get(position).getMemuDesc()+"("+mList.get(position).getCount()+")";
        }
    }
}
