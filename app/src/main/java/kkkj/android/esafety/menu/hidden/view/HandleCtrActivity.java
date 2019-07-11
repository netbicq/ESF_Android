package kkkj.android.esafety.menu.hidden.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.APPTroubleCtrView;
import kkkj.android.esafety.bean.DangerLevelDict;
import kkkj.android.esafety.bean.Dict;
import kkkj.android.esafety.common.pickperson.PickPersonActivity;
import kkkj.android.esafety.customer.MyOptionsPicker;
import kkkj.android.esafety.customer.MyTimePicker;
import kkkj.android.esafety.menu.hidden.contract.HandleCtrContract;
import kkkj.android.esafety.menu.hidden.model.ChangeDangerLevelModel;
import kkkj.android.esafety.menu.hidden.model.ChangeLevelModel;
import kkkj.android.esafety.menu.hidden.model.DelayFinishTimeModel;
import kkkj.android.esafety.menu.hidden.model.HandleCtrModel;
import kkkj.android.esafety.menu.hidden.presenter.HandleCtrPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.utils.SoftHideKeyBoardUtil;

/**
 * 处理管控项
 */
public class HandleCtrActivity extends MvpBaseActivity<HandleCtrPresenter> implements HandleCtrContract.View, View.OnClickListener {
    @BindView(R.id.ll_zhixr)
    LinearLayout ll_执行人;
    @BindView(R.id.ll_yansr)
    LinearLayout ll_验收人;
    @BindView(R.id.ll_time)
    LinearLayout ll_预计完成时间;
    @BindView(R.id.ll_dangerl)
    LinearLayout ll_风险等级;
    @BindView(R.id.ll_yhdj)
    LinearLayout ll_隐患等级;


    @BindView(R.id.tv_zhixr)
    TextView tv_执行人;
    @BindView(R.id.tv_yansr)
    TextView tv_验收人;
    @BindView(R.id.tv_time)
    TextView tv_预计完成时间;
    @BindView(R.id.tv_fxdj)
    TextView tv_风险等级;
    @BindView(R.id.tv_yhdj)
    TextView tv_隐患等级;
    @BindView(R.id.ed_describe)
    EditText tv_管控内容;


    TimePickerView TimePicker;
    OptionsPickerView DangerLevelsPicker, TroubleLevelsPicker;
    List<DangerLevelDict> DangerLevels = new ArrayList<>();
    List<Dict> TroubleLevels = new ArrayList<>();
    APPTroubleCtrView trouble;
    int PickRequest1 = 1001;
    int PickRequest2 = 1002;

    String FinishTime = "";


    String 风险等级ID = "";
    String 风险等级Name = "";

    String 隐患等级ID = "";
    String 隐患等级Name = "";
    HandleCtrModel.Request request;

    @Override
    protected int getLayout() {
        return R.layout.activity_handle_ctr;
    }

    @Override
    protected HandleCtrPresenter getPresenter() {
        return new HandleCtrPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        SoftHideKeyBoardUtil.assistActivity(mActivity);
        action_bar_title.setText("处理管控项");
        action_bar_right.setText("提交");
        action_bar_right.setOnClickListener(this);
        ll_执行人.setOnClickListener(this);
        ll_验收人.setOnClickListener(this);
        ll_预计完成时间.setOnClickListener(this);
        ll_风险等级.setOnClickListener(this);
        ll_隐患等级.setOnClickListener(this);
        DangerLevels = LitePal.findAll(DangerLevelDict.class);
        TroubleLevels = LitePal.findAll(Dict.class);
        initPickers();
        trouble = (APPTroubleCtrView) getIntent().getSerializableExtra("Trouble");
        tv_执行人.setText(trouble.getExecutor());
        tv_验收人.setText(trouble.getAcceptor());
        tv_预计完成时间.setText(trouble.getEstimatedDate());
        tv_隐患等级.setText(trouble.getTroubleLevelName());
        tv_风险等级.setText(trouble.getCDangerLevelName());
        tv_管控内容.setText(trouble.getCtrTarget());
        request = new HandleCtrModel.Request();

        request.setAcceptorID(trouble.getAcceptorID());
        request.setExecutorID(trouble.getExecutorID());
        request.setCtrID(trouble.getKeyID());
        request.setFinishTime(trouble.getEstimatedDate());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_bar_right:

                if (TextUtils.isEmpty(request.getExecutorID())) {
                    showToast("请选择执行人");
                    return;
                }
                if (TextUtils.isEmpty(request.getAcceptorID())) {
                    showToast("请选择验收人");
                    return;
                }


                if (TextUtils.isEmpty(request.getFinishTime())) {
                    showToast("请选择预计完成时间");
                    return;
                }
                tv_管控内容.getText().toString();
                request.setControlDescription(tv_管控内容.getText().toString());
                if (TextUtils.isEmpty(request.getControlDescription())) {
                    showToast("请填写管控内容");
                    return;
                }

                mPresenter.handleCtr(request);



                break;
            case R.id.ll_zhixr://执行人
                startActivityForResult(new Intent(mContext, PickPersonActivity.class), PickRequest1);
                break;
            case R.id.ll_yansr://验收人
                startActivityForResult(new Intent(mContext, PickPersonActivity.class), PickRequest2);
                break;
            case R.id.ll_time:
                TimePicker.show();
                break;
            case R.id.ll_dangerl:
                DangerLevelsPicker.show();
                break;
            case R.id.ll_yhdj:
                TroubleLevelsPicker.show();
                break;
        }
    }

    @Override
    public void delayFinishTimeSuc(boolean result) {
        if (result) {
            showToast("修改管控时间成功");
            tv_预计完成时间.setText(FinishTime);


            request.setFinishTime(FinishTime);

        } else {
            showToast("修改管控时间失败");
        }
    }

    @Override
    public void changeLevelSuc(boolean result) {
        if (result) {
            showToast("修改隐患等级成功");
            tv_隐患等级.setText(隐患等级Name);


        } else {
            showToast("修改隐患等级失败");
        }

    }

    @Override
    public void changeDangerLevelSuc(boolean result) {
        if (result) {
            showToast("修改风险等级成功");
            tv_风险等级.setText(风险等级Name);
        } else {
            showToast("修改风险等级失败");
        }
    }

    @Override
    public void handleCtr(boolean result) {
        if (result) {
            showToast("处理成功");
            //数据是使用Intent返回
            Intent intent = new Intent();
            //把返回数据存入Intent
            intent.putExtra("HandleResult", 100);
            //设置返回数据
            mActivity.setResult(RESULT_OK, intent);
            //关闭Activity
            finish();
        } else {
            showToast("处理失败");
        }
    }

    private void initPickers() {
        MyOptionsPicker picker = new MyOptionsPicker();
        MyTimePicker myTimePicker = new MyTimePicker();
        //初始化风险等级选择
        DangerLevelsPicker = picker.getPicker(mContext, "风险等级选择", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                风险等级Name = DangerLevels.get(options1).getDictName();
                ChangeDangerLevelModel.Request request = new ChangeDangerLevelModel.Request();
                request.setID(trouble.getKeyID());
                request.setDangerLevel(DangerLevels.get(options1).getKeyID());
                mPresenter.changeDangerLevel(request);
            }
        });
        DangerLevelsPicker.setPicker(DangerLevels, null, null);

        //初始化隐患等级选择
        TroubleLevelsPicker = picker.getPicker(mContext, "隐患等级选择", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                隐患等级Name = TroubleLevels.get(options1).getDictName();
                ChangeLevelModel.Request request = new ChangeLevelModel.Request();
                request.setID(trouble.getKeyID());
                request.setTroubleLevel(TroubleLevels.get(options1).getKeyID());
                mPresenter.changeLevel(request);
            }
        });
        TroubleLevelsPicker.setPicker(TroubleLevels, null, null);
        //初始化时间选择器
        TimePicker = myTimePicker.getPicker(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                FinishTime = df.format(date);
                DelayFinishTimeModel.Request request = new DelayFinishTimeModel.Request();
                request.setID(trouble.getKeyID());
                request.setFinishTime(df.format(date));
                mPresenter.delayFinishTime(request);
//                request.setStartTime(df.format(date));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PickRequest1) {
            if (resultCode == Activity.RESULT_OK) {
                String id = data.getStringExtra("PID");
                String name = data.getStringExtra("PName");
                tv_执行人.setText(name);
                request.setExecutorID(id);


            }
        } else if (requestCode == PickRequest2) {
            if (resultCode == Activity.RESULT_OK) {
                String id = data.getStringExtra("PID");
                String name = data.getStringExtra("PName");
                tv_验收人.setText(name);
                request.setAcceptorID(id);
            }
        }
    }
}
