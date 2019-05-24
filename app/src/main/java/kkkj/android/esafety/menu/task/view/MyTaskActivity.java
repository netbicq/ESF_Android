package kkkj.android.esafety.menu.task.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;
import kkkj.android.esafety.mvpInterface.MvpPresenter;

/**
 * 我的任务列表
 */
public class MyTaskActivity extends MvpBaseActivity {
    @BindView(R.id.tl_1)
    SegmentTabLayout tabLayout_1;
    @BindView(R.id.frame)
    FrameLayout frameLayout;
    @BindView(R.id.action_bar_right_iv)
    ImageView action_bar_right_iv;

    List<MvpBaseFragment> fragments;
    int showPosition = 0;
    int OverTimeTaskCount;

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    @Override
    protected int getLayout() {
        return R.layout.activity_mytask;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("我的任务");
        OverTimeTaskCount= getIntent().getIntExtra("OverTimeTaskCount",0);
//        action_bar_right_iv.setVisibility(View.VISIBLE);
        action_bar_right_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermissions();
            }
        });
        String[] mTitles = {"正常任务", "超时任务"};
        tabLayout_1.setTabData(mTitles);
        if(OverTimeTaskCount>0)
        {
            tabLayout_1.showMsg(1,OverTimeTaskCount);
        }
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
        fragments.add(new NormalTaskFragment());
        fragments.add(new OvertimeTaskFragment());
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
        showPosition = position;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Logger.d(result);
                    fragments.get(showPosition).reserve(result);
//                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    showToast("解析二维码失败");
                }
            }
        }
    }

    /**
     * 获取权限
     */
    private void getPermissions() {
        rxPermissions.requestEachCombined(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                .subscribe(permission -> { // will emit 1 Permission object
                    if (permission.granted) {
                        Intent intent = new Intent(getApplication(), CaptureActivity.class);
                        startActivityForResult(intent, REQUEST_CODE);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        //有至少一个权限没有同意
                        showToast("请同意全部权限");
                    } else {
                        //有至少一个权限没有同意且勾选了不在提示
                        showToast("请在权限管理中打开相关权限");
                    }
                });
    }
}
