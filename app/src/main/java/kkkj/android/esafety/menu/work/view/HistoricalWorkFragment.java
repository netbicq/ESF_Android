package kkkj.android.esafety.menu.work.view;
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
import kkkj.android.esafety.bean.OpreateBillByEmp;
import kkkj.android.esafety.menu.work.adapter.CurrentWorkAdapter;
import kkkj.android.esafety.menu.work.contract.HistoricalWorkContract;
import kkkj.android.esafety.menu.work.presenter.HistoricalWorkPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;

/**
 * 历史单据
 */
public class HistoricalWorkFragment extends MvpBaseFragment<HistoricalWorkPresenter> implements HistoricalWorkContract.View {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    List<OpreateBillByEmp> mList;
    CurrentWorkAdapter adapter;
    @Override
    protected int getLayout() {
        return R.layout.smart_recycler;
    }

    @Override
    protected HistoricalWorkPresenter getPresenter() {
        return new HistoricalWorkPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        smartRefreshLayout.autoRefresh();
    }

    @Override
    protected void initMonitorAndData() {
        mList = new ArrayList<>();
        smartRefreshLayout.setEnableLoadMore(false);
        adapter = new CurrentWorkAdapter(R.layout.item_work, mList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, MyWorkDetailsActivity.class).
                        putExtra("opreateid",mList.get(position).getOpreateBillID()));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getoverlist();
            }
        });
    }

    @Override
    public void onComplete() {
        super.onComplete();
        if(smartRefreshLayout!=null)
        {
            smartRefreshLayout.finishRefresh();
        }
    }
    @Override
    public void getoverlistSuc(List<OpreateBillByEmp> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
