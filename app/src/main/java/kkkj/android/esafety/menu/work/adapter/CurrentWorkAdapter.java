package kkkj.android.esafety.menu.work.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.OpreateBillByEmp;

public class CurrentWorkAdapter extends BaseQuickAdapter<OpreateBillByEmp, BaseViewHolder> {


    public CurrentWorkAdapter(int layoutResId, @Nullable List<OpreateBillByEmp> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OpreateBillByEmp item) {
        helper.setText(R.id.tv_name,item.getOpreateBillName());
        helper.setText(R.id.tv_staff,item.getPrincipal());
        helper.setText(R.id.tv_startt,item.getStartTime());
        helper.setText(R.id.tv_endt,item.getEndTime());
        helper.setText(R.id.tv_count,item.getCurrentIndex()+"/"+item.getAllCount());
    }
}
