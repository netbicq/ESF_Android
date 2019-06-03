package kkkj.android.esafety.menu.institution.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.PhoneDocInstitutionView;

public class InstitutionAdapter extends BaseQuickAdapter<PhoneDocInstitutionView, BaseViewHolder> {
    public InstitutionAdapter(int layoutResId, @Nullable List<PhoneDocInstitutionView> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhoneDocInstitutionView item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_type,item.getInstitutionType());
        helper.setText(R.id.tv_size,item.getBigCode());
        helper.setText(R.id.tv_date,item.getIssueDate());
    }
}
