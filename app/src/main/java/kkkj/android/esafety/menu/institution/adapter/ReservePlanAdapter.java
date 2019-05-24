package kkkj.android.esafety.menu.institution.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.PhoneDocSolutionView;

public class ReservePlanAdapter extends BaseQuickAdapter<PhoneDocSolutionView, BaseViewHolder> {
    public ReservePlanAdapter(int layoutResId, @Nullable List<PhoneDocSolutionView> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhoneDocSolutionView item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_type,item.getSolutionType());
        helper.setText(R.id.tv_size,item.getDangerLevel());
        helper.setText(R.id.tv_date,item.getIssueDate());

    }
}
