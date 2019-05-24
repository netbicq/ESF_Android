package kkkj.android.esafety.menu.statistics.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.PagerOfTimeOutTask;
import kkkj.android.esafety.bean.TimeOutTask;
import kkkj.android.esafety.menu.statistics.adapter.TimeOutTaskAdapter;
import kkkj.android.esafety.menu.statistics.contract.TimeOutTaskContract;
import kkkj.android.esafety.menu.statistics.model.GetTimeOutTaskModel;
import kkkj.android.esafety.menu.statistics.presenter.TimeOutTaskPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;
import kkkj.android.esafety.utils.NetUtils;

public class TimeOutTaskFm extends MvpBaseFragment<TimeOutTaskPresenter> implements TimeOutTaskContract.View {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    TimeOutTaskAdapter adapter;
    List<TimeOutTask> mList;

    GetTimeOutTaskModel.Request request ;
    int PageSize=10;
    int PageIndex=0;
    @Override
    protected int getLayout() {
        return R.layout.smart_recycler;
    }

    @Override
    protected TimeOutTaskPresenter getPresenter() {
        return new TimeOutTaskPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        request = new GetTimeOutTaskModel.Request();
        mList = new ArrayList<>();
        adapter = new TimeOutTaskAdapter(R.layout.item_timeouttask,mList);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                PageIndex = 0;
                request.setPageIndex(PageIndex);
                request.setPageSize(PageSize);
                request.setQuery("");
                mPresenter.getTimeOutTask(request);
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                PageIndex++;
                request.setPageIndex(PageIndex);
                request.setPageSize(PageSize);
                request.setQuery("");
                mPresenter.getTimeOutTask(request);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if(NetUtils.checkNetWork())
        {
            request.setPageIndex(PageIndex);
            request.setPageSize(PageSize);
            request.setQuery("");
            mPresenter.getTimeOutTask(request);
        }
    }



    @Override
    public void onComplete() {
        super.onComplete();
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }
    @Override
    public void getTimeOutTaskSuc(PagerOfTimeOutTask data) {
        if(PageIndex==0)
        {
            mList.clear();
        }
        if(data.getData().size()>0)
        {
            mList.addAll(data.getData());
        }
        else {
            if(PageIndex>0)
            {
                PageIndex--;
            }
        }
        adapter.notifyDataSetChanged();
    }
}
