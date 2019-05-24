package kkkj.android.esafety.home.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import kkkj.android.esafety.home.contract.WindControlSContract;
import kkkj.android.esafety.home.model.GetTaskBillMastersOverByQRCoderModel;
import kkkj.android.esafety.home.presenter.WindControlSPresenter;
import kkkj.android.esafety.menu.bill.adapter.BillAdapter;
import kkkj.android.esafety.menu.bill.view.MyTaskDetailsActivity;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;

public class WindControlScanningActivity extends MvpBaseActivity<WindControlSPresenter> implements WindControlSContract.View {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    List<TaskBillModel> mList;
    BillAdapter adapter;
    GetTaskBillMastersOverByQRCoderModel.Request request;
    String pointID = "";
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;

    @Override
    public void getTaskBillMastersOverByQRCoderSuc(GetTaskBillMastersOverByQRCoderModel.Response response) {
        if (response != null) {
            mList.clear();
            mList.addAll(response.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_windcontrols;
    }

    @Override
    protected WindControlSPresenter getPresenter() {
        return new WindControlSPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("已完成单据");
        pointID = getIntent().getStringExtra("pointID");
        request = new GetTaskBillMastersOverByQRCoderModel.Request();
        mList = new ArrayList<>();
        smartRefreshLayout.setEnableLoadMore(false);
        adapter = new BillAdapter(R.layout.item_taskbills, mList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, MyTaskDetailsActivity.class).
                        putExtra("mBillid", mList.get(position).getBillID()).putExtra("mType", 1));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        request.setPointID(pointID);
        mPresenter.getTaskBillMastersOverByQRCoder(request);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (!TextUtils.isEmpty(request.getPointID())) {
                    mPresenter.getTaskBillMastersOverByQRCoder(request);
                }
                else {
                    showToast("未获取到风险点ID");
//                    getPermissions();
                }
            }
        });
    }

    @Override
    public void onComplete() {
        super.onComplete();
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        smartRefreshLayout.autoRefresh();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        onComplete();
//        if (requestCode == REQUEST_CODE) {
//            //处理扫描结果（在界面上显示）
//            if (null != data) {
//                Bundle bundle = data.getExtras();
//                if (bundle == null) {
//                    return;
//                }
//                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
//                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    Logger.d(result);
//                    request.setPointID(result);
//                    mPresenter.getTaskBillMastersOverByQRCoder(request);
////                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
//                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                    mToast.showText("解析二维码失败");
//
//                }
//            }
//        }
//    }
//
//    /**
//     * 获取权限
//     */
//    private void getPermissions() {
//        rxPermissions.requestEachCombined(Manifest.permission.CAMERA,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE
//        )
//                .subscribe(permission -> { // will emit 1 Permission object
//                    if (permission.granted) {
//                        Intent intent = new Intent(getApplication(), CaptureActivity.class);
//                        startActivityForResult(intent, REQUEST_CODE);
//                    } else if (permission.shouldShowRequestPermissionRationale) {
//                        //有至少一个权限没有同意
//                        mToast.showText("请同意全部权限");
//                    } else {
//                        //有至少一个权限没有同意且勾选了不在提示
//                        mToast.showText("请在权限管理中打开相关权限");
//                    }
//                });
//    }
}
