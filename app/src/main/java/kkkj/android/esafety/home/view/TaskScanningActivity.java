package kkkj.android.esafety.home.view;

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
import kkkj.android.esafety.home.contract.TaskScanContract;
import kkkj.android.esafety.home.model.AddBillModel;
import kkkj.android.esafety.home.model.GetEmpTaskByQRCoderModel;
import kkkj.android.esafety.home.presenter.TaskScanPresenter;
import kkkj.android.esafety.menu.bill.view.MyTaskBillActivity;
import kkkj.android.esafety.menu.task.adapter.OvertimeTaskByEmployeeAdapter;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;

public class TaskScanningActivity extends MvpBaseActivity<TaskScanPresenter> implements TaskScanContract.View {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    SlideRecyclerView recyclerView;
    List<InsepctTaskByEmployee> mList;
    OvertimeTaskByEmployeeAdapter adapter;
    NewBillDialog dialog2;
    CustomerDialog dialog;
    String remarks="";
    boolean isCompleteJump;
    @Override
    protected int getLayout() {
        return R.layout.activity_scantask;
    }

    @Override
    protected TaskScanPresenter getPresenter() {
        return new TaskScanPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("任务");
        mList = new ArrayList<>();
        smartRefreshLayout.setEnableLoadMore(false);
        remarks = getIntent().getStringExtra("pointID");
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
                        isCompleteJump = isjump;
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
                if(!TextUtils.isEmpty(remarks))
                {
                    GetEmpTaskByQRCoderModel.Request request = new GetEmpTaskByQRCoderModel.Request();
                    request.setDangerPointID(remarks);
                    mPresenter.getEmpTaskByQRCoder(request);
                }
                else {
                    showToast("未获取到风控点ID");
                }
            }
        });

        if(!TextUtils.isEmpty(remarks))
        {
            GetEmpTaskByQRCoderModel.Request request = new GetEmpTaskByQRCoderModel.Request();
            request.setDangerPointID(remarks);
            mPresenter.getEmpTaskByQRCoder(request);
        }
        smartRefreshLayout.autoRefresh();
    }


    @Override
    public void onResume() {
        super.onResume();
        smartRefreshLayout.autoRefresh();
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
    public void getEmpTaskByQRCoderSuc(GetEmpTaskByQRCoderModel.Response response) {
        mList.clear();
        mList.addAll(response.getData());
        adapter.notifyDataSetChanged();
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
