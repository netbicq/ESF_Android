package kkkj.android.esafety.menu.statistics.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.TimeOutTask;
import kkkj.android.esafety.bean.TroubleCtrsPage;


public class TimeOutTaskAdapter extends BaseQuickAdapter<TimeOutTask, BaseViewHolder> {
    public TimeOutTaskAdapter(int layoutResId, @Nullable List<TimeOutTask> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TimeOutTask item) {
        helper.setText(R.id.tv_left1,item.getName());
        helper.setText(R.id.tv_right1,item.getEmpOrPost());
        helper.setText(R.id.tv_left2,item.getOverHours());
        helper.setText(R.id.tv_right2,item.getDangerLevel());
        helper.setText(R.id.tv_bottom,item.getDangerPoint());
    }
}
