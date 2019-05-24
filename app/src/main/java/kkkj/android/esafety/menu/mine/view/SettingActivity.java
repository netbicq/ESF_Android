package kkkj.android.esafety.menu.mine.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.customer.CustomerDialog;
import kkkj.android.esafety.login.view.LoginActivity;
import kkkj.android.esafety.menu.mine.common.DataCleanManager;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.utils.SharedPreferenceUtil;

public class SettingActivity extends MvpBaseActivity implements View.OnClickListener {
    @BindView(R.id.ll_about)
    LinearLayout ll_about;
    @BindView(R.id.ll_feedback)
    LinearLayout ll_feedback;
    @BindView(R.id.ll_clear)
    LinearLayout ll_clear;
    @BindView(R.id.tv_size)
    TextView tv_size;
    @BindView(R.id.btn_swich)
    Button btn_swich;

    CustomerDialog dialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("设置");
        ll_about.setOnClickListener(this);
        ll_feedback.setOnClickListener(this);
        ll_clear.setOnClickListener(this);
        btn_swich.setOnClickListener(this);
        try {
            tv_size.setText(DataCleanManager.getFormatSize(DataCleanManager.getFolderSize(mContext.getCacheDir())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        dialog = new CustomerDialog(mContext, 200, "删除缓存可能导致加载历史页面变慢,请确认是否删除缓存", "", "取消", "删除", new CustomerDialog.OnDialogButtonClickListener() {
            @Override
            public void onDialogButtonClick(int requestCode, boolean isPositive) {
                if (isPositive) {
                    dialog.dismiss();
                } else {
                    DataCleanManager.cleanInternalCache(mContext);
                    dialog.dismiss();
                }
            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                try {
                    tv_size.setText(DataCleanManager.getFormatSize(DataCleanManager.getFolderSize(mContext.getCacheDir())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_about:
                startActivity(new Intent(mContext, AboutActivity.class));
                break;
            case R.id.ll_feedback:
                startActivity(new Intent(mContext, FeedbackActivity.class));
                break;
            case R.id.ll_clear:
                dialog.show();
                break;
            case R.id.btn_swich:
                SharedPreferenceUtil.setBoolean(SharedPreferenceUtil.SP_AUTO_LOGIN,false);
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
                break;
        }
    }
}
