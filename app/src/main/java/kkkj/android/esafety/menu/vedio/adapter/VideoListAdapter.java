package kkkj.android.esafety.menu.vedio.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.VideoView;


public class VideoListAdapter extends BaseQuickAdapter<VideoView, BaseViewHolder> {
    public VideoListAdapter(int layoutResId, @Nullable List<VideoView> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoView item) {
        helper.setText(R.id.tv_name,item.getSite());
        helper.setText(R.id.tv_code,item.getCode());
    }
}
