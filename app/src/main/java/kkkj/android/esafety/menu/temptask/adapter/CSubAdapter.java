package kkkj.android.esafety.menu.temptask.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.CSub;
import kkkj.android.esafety.bean.Sub;
import kkkj.android.esafety.bean.SubjectSelectModel;

public class CSubAdapter extends BaseQuickAdapter<CSub, BaseViewHolder> {
    public CSubAdapter(int layoutResId, @Nullable List<CSub> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CSub item) {
        helper.setText(R.id.tv_name,item.getSubType());
        RecyclerView recyclerview_child = helper.getView(R.id.recyclerview_child);
    }
}
