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
import kkkj.android.esafety.menu.statistics.adapter.DangerPointChildAdapter;
import kkkj.android.esafety.menu.statistics.contract.DangerPointChildContract;
import kkkj.android.esafety.bean.APPDangerPointView;
import kkkj.android.esafety.menu.statistics.model.GetDangerPointsPageModel;
import kkkj.android.esafety.bean.PagerOfAPPDangerPointView;
import kkkj.android.esafety.menu.statistics.presenter.DangerPointChildPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;

public class DangerPointChildFm extends MvpBaseFragment<DangerPointChildPresenter> implements DangerPointChildContract.View {
    String pointID;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    DangerPointChildAdapter adapter;
    List<APPDangerPointView> mList;

    GetDangerPointsPageModel.Request request ;
    int PageSize=10;
    int PageIndex=0;
    public static DangerPointChildFm getInstance(String pointID) {
        DangerPointChildFm fm = new DangerPointChildFm();
        fm.pointID = pointID;
        return fm;
    }

    @Override
    protected int getLayout() {
        return R.layout.smart_recycler;
    }

    @Override
    protected DangerPointChildPresenter getPresenter() {
        return new DangerPointChildPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        request = new GetDangerPointsPageModel.Request();
        mList = new ArrayList<>();
        adapter = new DangerPointChildAdapter(R.layout.item_dangerpoint_child,mList);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                PageIndex = 0;
                request.setPageIndex(PageIndex);
                request.setPageSize(PageSize);
                request.setQuery(pointID);
                mPresenter.getDangerPointsPage(request);
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                PageIndex++;
                request.setPageIndex(PageIndex);
                request.setPageSize(PageSize);
                request.setQuery(pointID);
                mPresenter.getDangerPointsPage(request);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        request.setPageIndex(PageIndex);
        request.setPageSize(PageSize);
        request.setQuery(pointID);
        mPresenter.getDangerPointsPage(request);
    }

    @Override
    public void getDangerLevelsSuc(PagerOfAPPDangerPointView data) {
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

    @Override
    public void onComplete() {
        super.onComplete();
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }
}
