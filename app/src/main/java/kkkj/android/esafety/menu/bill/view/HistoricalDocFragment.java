package kkkj.android.esafety.menu.bill.view;

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
import kkkj.android.esafety.bean.TaskBillModel;
import kkkj.android.esafety.menu.bill.adapter.BillAdapter;
import kkkj.android.esafety.menu.bill.contract.HistoricalDocContract;
import kkkj.android.esafety.menu.bill.presenter.HistoricalDocPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;

/**
 * 历史单据
 */
public class HistoricalDocFragment extends MvpBaseFragment<HistoricalDocPresenter> implements HistoricalDocContract.View {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    List<TaskBillModel> mList;
    BillAdapter adapter;
    @Override
    protected int getLayout() {
        return R.layout.smart_recycler;
    }

    @Override
    protected HistoricalDocPresenter getPresenter() {
        return new HistoricalDocPresenter();
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
        adapter = new BillAdapter(R.layout.item_taskbills, mList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, MyTaskDetailsActivity.class).
                        putExtra("mBillid",mList.get(position).getBillID()).putExtra("mType",1));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.gettaskbillsover();
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
    public void gettaskbillsoverSuc(List<TaskBillModel> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
