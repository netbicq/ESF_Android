package kkkj.android.esafety.menu.bill.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.TaskBillModel;

public class BillAdapter extends BaseQuickAdapter<TaskBillModel, BaseViewHolder> {
    public BillAdapter(int layoutResId, @Nullable List<TaskBillModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskBillModel item) {
        if (item.getTaskType() == 1) {
            helper.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            helper.itemView.setBackgroundColor(Color.parseColor("#FFA07A"));
        }

        helper.setText(R.id.tv_name, item.getTaskName());

        helper.setText(R.id.tv_danger, item.getDangerName());

        helper.setText(R.id.tv_staff, item.getEmployeeName());

        helper.setText(R.id.tv_state, item.getState());

        helper.setText(R.id.tv_startt, item.getStartTime());

        helper.setText(R.id.tv_endt, item.getEndTime());

        helper.setText(R.id.tv_count, item.getSubCheckedCount() + "/" + item.getSubCount());
        helper.addOnClickListener(R.id.tv_delete);
        helper.addOnClickListener(R.id.tv_complete);
        helper.addOnClickListener(R.id.tv_see);
    }
}
