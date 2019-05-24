package kkkj.android.esafety.menu.work.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.menu.work.model.MyWorkModel;

public class MyWorkAdapter extends BaseQuickAdapter<MyWorkModel.Response.Data, BaseViewHolder> {
    public MyWorkAdapter(int layoutResId, @Nullable List<MyWorkModel.Response.Data> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyWorkModel.Response.Data item) {
        if(!TextUtils.isEmpty(item.getName()))
        {
            helper.setText(R.id.tv_name,item.getName());
        }

        if(!TextUtils.isEmpty(item.getDate()))
        {
            helper.setText(R.id.tv_date,item.getDate());
        }

        if(!TextUtils.isEmpty(item.getNum())&&!TextUtils.isEmpty(item.getTotal()))
        {
            helper.setText(R.id.tv_num,item.getNum()+"/"+item.getTotal());
        }
    }
}
