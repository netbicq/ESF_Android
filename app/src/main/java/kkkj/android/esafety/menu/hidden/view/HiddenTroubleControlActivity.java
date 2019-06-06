package kkkj.android.esafety.menu.hidden.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.APPTroubleCtrView;
import kkkj.android.esafety.common.pickperson.PickPersonActivity;
import kkkj.android.esafety.customer.SlideRecyclerView;
import kkkj.android.esafety.menu.hidden.adapter.HiddenAdapter;
import kkkj.android.esafety.menu.hidden.contract.HiddenTroubleContract;
import kkkj.android.esafety.menu.hidden.customer.CheckCustomerDialog;
import kkkj.android.esafety.menu.hidden.model.AddTroubleCtrFlowModel;
import kkkj.android.esafety.menu.hidden.model.FiledModel;
import kkkj.android.esafety.menu.hidden.model.QuickHandleCtrModel;
import kkkj.android.esafety.menu.hidden.model.TransferPrincipalModel;
import kkkj.android.esafety.menu.hidden.presenter.HiddenTroublePresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;

/**
 * 隐患管控
 */
public class HiddenTroubleControlActivity extends MvpBaseActivity<HiddenTroublePresenter> implements HiddenTroubleContract.View {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    SlideRecyclerView recyclerView;
    List<APPTroubleCtrView> mList;
    HiddenAdapter adapter;
    QMUIDialog.EditTextDialogBuilder quickHandleDialog;
    QMUIDialog.EditTextDialogBuilder addTroubleCtrFlowDialog1;
    QMUIDialog confirmzr;
    QMUIDialog filedConfirmDialog;
    int FlowType = 1;
    CheckCustomerDialog checkCustomerDialog;
    int PickRequest = 300;
    TransferPrincipalModel.Request request;
    @Override
    protected int getLayout() {
        return R.layout.activity_hidden;
    }

    @Override
    protected HiddenTroublePresenter getPresenter() {
        return new HiddenTroublePresenter();
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("隐患管控");
        mList = new ArrayList<>();
        smartRefreshLayout.setEnableLoadMore(false);
        adapter = new HiddenAdapter(R.layout.item_hidden, mList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, HiddenDetailsActivity.class).putExtra("Trouble", mList.get(position)));
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_menu1:
//                        showToast("申请验收");
//                        addTroubleCtrFlowDialog1
                        if (addTroubleCtrFlowDialog1 == null) {
                            addTroubleCtrFlowDialog1 = new QMUIDialog.EditTextDialogBuilder(mContext);
                            addTroubleCtrFlowDialog1.setTitle("申请验收");
                            addTroubleCtrFlowDialog1.addAction("取消", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            });
                            addTroubleCtrFlowDialog1.addAction("确定", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    CharSequence text = addTroubleCtrFlowDialog1.getEditText().getText();
                                    if (text != null && text.length() > 0) {
                                        AddTroubleCtrFlowModel.Request request = new AddTroubleCtrFlowModel.Request();
                                        request.setControlID(mList.get(position).getKeyID());
                                        request.setFlowMemo(text.toString());
                                        FlowType = 1;
                                        request.setFlowType(FlowType);
                                        request.setFlowResult(0);
                                        mPresenter.addTroubleCtrFlow(request);
                                        dialog.dismiss();
                                    } else {
                                        showToast("请输入备注");
                                    }
                                }
                            });
                            addTroubleCtrFlowDialog1.setPlaceholder("请在此输入备注");
                            addTroubleCtrFlowDialog1.create();
                        }
                        addTroubleCtrFlowDialog1.show();
                        break;
                    case R.id.tv_menu2:
                        if (checkCustomerDialog == null) {
                            checkCustomerDialog = new CheckCustomerDialog(mContext, 300, new CheckCustomerDialog.OnDialogButtonClickListener() {
                                @Override
                                public void onDialogButtonClick(int requestCode, boolean isPositive, String content, int result) {
                                    if (isPositive) {
                                        AddTroubleCtrFlowModel.Request request = new AddTroubleCtrFlowModel.Request();
                                        request.setControlID(mList.get(position).getKeyID());
                                        request.setFlowMemo(content);
                                        FlowType = 2;
                                        request.setFlowType(FlowType);
                                        request.setFlowResult(result);
                                        mPresenter.addTroubleCtrFlow(request);
                                        checkCustomerDialog.dismiss();
                                    } else {
                                        checkCustomerDialog.dismiss();
                                    }
                                }
                            });
                        }
                        checkCustomerDialog.show();
                        break;
                    case R.id.tv_menu3:
                        startActivityForResult(new Intent(mContext, HandleCtrActivity.class).putExtra("Trouble", mList.get(position)),1000);
                        break;
                    case R.id.tv_menu4:
                        if (filedConfirmDialog == null) {
                            filedConfirmDialog = new QMUIDialog.MessageDialogBuilder(mActivity)
                                    .setTitle("提示")
                                    .setMessage("确定归档吗？")
                                    .addAction("取消", new QMUIDialogAction.ActionListener() {
                                        @Override
                                        public void onClick(QMUIDialog dialog, int index) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .addAction("确定", new QMUIDialogAction.ActionListener() {
                                        @Override
                                        public void onClick(QMUIDialog dialog, int index) {
                                            FiledModel.Request request = new FiledModel.Request();
                                            request.setCtrID(mList.get(position).getKeyID());
                                            mPresenter.filed(request);
                                            dialog.dismiss();
                                        }
                                    })
                                    .create();
                        }
                        filedConfirmDialog.show();
                        break;
                    case R.id.tv_menu5:
                        request = new TransferPrincipalModel.Request();
                        request.setCtrID(mList.get(position).getKeyID());
                        startActivityForResult(new Intent(mContext, PickPersonActivity.class), PickRequest);
                        break;
                    case R.id.tv_menu6:
                        if (quickHandleDialog == null) {
                            quickHandleDialog = new QMUIDialog.EditTextDialogBuilder(mContext);
                            quickHandleDialog.setTitle("快速处理");
                            quickHandleDialog.addAction("取消", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            });
                            quickHandleDialog.addAction("确定", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    CharSequence text = quickHandleDialog.getEditText().getText();
                                    if (text != null && text.length() > 0) {
                                        QuickHandleCtrModel.Request request = new QuickHandleCtrModel.Request();
                                        request.setCtrID(mList.get(position).getKeyID());
                                        request.setDescription(text.toString());
                                        mPresenter.quickHandleCtr(request);
                                        dialog.dismiss();
                                    } else {
                                        showToast("请输入备注");
                                    }
                                }
                            });
                            quickHandleDialog.setPlaceholder("请在此输入备注");
                            quickHandleDialog.create();
                        }
                        quickHandleDialog.show();
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getTroubleCtr();
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
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void getTroubleCtrSuc(List<APPTroubleCtrView> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void filedSuc(boolean result) {
        recyclerView.closeMenu();
        if (result) {
            showToast("归档成功");
            smartRefreshLayout.autoRefresh();
        } else {
            showToast("归档失败");
        }

    }

    @Override
    public void transferPrincipalSuc(boolean result) {
        recyclerView.closeMenu();
        if (result) {
            showToast("转让成功");
            smartRefreshLayout.autoRefresh();
        } else {
            showToast("转让失败");
        }
    }

    @Override
    public void quickHandleCtrSuc(boolean result) {
        recyclerView.closeMenu();
        if (result) {
            showToast("处理成功");
            smartRefreshLayout.autoRefresh();
        } else {
            showToast("处理失败");
        }
    }

    @Override
    public void addTroubleCtrFlowSuc(boolean result) {
        recyclerView.closeMenu();
        if (result) {
            if (FlowType == 1) {
                showToast("申请验收成功");
            } else {
                showToast("验收成功");
            }
            smartRefreshLayout.autoRefresh();
        } else {
            if (FlowType == 1) {
                showToast("申请验收失败");
            } else {
                showToast("验收失败");
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PickRequest) {
            if (resultCode == Activity.RESULT_OK) {
                String id = data.getStringExtra("PID");
                String name = data.getStringExtra("PName");

                confirmzr = new QMUIDialog.MessageDialogBuilder(mActivity)
                        .setTitle("提示")
                        .setMessage("确定要把责任人转让给"+name+"吗？")
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .addAction("确定", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                request.setPrincipalID(id);
                                mPresenter.transferPrincipal(request);
                                dialog.dismiss();
                            }
                        })
                        .create();
                confirmzr.show();
            }
        }
        else if(requestCode==1000){
            smartRefreshLayout.autoRefresh();
        }
    }
}
