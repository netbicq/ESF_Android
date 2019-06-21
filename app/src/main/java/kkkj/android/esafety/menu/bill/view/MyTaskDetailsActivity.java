package kkkj.android.esafety.menu.bill.view;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.orhanobut.logger.Logger;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.SubStandard;
import kkkj.android.esafety.bean.Subject;
import kkkj.android.esafety.bean.TaskSubjectView;
import kkkj.android.esafety.customer.SlideRecyclerView;
import kkkj.android.esafety.menu.bill.adapter.TaskSubjectViewAdapter;
import kkkj.android.esafety.menu.bill.contract.MyTaskDetailsContract;
import kkkj.android.esafety.menu.bill.model.AddTaskSubjectModel;
import kkkj.android.esafety.menu.bill.model.DelSubResultModel;
import kkkj.android.esafety.menu.bill.model.GetSubjectsModel;
import kkkj.android.esafety.menu.bill.model.GetTaskSuboverModel;
import kkkj.android.esafety.menu.bill.presenter.MyTaskDetailsPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.utils.NetUtils;

/**
 * 任务详情页
 */
public class MyTaskDetailsActivity extends MvpBaseActivity<MyTaskDetailsPresenter> implements MyTaskDetailsContract.View, View.OnClickListener {
    @BindView(R.id.tl_1)
    SegmentTabLayout tabLayout_1;
    @BindView(R.id.btn_sub)
    QMUIRoundButton btn_sub;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    SlideRecyclerView recyclerView;
    @BindView(R.id.line)
    View line;
    List<TaskSubjectView> mList;
    List<TaskSubjectView> mList1;
    List<TaskSubjectView> mList2;
    List<TaskSubjectView> mList3;
    TaskSubjectViewAdapter adapter;
    List<TaskSubjectView> mData;
    String mBillid;
    int mType = 0;
    List<Subject> subjectList;
    int index = 0;
    int count = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_taskdetails;
    }

    @Override
    protected MyTaskDetailsPresenter getPresenter() {
        return new MyTaskDetailsPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("任务详情");
        btn_sub.setOnClickListener(this);
        mBillid = getIntent().getStringExtra("mBillid");
        mType = getIntent().getIntExtra("mType", 0);
        String[] mTitles = {"设备设施", "岗位", "作业"};
        tabLayout_1.setTabData(mTitles);
        mList = new ArrayList<>();
        mData = new ArrayList<>();
        adapter = new TaskSubjectViewAdapter(R.layout.item_taskdetails, mList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent(mContext, TaskResultActivity.class);
                if (mType == 0) {
                    //当前检查单
                    intent.putExtra("subresultid", "");
                } else {
                    //历史检查单
                    intent.putExtra("subresultid", mList.get(position).getSubResultID());
                }
                intent.putExtra("billID", mList.get(position).getBillID());
                intent.putExtra("subjectID", mList.get(position).getSubID());
                intent.putExtra("subjectType", mList.get(position).getSubType());
                intent.putExtra("DangerID", mList.get(position).getDangerID());
                intent.putExtra("IsControl", mList.get(position).isControl());
                intent.putExtra("KeyID", mList.get(position).getKeyID());
                intent.putExtra("LastResult", mList.get(position).getLastResult());

                startActivity(intent);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                DelSubResultModel.Request request = new DelSubResultModel.Request();
                request.setSubresultid(mList.get(position).getSubResultID());
                mPresenter.delsubresult(request);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        if (mType == 0) {
            recyclerView.setCanMove(false);
        } else {
            recyclerView.setCanMove(true);
        }
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (mType == 0) {
                    //当前检查单
                    GetSubjectsModel.Request request = new GetSubjectsModel.Request();
                    request.setBillid(mBillid);
                    mPresenter.getsubjects(request);
                } else {
                    //历史检查单
                    GetTaskSuboverModel.Request request = new GetTaskSuboverModel.Request();
                    request.setBillid(mBillid);
                    mPresenter.gettasksubover(request);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        smartRefreshLayout.autoRefresh();
    }
    //当前检查单
    @Override
    public void getsubjectsSuc(List<TaskSubjectView> data) {
        mData = data;
        initTab();
        for(int i = 0 ;i<data.size();i++)
        {
            for(int j = 0 ;j<data.get(i).getSubStandards().size();j++)
            {
                data.get(i).getSubStandards().get(j).setKeyID(data.get(i).getKeyID());
                data.get(i).getSubStandards().get(j).
                        saveOrUpdateAsync("KeyID = ? and SubStandardID = ?",data.get(i).getKeyID(),
                                data.get(i).getSubStandards().get(j).getSubStandardID());
            }

        }

    }
    //历史检查单
    @Override
    public void gettasksuboverSuc(List<TaskSubjectView> data) {
        mData = data;
        initTab();
        for(int i = 0 ;i<data.size();i++)
        {
            for(int j = 0 ;j<data.get(i).getSubStandards().size();j++)
            {
                data.get(i).getSubStandards().get(j).setKeyID(data.get(i).getKeyID());
                data.get(i).getSubStandards().get(j).
                        saveOrUpdateAsync("KeyID = ? and SubStandardID = ?",data.get(i).getKeyID(),
                                data.get(i).getSubStandards().get(j).getSubStandardID());
            }

        }

    }

    public void initTab() {


        if (mType == 0) {
            if (NetUtils.checkNetWork()) {
                btn_sub.setVisibility(View.VISIBLE);
            } else {
                btn_sub.setVisibility(View.GONE);
            }
        } else {
            btn_sub.setVisibility(View.GONE);
        }
        mList1 = new ArrayList<>();
        mList2 = new ArrayList<>();
        mList3 = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            if (!TextUtils.isEmpty(mData.get(i).getSubTypeName())) {
                if (mData.get(i).getSubTypeName().equals("设备设施")) {
                    mList1.add(mData.get(i));
                } else if (mData.get(i).getSubTypeName().equals("岗位")) {
                    mList2.add(mData.get(i));
                } else if (mData.get(i).getSubTypeName().equals("作业")) {
                    mList3.add(mData.get(i));
                }
            }
        }
        if (mList1.size() > 0) {
            tabLayout_1.showDot(0);
        }
        else {
            tabLayout_1.hideMsg(0);
        }
        if (mList2.size() > 0) {
            tabLayout_1.showDot(1);
        }
        else {
            tabLayout_1.hideMsg(1);
        }
        if (mList3.size() > 0) {
            tabLayout_1.showDot(2);
        }
        else {
            tabLayout_1.hideMsg(2);
        }
        mList.clear();
        if (index == 0) {
            mList.addAll(mList1);
        } else if (index == 1) {
            mList.addAll(mList2);
        } else {
            mList.addAll(mList3);
        }
        adapter.notifyDataSetChanged();
//        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
//            @Override
//            public int getOldListSize() {
//                return adapter.getData().size();
//            }
//
//            @Override
//            public int getNewListSize() {
//                return mList.size();
//            }
//
//            @Override
//            public boolean areItemsTheSame(int i, int i1) {
//                return adapter.getData().get(i).getClass().equals(mList.get(i1).getClass());
//            }
//
//            @Override
//            public boolean areContentsTheSame(int i, int i1) {
//                return adapter.getData().get(i).equals(mList.get(i1));
//            }
//        });
//        diffResult.dispatchUpdatesTo(adapter);
        tabLayout_1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                index = position;
                mList.clear();
                switch (position) {
                    case 0:
                        mList.addAll(mList1);
                        break;
                    case 1:
                        mList.addAll(mList2);
                        break;
                    case 2:
                        mList.addAll(mList3);
                        break;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void delsubresultSuc() {

        showToast("删除成功");
        smartRefreshLayout.autoRefresh();
    }

    @Override
    public void addtasksubjectSuc() {
        count++;
        if (count == subjectList.size()) {
            showToast("任务提交完毕");
            smartRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void onComplete() {
        super.onComplete();
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishLoadMore();
            smartRefreshLayout.finishRefresh();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sub:
                subjectList = new ArrayList<>();
                count = 0;
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getState() != 0) {
                        List<Subject> subjects = LitePal.where("SubjectID = ? and BillID = ?and DangerID = ?"
                                , mList.get(i).getSubID(), mList.get(i).getBillID(), mList.get(i).getDangerID()).find(Subject.class);
                        subjectList.addAll(subjects);

                    }
                }
                if (subjectList.size() > 0) {
                    showLoading();
                    for (int j = 0; j < subjectList.size(); j++) {
                        Subject subject = subjectList.get(j);
                        AddTaskSubjectModel.Request request = new AddTaskSubjectModel.Request();
                        request.setTime(subject.getTime());
                        request.setAttachFiles(subject.getAttachFiles());
                        request.setTaskResultMemo(subject.getTaskResultMemo());
                        request.setTaskResult(subject.getTaskResult());
                        request.setSubjectType(subject.getSubjectType());
                        request.setSubjectID(subject.getSubjectID());
                        request.setBillID(subject.getBillID());
                        request.setDangerID(subject.getDangerID());

                        request.setEval_WHYS(subject.getEval_WHYS());
                        request.setEval_SGLX(subject.getEval_SGLX());
                        request.setEval_SGJG(subject.getEval_SGJG());
                        request.setEval_YXFW(subject.getEval_YXFW());
                        request.setEval_Method(subject.getEval_Method());
                        request.setDangerLevel(subject.getDangerLevel());
                        request.setTroubleLevel(subject.getTroubleLevel());
                        request.setPrincipalID(subject.getPrincipalID());

                        request.setLECD_L(subject.getLECD_L());
                        request.setLECD_E(subject.getLECD_E());
                        request.setLECD_C(subject.getLECD_C());
                        request.setLSD_L(subject.getLSD_L());
                        request.setLSD_S(subject.getLSD_S());
                        request.setDValue(subject.getDValue());

                        mPresenter.addtasksubject(request);
                    }
                } else {
                    showToast("未发现未提交的任务");
                }
                break;
        }
    }
}
