package kkkj.android.esafety.menu.work.view;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.OpreateBillFlow;
import kkkj.android.esafety.menu.work.adapter.WorkDetailsAdapter;
import kkkj.android.esafety.menu.work.contract.WorkDetailsContract;
import kkkj.android.esafety.menu.work.model.BillFlowSetModel;
import kkkj.android.esafety.menu.work.model.GetOpreateFlowModel;
import kkkj.android.esafety.menu.work.model.OpreateBillFlowModel;
import kkkj.android.esafety.menu.work.presenter.WorkDetailsPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;

public class MyWorkDetailsActivity extends MvpBaseActivity<WorkDetailsPresenter> implements WorkDetailsContract.View {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_startt)
    TextView tv_startt;
    @BindView(R.id.tv_endt)
    TextView tv_endt;
    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.tv_principal)
    TextView tv_principal;
    WorkDetailsAdapter adapter;
    List<OpreateBillFlow> mList;
    String opreateid = "";
    QMUIDialog.EditTextDialogBuilder inputDialog;
    @Override
    protected int getLayout() {
        return R.layout.activity_mywork_details;
    }

    @Override
    protected WorkDetailsPresenter getPresenter() {
        return new WorkDetailsPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("作业详情");
        opreateid = getIntent().getStringExtra("opreateid");
        mList = new ArrayList<>();
        adapter = new WorkDetailsAdapter(R.layout.item_workdetails,mList);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                BillFlowSetModel.Request request = new BillFlowSetModel.Request();
                request.setBillID(opreateid);
                request.setOpreationFlowID(mList.get(position).getOpreationFlowID());
                inputDialog = new QMUIDialog.EditTextDialogBuilder(mContext);

                switch (view.getId())
                {
                    case R.id.btn_complete:
                        request.setFlowResult(1);
                        break;
                    case R.id.btn_stop:
                        request.setFlowResult(2);
                        break;
                    case R.id.btn_back:
                        request.setFlowResult(3);
                        break;
                }
                inputDialog.setTitle("添加备注")
                        .setPlaceholder("在此输入备注")
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .addAction("确定", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                CharSequence text = inputDialog.getEditText().getText();
                                if (text != null && text.length() > 0) {
                                    request.setFlowMemo(text.toString());
                                    mPresenter.billflowset(request);
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(mContext, "请输入备注", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).show();

            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void onResume() {
        super.onResume();
        GetOpreateFlowModel.Request request = new GetOpreateFlowModel.Request();
        request.setOpreateid(opreateid);
        mPresenter.getopreateflowmodel(request);
    }

    @Override
    public void getopreateflowmodelSuc(OpreateBillFlowModel data) {
        tv_name.setText(data.getBillName());
        tv_startt.setText(data.getStartTime());
        tv_endt.setText(data.getEndTime());
        tv_state.setText(data.getStateName());
        tv_principal.setText(data.getPrincipalEmployeeName());
        mList.clear();
        mList.addAll(data.getBillFlows());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void billflowsetSuc(BillFlowSetModel.Response response) {
        if(response.isData())
        {
            showToast("操作成功");
            GetOpreateFlowModel.Request request = new GetOpreateFlowModel.Request();
            request.setOpreateid(opreateid);
            mPresenter.getopreateflowmodel(request);
        }
        else {
            showToast(response.getMsg());
        }
    }
}
