package kkkj.android.esafety.menu.bill.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.TaskBillModel;
import kkkj.android.esafety.bean.WHYSDict;
import kkkj.android.esafety.customer.SlideRecyclerView;
import kkkj.android.esafety.menu.bill.adapter.BillAdapter;
import kkkj.android.esafety.menu.bill.contract.CurrentDocFContract;
import kkkj.android.esafety.menu.bill.model.DelTaskBillModel;
import kkkj.android.esafety.menu.bill.model.TaskBillOverModel;
import kkkj.android.esafety.menu.bill.presenter.CurrentDocFPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;
import kkkj.android.esafety.utils.NetUtils;

/**
 * 当前单据
 */
public class CurrentDocFragment extends MvpBaseFragment<CurrentDocFPresenter> implements CurrentDocFContract.View {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    SlideRecyclerView recyclerView;
    List<TaskBillModel> mList;
    BillAdapter adapter;
    @Override
    protected int getLayout() {
        return R.layout.smart_slide_recycler;
    }

    @Override
    protected CurrentDocFPresenter getPresenter() {
        return new CurrentDocFPresenter();
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
                        putExtra("mBillid",mList.get(position).getBillID()).putExtra("mType",0));
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_delete:
                        if(mList.get(position).getTaskType() == 2)
                        {
                            showErr("临时任务无法删除");
                            return;
                        }
                        DelTaskBillModel.Request request = new DelTaskBillModel.Request();
                        request.setBillid(mList.get(position).getBillID());
                        mPresenter.deltaskbill(request);
                        break;
                    case R.id.tv_complete:

                        TaskBillOverModel.Request request1 = new TaskBillOverModel.Request();
                        request1.setBillid(mList.get(position).getBillID());
                        mPresenter.taskbillover(request1);
                        break;
                    case R.id.tv_see:


                        if(NetUtils.checkNetWork())
                        {
                            startActivity(new Intent(mContext, MyTaskDetailsActivity.class).
                                    putExtra("mBillid",mList.get(position).getBillID()).putExtra("mType",1));
                        }
                        else {
                            showToast("无网络时无法查看已完成任务");
                        }

                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.gettaskbills();
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
    public void gettaskbillsSuc(List<TaskBillModel> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
        for(int i = 0 ;i<data.size();i++)
        {
            for (int j = 0; j < data.get(i).getWHYSDicts().size(); j++) {
                WHYSDict whysDict = new WHYSDict();
                whysDict.setBillID(data.get(i).getBillID());
                whysDict.setDictName(data.get(i).getWHYSDicts().get(j).getDictName());
                whysDict.setKeyID(data.get(i).getWHYSDicts().get(j).getKeyID());
                whysDict.setMaxValue(data.get(i).getWHYSDicts().get(j).getMaxValue());
                whysDict.setMinValue(data.get(i).getWHYSDicts().get(j).getMinValue());
                whysDict.saveOrUpdate("KeyID = ? and BillID=?",whysDict.getKeyID(),whysDict.getBillID());
                Logger.d("--------------------"+whysDict.getDictName());
            }
        }

    }

    @Override
    public void deltaskbillSuc() {
        showToast("删除成功");
        smartRefreshLayout.autoRefresh();
    }

    @Override
    public void taskbilloverSuc() {
        showToast("提交成功");
        smartRefreshLayout.autoRefresh();
    }
}
