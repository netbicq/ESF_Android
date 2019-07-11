package kkkj.android.esafety.menu.task.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.InsepctTaskByEmployee;

public class NormalTaskByEmployeeAdapter extends BaseQuickAdapter<InsepctTaskByEmployee
        , BaseViewHolder> {
    public NormalTaskByEmployeeAdapter(int layoutResId, @Nullable List<InsepctTaskByEmployee> data) {
        super(layoutResId, data);
    }

    private OnDeleteClickLister mDeleteClickListener;

    @Override
    protected void convert(BaseViewHolder helper, InsepctTaskByEmployee item) {
        View view = helper.getView(R.id.tv_delete);

        if (item.isHasDone()) {
            helper.getConvertView().setBackgroundColor(Color.parseColor("#2bb673"));
        }

        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDeleteClickListener != null) {
                        mDeleteClickListener.onDeleteClick(v, helper.getAdapterPosition());
                    }
                }
            });
        }
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.tv_name, item.getName());
        }

        if (!TextUtils.isEmpty(item.getDangerName())) {
            helper.setText(R.id.tv_danger, item.getDangerName());
        }

        helper.setText(R.id.tv_frequency, item.getCycleValue() + "次/" + item.getCycleName());

        if (!TextUtils.isEmpty(item.getLastTime())) {
            helper.setText(R.id.tv_lastTime, item.getLastTime());
        }

        if (!TextUtils.isEmpty(item.getTimeOutHours())) {
            helper.setText(R.id.tv_timeOutHours, item.getTimeOutHours() + "分钟");
        }

//        if(!TextUtils.isEmpty(item.getTaskDescription()))
//        {
//            helper.addOnClickListener(R.id.tv_explain);
//        }
    }

    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    public interface OnDeleteClickLister {
        void onDeleteClick(View view, int position);
    }
}
