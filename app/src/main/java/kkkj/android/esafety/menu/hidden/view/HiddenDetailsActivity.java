package kkkj.android.esafety.menu.hidden.view;

import android.widget.EditText;
import android.widget.TextView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.APPTroubleCtrView;
import kkkj.android.esafety.bean.DangerLevelDict;
import kkkj.android.esafety.bean.Dict;
import kkkj.android.esafety.menu.hidden.presenter.HandleCtrPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.utils.SoftHideKeyBoardUtil;

/**
 * 管控项详情
 */
public class HiddenDetailsActivity extends MvpBaseActivity {

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
    @BindView(R.id.tv_zt)
    TextView tv_主体;
    @BindView(R.id.tv_fkx)
    TextView tv_风控项;
    @BindView(R.id.ed_describe)
    EditText tv_管控内容;

    List<DangerLevelDict> DangerLevels = new ArrayList<>();
    List<Dict> TroubleLevels = new ArrayList<>();
    APPTroubleCtrView trouble;
    @Override
    protected int getLayout() {
        return R.layout.activity_hidden_details;
    }

    @Override
    protected HandleCtrPresenter getPresenter() {
        return new HandleCtrPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        SoftHideKeyBoardUtil.assistActivity(mActivity);
        action_bar_title.setText("处理管控项");
        DangerLevels = LitePal.findAll(DangerLevelDict.class);
        TroubleLevels = LitePal.findAll(Dict.class);
        trouble = (APPTroubleCtrView) getIntent().getSerializableExtra("Trouble");
        tv_执行人.setText(trouble.getExecutor());
        tv_验收人.setText(trouble.getAcceptor());
        tv_预计完成时间.setText(trouble.getEstimatedDate());
        tv_隐患等级.setText(trouble.getTroubleLevelName());
        tv_风险等级.setText(trouble.getCDangerLevelName());
        tv_主体.setText(trouble.getSubName());
        tv_风控项.setText(trouble.getDangerName());
        tv_管控内容.setText(trouble.getCtrTarget());
        tv_管控内容.setEnabled(false);
    }
}
