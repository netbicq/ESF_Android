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
import kkkj.android.esafety.bean.PagerOfTroubleCtrsPage;
import kkkj.android.esafety.bean.TroubleCtrsPage;
import kkkj.android.esafety.menu.statistics.adapter.ControlsChildAdapter;
import kkkj.android.esafety.menu.statistics.contract.ControlsChildContract;
import kkkj.android.esafety.menu.statistics.model.GetTroubleCtrsPageModel;
import kkkj.android.esafety.menu.statistics.presenter.ControlsChildPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;

public class ControlsChildFm extends MvpBaseFragment<ControlsChildPresenter> implements ControlsChildContract.View {
    int menuValue;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    ControlsChildAdapter adapter;
    List<TroubleCtrsPage> mList;

    GetTroubleCtrsPageModel.Request request ;
    int PageSize=10;
    int PageIndex=0;
    public static ControlsChildFm getInstance(int menuValue) {
        ControlsChildFm fm = new ControlsChildFm();
        fm.menuValue = menuValue;
        return fm;
    }

    @Override
    protected int getLayout() {
        return R.layout.smart_recycler;
    }

    @Override
    protected ControlsChildPresenter getPresenter() {
        return new ControlsChildPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        request = new GetTroubleCtrsPageModel.Request();
        mList = new ArrayList<>();
        adapter = new ControlsChildAdapter(R.layout.item_control_child,mList);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                PageIndex = 0;
                request.setPageIndex(PageIndex);
                request.setPageSize(PageSize);
                request.setQuery(menuValue);
                mPresenter.getTroubleCtrsPage(request);
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                PageIndex++;
                request.setPageIndex(PageIndex);
                request.setPageSize(PageSize);
                request.setQuery(menuValue);
                mPresenter.getTroubleCtrsPage(request);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        request.setPageIndex(PageIndex);
        request.setPageSize(PageSize);
        request.setQuery(menuValue);
        mPresenter.getTroubleCtrsPage(request);
    }



    @Override
    public void onComplete() {
        super.onComplete();
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    public void getTroubleCtrsPageSuc(PagerOfTroubleCtrsPage data) {
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
