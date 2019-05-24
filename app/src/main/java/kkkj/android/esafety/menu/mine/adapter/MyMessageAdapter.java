package kkkj.android.esafety.menu.mine.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.menu.mine.model.MyMessage;
import kkkj.android.esafety.utils.ImageLoader;

public class MyMessageAdapter extends BaseQuickAdapter<MyMessage.Data, BaseViewHolder> {
    public MyMessageAdapter(int layoutResId, @Nullable List<MyMessage.Data> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyMessage.Data item) {
        if(!TextUtils.isEmpty(item.getName()))
        {
            helper.setText(R.id.tv_name,item.getName());
        }

        if(!TextUtils.isEmpty(item.getTitle()))
        {
            helper.setText(R.id.tv_title,item.getTitle());
        }

        if(!TextUtils.isEmpty(item.getImg()))
        {
//            ImageView iv_type = helper.getView(R.id.iv_type);
//            ImageLoader.load(mContext,iv_type,item.getImg(),20);
        }
    }
}
