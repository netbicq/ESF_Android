package kkkj.android.esafety.menu.statistics.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.TroubleCtrsPage;


public class ControlsChildIngAdapter extends BaseQuickAdapter<TroubleCtrsPage, BaseViewHolder> {
    public ControlsChildIngAdapter(int layoutResId, @Nullable List<TroubleCtrsPage> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TroubleCtrsPage item) {
        helper.setText(R.id.tv_left1,item.getBillEmp());
        helper.setText(R.id.tv_right1,item.getPrincipal());

        helper.setText(R.id.tv_left2,item.getDangerLevel());
        helper.setText(R.id.tv_right2,item.getTroubleLevel());

        helper.setText(R.id.tv_left3,item.getSubType());
        helper.setText(R.id.tv_right3,item.getCheckSub());

        helper.setText(R.id.tv_left4,item.getDangerPoint());
        helper.setText(R.id.tv_right4,item.getDanger());

        helper.setText(R.id.tv_left5,item.getAEmp());
        helper.setText(R.id.tv_right5,item.getEEmp());

        helper.setText(R.id.id_tv_time,item.getCTime());
    }
}
