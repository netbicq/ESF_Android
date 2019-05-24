package kkkj.android.esafety.menu.work.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.OpreateBillFlowDetials;


public class OpreateFlowFetialsAdapter extends BaseQuickAdapter<OpreateBillFlowDetials, BaseViewHolder> {


    public OpreateFlowFetialsAdapter(int layoutResId, @Nullable List<OpreateBillFlowDetials> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OpreateBillFlowDetials item) {
        helper.setText(R.id.tv_name,item.getFlowEmployeeName());
        helper.setText(R.id.tv_memo,item.getFlowMemo());
    }
}
