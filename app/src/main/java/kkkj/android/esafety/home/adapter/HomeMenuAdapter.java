package kkkj.android.esafety.home.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.HomeMenu;
import kkkj.android.esafety.utils.ImageLoader;

public class HomeMenuAdapter extends BaseQuickAdapter<HomeMenu, BaseViewHolder> {
    public HomeMenuAdapter(int layoutResId, @Nullable List<HomeMenu> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMenu item) {
        if(!TextUtils.isEmpty(item.getName()))
        {
            helper.setText(R.id.text,item.getName());
        }

        if(item.getImgId()!=0)
        {
            ImageView img = helper.getView(R.id.image);
            ImageLoader.load(mContext,img,item.getImgId());
        }

        if(item.getCount()>0)
        {
            TextView textView = helper.getView(R.id.textView);
            textView.setText(item.getCount()+"");
            textView.setVisibility(View.VISIBLE);
        }

    }
}
