package kkkj.android.esafety.common.pickperson;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.Emp;

public class PickPersonAdapter extends BaseQuickAdapter<Emp, BaseViewHolder> {
    public PickPersonAdapter(int layoutResId, @Nullable List<Emp> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Emp item) {
        helper.setText(R.id.name,item.getCNName());
    }
}
