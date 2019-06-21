package kkkj.android.esafety.menu.bill.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.TaskSubjectView;

public class TaskSubjectViewAdapter extends BaseQuickAdapter<TaskSubjectView
        , BaseViewHolder> {
    public TaskSubjectViewAdapter(int layoutResId, @Nullable List<TaskSubjectView> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskSubjectView item) {
        if(item.getState()==1)
        {
            helper.itemView.setBackgroundColor(Color.parseColor("#54FF9F"));
        }
        else {
            helper.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        helper.setText(R.id.tv_danger2,  "风控项:" + item.getDangerName());
        helper.setText(R.id.tv_name, item.getSubTypeName() + "名:" + item.getSubName());
        helper.setText(R.id.tv_danger, "风险等级:" + item.getDangerLevel());
        helper.addOnClickListener(R.id.tv_delete);
    }
}
