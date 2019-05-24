package kkkj.android.esafety.menu.statistics.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.APPDangerPointView;

public class DangerPointChildAdapter extends BaseQuickAdapter<APPDangerPointView, BaseViewHolder> {
    public DangerPointChildAdapter(int layoutResId, @Nullable List<APPDangerPointView> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, APPDangerPointView item) {
        helper.setText(R.id.tv_left,item.getDangerPoint());
        helper.setText(R.id.tv_mid,item.getPrincipal());
        helper.setText(R.id.tv_right,item.getDangerLevel());
    }
}
