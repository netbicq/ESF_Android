package kkkj.android.esafety.menu.statistics.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.TroubleCtrsPage;


public class ControlsChildAdapter extends BaseQuickAdapter<TroubleCtrsPage, BaseViewHolder> {
    public ControlsChildAdapter(int layoutResId, @Nullable List<TroubleCtrsPage> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TroubleCtrsPage item) {
        helper.setText(R.id.tv_left1,item.getBillEmp());
        helper.setText(R.id.tv_right1,item.getDangerLevel());
        helper.setText(R.id.tv_left2,item.getPrincipal());
        helper.setText(R.id.tv_right2,item.getTroubleLevel());
    }
}
