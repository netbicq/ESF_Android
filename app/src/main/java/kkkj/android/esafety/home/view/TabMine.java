package kkkj.android.esafety.home.view;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.app.ESafety;
import kkkj.android.esafety.bean.Auth_UserProfile;
import kkkj.android.esafety.http.ApiConfig;
import kkkj.android.esafety.menu.mine.view.MyDataActivity;
import kkkj.android.esafety.menu.mine.view.MyMessageActivity;
import kkkj.android.esafety.menu.mine.view.SettingActivity;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import kkkj.android.esafety.utils.ImageLoader;

public class TabMine extends MvpBaseFragment implements View.OnClickListener {
    @BindView(R.id.action_bar_title)
    TextView mTitle;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.iv_touxiang)
    ImageView iv_touxiang;
    @BindView(R.id.ll_mine)
    LinearLayout ll_mine;
    @BindView(R.id.ll_message)
    LinearLayout ll_message;
    @BindView(R.id.ll_setting)
    LinearLayout ll_setting;
    Auth_UserProfile userProfile;
    @Override
    protected int getLayout() {
        return R.layout.tab_mine;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initMonitorAndData() {
        mTitle.setText("我");
        ll_mine.setOnClickListener(this);
        ll_message.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        userProfile = ESafety.getInstance().getUserProfile();
        if(userProfile!=null)
        {
            if(!TextUtils.isEmpty(userProfile.getCNName()))
            {
                tv_name.setText(userProfile.getCNName());
            }

            if(!TextUtils.isEmpty(userProfile.getHeadIMG().replace("~", ApiConfig.BASE_URL)))
            {
                ImageLoader.load(mContext,iv_touxiang,userProfile.getHeadIMG().replace("~", ApiConfig.BASE_URL),25);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ll_mine:
                startActivity(new Intent(mContext, MyDataActivity.class));
                break;
            case R.id.ll_message:
                startActivity(new Intent(mContext, MyMessageActivity.class));
                break;
            case R.id.ll_setting:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
        }
    }

}
