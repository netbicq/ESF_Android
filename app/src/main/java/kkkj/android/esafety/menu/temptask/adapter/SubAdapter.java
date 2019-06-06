package kkkj.android.esafety.menu.temptask.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.SubjectSelectModel;

public class SubAdapter extends BaseQuickAdapter<SubjectSelectModel, BaseViewHolder> {
    public SubAdapter(int layoutResId, @Nullable List<SubjectSelectModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubjectSelectModel item) {
        helper.setText(R.id.SubTypeName,item.getSubTypeName());
//        helper.setText(R.id.EntityTypeName,item.getEntityTypeName());
//        helper.setText(R.id.SubName,item.getEntity().getSubName());
        helper.setText(R.id.SubName,item.getEntityTypeName());
//        helper.setText(R.id.EntityTypeName,item.getEntity().getEntityTypeName());
        View line  = helper.getView(R.id.line);

        helper.addOnClickListener(R.id.ll_delete) ;

    }
}
