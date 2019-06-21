package kkkj.android.esafety.menu.task.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.InsepctTaskByEmployee;
import kkkj.android.esafety.customer.CustomerDialog;
import kkkj.android.esafety.customer.NewBillDialog;
import kkkj.android.esafety.customer.SlideRecyclerView;
import kkkj.android.esafety.home.model.AddBillModel;
import kkkj.android.esafety.menu.bill.view.MyTaskBillActivity;
import kkkj.android.esafety.menu.task.adapter.OvertimeTaskByEmployeeAdapter;
import kkkj.android.esafety.menu.task.contract.OvertimeTaskFContract;
import kkkj.android.esafety.menu.task.model.GetEmpTaskTimeOutByQRCoderModel;
import kkkj.android.esafety.menu.task.model.GetTimeTaskModel;
import kkkj.android.esafety.menu.task.presenter.OvertimeTaskFPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;

public class OvertimeTaskFragment extends MvpBaseFragment<OvertimeTaskFPresenter> implements OvertimeTaskFContract.View {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    SlideRecyclerView recyclerView;
    List<InsepctTaskByEmployee> mList;
    OvertimeTaskByEmployeeAdapter adapter;
    CustomerDialog dialog;
    NewBillDialog dialog2;
    boolean isCompleteJump;
    @Override
    protected int getLayout() {
        return R.layout.smart_slide_recycler;
    }

    @Override
    protected OvertimeTaskFPresenter getPresenter() {
        return new OvertimeTaskFPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initMonitorAndData() {
        mList = new ArrayList<>();
        smartRefreshLayout.setEnableLoadMore(false);
        adapter = new OvertimeTaskByEmployeeAdapter(R.layout.item_mytask2, mList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(mContext,MyTaskBillActivity.class));
                Logger.d(mList.get(position).getTaskDescription() + "~~~~~~~~~~~~~");
                dialog = new CustomerDialog(mContext, 200, mList.get(position).getTaskDescription()
                        , mList.get(position).getName() + "说明", "确认", "",
                        new CustomerDialog.OnDialogButtonClickListener() {
                            @Override
                            public void onDialogButtonClick(int requestCode, boolean isPositive) {
                                dialog.dismiss();
                            }
                        });
                dialog.show();
            }
        });
        adapter.setOnDeleteClickListener(new OvertimeTaskByEmployeeAdapter.OnDeleteClickLister() {
            @Override
            public void onDeleteClick(View view, int position) {
                dialog2 = new NewBillDialog(mContext, 200, mList.get(position).getTaskID(),new NewBillDialog.OnDialogButtonClickListener() {
                    @Override
                    public void onDialogButtonClick(int requestCode, String startt, String endt,boolean isjump) {
                        AddBillModel.Request request = new AddBillModel.Request();
                        Timestamp timestamp = new Timestamp(new Date().getTime());
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        request.setEndTime(df.format(timestamp));
                        request.setStartTime(df.format(timestamp));
                        request.setTaskID(mList.get(position).getTaskID());
                        isCompleteJump =isjump;
                        mPresenter.addbill(request);
                    }
                });
                dialog2.show();
                recyclerView.closeMenu();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                GetTimeTaskModel.Request request = new GetTimeTaskModel.Request();
                mPresenter.gettimetask(request);
            }
        });
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

    @Override
    public void addbillSuc(AddBillModel.Response response) {
        showToast("新建任务单据成功");
        if(isCompleteJump)
        {
            startActivity(new Intent(mContext, MyTaskBillActivity.class));
        }
    }

    @Override
    public void gettimetaskSuc(List<InsepctTaskByEmployee> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getEmpTaskTimeOutByQRCoderSuc(GetEmpTaskTimeOutByQRCoderModel.Response response) {
            mList.clear();
            mList.addAll(response.getData());
            adapter.notifyDataSetChanged();
    }
    @Override
    public void reserve(String remarks) {
        super.reserve(remarks);
        if(!TextUtils.isEmpty(remarks))
        {
            GetEmpTaskTimeOutByQRCoderModel.Request request = new GetEmpTaskTimeOutByQRCoderModel.Request();
            request.setDangerPointID(remarks);
            mPresenter.getEmpTaskTimeOutByQRCoder(request);
        }
    }
}
