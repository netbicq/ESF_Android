package kkkj.android.esafety;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.tencent.bugly.beta.Beta;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.home.view.TabHome;
import kkkj.android.esafety.home.view.TabMine;
import kkkj.android.esafety.home.view.TabStatistics;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.mvpInterface.MvpPresenter;

public class MainActivity extends MvpBaseActivity {
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    List<Fragment> fragments;
    int showPosition = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initMonitorAndData() {
//        navigation.setItemIconTintList(null);
        Beta.checkUpgrade(false, false);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        switchFragment(0);
                        break;
                    case R.id.navigation_dashboard:
                        switchFragment(1);
                        break;
                    case R.id.navigation_notifications:
                        switchFragment(2);
                        break;
                }

                return true;
            }
        });
        fragments = new ArrayList<>();
        fragments.add(new TabHome());
        fragments.add(new TabStatistics());
        fragments.add(new TabMine());
        FragmentManager manager;
        FragmentTransaction transaction;
        manager = getSupportFragmentManager();
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
        manager = getSupportFragmentManager();
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
}
