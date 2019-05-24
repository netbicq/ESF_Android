package kkkj.android.esafety.menu.vedio.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.VideoView;
import kkkj.android.esafety.common.WebViewActivity;
import kkkj.android.esafety.menu.vedio.adapter.VideoListAdapter;
import kkkj.android.esafety.menu.vedio.contract.VideoMonitorListContract;
import kkkj.android.esafety.menu.vedio.model.GetVideoListModel;
import kkkj.android.esafety.menu.vedio.presenter.VideoMonitorListPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;

public class VideoMonitorListActivity extends MvpBaseActivity<VideoMonitorListPresenter> implements VideoMonitorListContract.View {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    VideoListAdapter adapter;
    List<VideoView> mList;
    @Override
    protected int getLayout() {
        return R.layout.activity_video_monitor;
    }

    @Override
    protected VideoMonitorListPresenter getPresenter() {
        return new VideoMonitorListPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("视频监控");
        mList = new ArrayList<>();
        adapter = new VideoListAdapter(R.layout.item_video_list,mList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, WebViewActivity.class)
                        .putExtra("title", mList.get(position).getSite())
                        .putExtra("url",mList.get(position).getUrl()));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                GetVideoListModel.Request request = new GetVideoListModel.Request();
                mPresenter.getvideolist(request);
            }
        });
    }

    @Override
    public void getvideolistSuc(List<VideoView> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        smartRefreshLayout.autoRefresh();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        if(smartRefreshLayout!=null)
        {
            smartRefreshLayout.finishRefresh();
        }
    }
}
