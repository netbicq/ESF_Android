package kkkj.android.esafety.home.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.litepal.LitePal;
import org.litepal.crud.callback.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.BillData;
import kkkj.android.esafety.bean.DangerLevelDict;
import kkkj.android.esafety.bean.Dict;
import kkkj.android.esafety.bean.DownloadData;
import kkkj.android.esafety.bean.Emp;
import kkkj.android.esafety.bean.EnumItem;
import kkkj.android.esafety.bean.Org;
import kkkj.android.esafety.bean.SubStandard;
import kkkj.android.esafety.bean.TaskBillModel;
import kkkj.android.esafety.bean.TaskSubjectView;
import kkkj.android.esafety.bean.WHYSDict;
import kkkj.android.esafety.home.contract.TabHomeContract;
import kkkj.android.esafety.bean.HomeMenu;
import kkkj.android.esafety.home.model.GetEmpTaskByQRCoderModel;
import kkkj.android.esafety.home.presenter.TabHomePresenter;
import kkkj.android.esafety.menu.bill.view.MyTaskBillActivity;
import kkkj.android.esafety.menu.hidden.view.HiddenTroubleControlActivity;
import kkkj.android.esafety.menu.institution.view.InstitutionActivity2;
import kkkj.android.esafety.menu.temptask.view.AddTemWorkActivity;
import kkkj.android.esafety.menu.vedio.view.VideoMonitorListActivity;
import kkkj.android.esafety.menu.work.view.MyWorkActivity2;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;
import kkkj.android.esafety.utils.NetUtils;
import kkkj.android.esafety.home.adapter.*;
import kkkj.android.esafety.menu.task.view.*;
public class TabHome extends MvpBaseFragment<TabHomePresenter> implements View.OnClickListener, TabHomeContract.View {
    @BindView(R.id.action_bar_title)
    TextView mTitle;
    @BindView(R.id.action_bar_right_iv)
    ImageView action_bar_right_iv;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    List<HomeMenu> menuList;
    HomeMenuAdapter adapter;
    int OverTimeTaskCount = 0;
    String result = "";
    /**
     * ????????????Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;

    @Override
    protected int getLayout() {
        return R.layout.tab_home;
    }

    @Override
    protected TabHomePresenter getPresenter() {
        return new TabHomePresenter();
    }

    @Override
    protected void initMonitorAndData() {
        mTitle.setText("??????????????????");
        action_bar_right_iv.setVisibility(View.VISIBLE);
        action_bar_right_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermissions();
            }
        });
        smartRefreshLayout.setEnableLoadMore(false);
        menuList = new ArrayList<>();
//        setMenu();
        adapter = new HomeMenuAdapter(R.layout.item_home_menu, menuList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String menuName = menuList.get(position).getName();
                switch (menuName) {
                    case "????????????":
                        startActivity(new Intent(mContext, MyTaskActivity.class).putExtra("OverTimeTaskCount", OverTimeTaskCount));
                        break;
                    case "???????????????":
                        //????????????
                        startActivity(new Intent(mContext, MyTaskBillActivity.class));
                        break;
                    case "????????????":
                        startActivity(new Intent(mContext, MyWorkActivity2.class));
                        break;
                    case "????????????":
                        startActivity(new Intent(mContext, AddTemWorkActivity.class));
                        break;
                    case "????????????":
                        //???????????????
                        startActivity(new Intent(mContext, InstitutionActivity2.class));
                        break;
                    case "????????????":
                        //????????????
//                        startActivity(new Intent(mContext, WebViewActivity.class).putExtra("title", "????????????"));
                        startActivity(new Intent(mContext, VideoMonitorListActivity.class));
                        break;
//                    case "???????????????":
//                        startActivity(new Intent(mContext, WindControlScanningActivity.class));
//                        break;
                    case "????????????":
                        startActivity(new Intent(mContext, HiddenTroubleControlActivity.class));
                        break;

                }
            }
        });
        setMenu();
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new GridLayoutManager(mContext, 3));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if(NetUtils.checkNetWork())
                {
                    mPresenter.downloaddata();
                }
                else {
                    showErr("???????????????");
                    onComplete();
                }
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
    public void onResume() {
        super.onResume();
        Logger.d("onResume");
    }

    public void setMenu() {
        HomeMenu menu1 = new HomeMenu();
        HomeMenu menu2 = new HomeMenu();
        HomeMenu menu3 = new HomeMenu();
        HomeMenu menu4 = new HomeMenu();
        HomeMenu menu5 = new HomeMenu();
        HomeMenu menu6 = new HomeMenu();
        HomeMenu menu7 = new HomeMenu();
        HomeMenu menu8 = new HomeMenu();
        menu1.setImgId(R.drawable.ic6);
        menu1.setName("????????????");
        menu1.setCount(OverTimeTaskCount);
        menu7.setImgId(R.drawable.ic27);
        menu7.setName("???????????????");
        menu2.setImgId(R.drawable.ic5);
        menu2.setName("????????????");
        menu3.setImgId(R.drawable.ic4);
        menu3.setName("????????????");
//        menu1.setCount(1);
        menu4.setImgId(R.drawable.ic34);
        menu4.setName("???????????????");
        menu5.setImgId(R.drawable.ic9);
        menu5.setName("????????????");
        menu6.setImgId(R.drawable.ic10);
        menu6.setName("????????????");
        menu8.setImgId(R.drawable.ic36);
        menu8.setName("????????????");
        menuList.clear();
        menuList.add(menu1);
        menuList.add(menu7);
        menuList.add(menu2);
        menuList.add(menu3);
        menuList.add(menu5);
        menuList.add(menu6);
//        menuList.add(menu4);
        menuList.add(menu8);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                showToast("??????????????????");
                break;
        }
    }

    @Override
    public void downloaddataSuc(DownloadData data) {
        if (data != null) {
            OverTimeTaskCount = data.getOverTimeTaskCount();
            menuList.get(0).setCount(OverTimeTaskCount);

            List<BillData> mBillDatas = data.getBillDatas();
            int count = 0;//?????????????????????
            int SubCount= 0;//?????????
            int SubCheckedCount=0;//?????????
            for (int i=0;i<mBillDatas.size();i++) {
                BillData mBillData = mBillDatas.get(i);
                SubCount  = mBillData.getSubCount();
                SubCheckedCount = mBillData.getSubCheckedCount();
                if (SubCount == SubCheckedCount) {
                    count = count + 1;
                }
            }

            menuList.get(1).setCount(count);

            adapter.notifyDataSetChanged();
            LitePal.deleteAll(TaskBillModel.class);
            LitePal.deleteAll(TaskSubjectView.class);
            LitePal.deleteAll(SubStandard.class);
            LitePal.deleteAll(Dict.class);
            LitePal.deleteAll(DangerLevelDict.class);
            LitePal.deleteAll(EnumItem.class);
            LitePal.deleteAll(Emp.class);
            LitePal.deleteAll(Org.class);

            for (int i = 0; i < data.getBillDatas().size(); i++) {
                TaskBillModel taskBillModel = new TaskBillModel();
                taskBillModel.setBillID(data.getBillDatas().get(i).getBillID());
                taskBillModel.setDangerName(data.getBillDatas().get(i).getDangerName());
                taskBillModel.setEmployeeName(data.getBillDatas().get(i).getEmployeeName());
                taskBillModel.setEndTime(data.getBillDatas().get(i).getEndTime());
                taskBillModel.setStartTime(data.getBillDatas().get(i).getStartTime());
                taskBillModel.setState(data.getBillDatas().get(i).getState());
                taskBillModel.setSubCheckedCount(data.getBillDatas().get(i).getSubCheckedCount());
                taskBillModel.setSubCount(data.getBillDatas().get(i).getSubCount());
                taskBillModel.setTaskName(data.getBillDatas().get(i).getTaskName());
                int finalI = i;
                taskBillModel.saveOrUpdateAsync("BillID = ?", data.getBillDatas().get(i).getBillID()).listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(success) {

                            for (int j = 0; j < data.getBillDatas().get(finalI).getCheckSubs().size(); j++) {
                                TaskSubjectView taskSubjectView = new TaskSubjectView();
                                taskSubjectView.setDangerName(data.getBillDatas().get(finalI).getCheckSubs().get(j).getDangerName());
                                taskSubjectView.setDangerID(data.getBillDatas().get(finalI).getCheckSubs().get(j).getDangerID());
                                taskSubjectView.setBillID(data.getBillDatas().get(finalI).getCheckSubs().get(j).getBillID());
                                taskSubjectView.setDangerLevel(data.getBillDatas().get(finalI).getCheckSubs().get(j).getDangerLevel());
                                taskSubjectView.setKeyID(data.getBillDatas().get(finalI).getCheckSubs().get(j).getKeyID());
                                taskSubjectView.setPrincipal(data.getBillDatas().get(finalI).getCheckSubs().get(j).getPrincipal());
                                taskSubjectView.setPrincipalTel(data.getBillDatas().get(finalI).getCheckSubs().get(j).getPrincipalTel());
                                taskSubjectView.setSubID(data.getBillDatas().get(finalI).getCheckSubs().get(j).getSubID());
                                taskSubjectView.setSubName(data.getBillDatas().get(finalI).getCheckSubs().get(j).getSubName());
                                taskSubjectView.setSubResultID(data.getBillDatas().get(finalI).getCheckSubs().get(j).getSubResultID());
                                taskSubjectView.setSubType(data.getBillDatas().get(finalI).getCheckSubs().get(j).getSubType());
                                taskSubjectView.setSubTypeName(data.getBillDatas().get(finalI).getCheckSubs().get(j).getSubTypeName());
                                taskSubjectView.setControl(data.getBillDatas().get(finalI).getCheckSubs().get(j).isControl());
                                taskSubjectView.setSubStandards(data.getBillDatas().get(finalI).getCheckSubs().get(j).getSubStandards());

                                taskSubjectView.saveOrUpdateAsync("KeyID = ?", data.getBillDatas().get(finalI).getCheckSubs().get(j).getKeyID())
                                        .listen(new SaveCallback() {
                                            @Override
                                            public void onFinish(boolean success) {
                                                if(!success)
                                                {
                                                    showErr("?????????????????????TaskSubjectView");
                                                }
                                            }
                                        });
                            }
                            for (int j = 0; j < data.getBillDatas().get(finalI).getWHYSDicts().size(); j++) {
                                WHYSDict whysDict = new WHYSDict();
                                whysDict.setBillID(data.getBillDatas().get(finalI).getBillID());
                                whysDict.setDictName(data.getBillDatas().get(finalI).getWHYSDicts().get(j).getDictName());
                                whysDict.setKeyID(data.getBillDatas().get(finalI).getWHYSDicts().get(j).getKeyID());
                                whysDict.setMaxValue(data.getBillDatas().get(finalI).getWHYSDicts().get(j).getMaxValue());
                                whysDict.setMinValue(data.getBillDatas().get(finalI).getWHYSDicts().get(j).getMinValue());
                                whysDict.saveOrUpdateAsync("KeyID = ? and BillID=?",data.getBillDatas().get(finalI).getWHYSDicts().get(j).getKeyID(),data.getBillDatas().get(finalI).getBillID()).listen(new SaveCallback() {
                                    @Override
                                    public void onFinish(boolean success) {
                                        if(!success)
                                        {
                                            showErr("?????????????????????WHYSDict");
                                        }
                                    }
                                });
                                Logger.d("--------------------"+whysDict.getDictName());
                            }
                        }
                        else {
                            showErr("?????????????????????TaskBillModel");
                        }
                    }
                });


            }
            for(int i = 0 ;i<data.getLECD_Ls().size();i++)
            {
                data.getLECD_Ls().get(i).setName("LECD_Ls");
                data.getLECD_Ls().get(i).saveOrUpdateAsync("KeyID=?",data.getLECD_Ls().get(i).getKeyID()).listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(!success)
                        {
                            showErr("?????????????????????LECD_Ls");
                        }
                    }
                });
            }
            for(int i = 0 ;i<data.getLECD_Es().size();i++)
            {
                data.getLECD_Es().get(i).setName("LECD_Es");
                data.getLECD_Es().get(i).saveOrUpdateAsync("KeyID=?",data.getLECD_Es().get(i).getKeyID())
                .listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(!success)
                        {
                            showErr("?????????????????????LECD_Es");
                        }
                    }
                });
            }
            for(int i = 0 ;i<data.getLECD_Cs().size();i++)
            {
                data.getLECD_Cs().get(i).setName("LECD_Cs");
                data.getLECD_Cs().get(i).saveOrUpdateAsync("KeyID=?",data.getLECD_Cs().get(i).getKeyID())
                .listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(!success)
                        {
                            showErr("?????????????????????LECD_Cs");
                        }
                    }
                });
            }
            for(int i = 0 ;i<data.getLSD_Ls().size();i++)
            {
                data.getLSD_Ls().get(i).setName("LSD_Ls");
                data.getLSD_Ls().get(i).saveOrUpdateAsync("KeyID=?",data.getLSD_Ls().get(i).getKeyID())
                .listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(!success)
                        {
                            showErr("?????????????????????LSD_Ls");
                        }
                    }
                });
            }
            for(int i = 0 ;i<data.getLSD_Ss().size();i++)
            {
                data.getLSD_Ss().get(i).setName("LSD_Ss");
                data.getLSD_Ss().get(i).saveOrUpdateAsync("KeyID=?",data.getLSD_Ss().get(i).getKeyID())
                .listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(!success)
                        {
                            showErr("?????????????????????LSD_Ss");
                        }
                    }
                });
            }
            for(int i = 0 ;i<data.getSGLXDicts().size();i++)
            {

                data.getSGLXDicts().get(i).setName("SGLX");
                data.getSGLXDicts().get(i).saveOrUpdateAsync("KeyID=?",data.getSGLXDicts().get(i).getKeyID())
                .listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(!success)
                        {
                            showErr("?????????????????????SGLX");
                        }
                    }
                });
            }
            for(int i = 0 ;i<data.getSGHGDicts().size();i++)
            {
                data.getSGHGDicts().get(i).setName("SGHG");
                data.getSGHGDicts().get(i).saveOrUpdateAsync("KeyID=?",data.getSGHGDicts().get(i).getKeyID())
                .listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(!success)
                        {
                            showErr("?????????????????????SGHG");
                        }
                    }
                });
            }

            for(int i = 0 ;i<data.getYXFWDicts().size();i++)
            {
                data.getYXFWDicts().get(i).setName("YXFW");
                data.getYXFWDicts().get(i).saveOrUpdateAsync("KeyID=?",data.getYXFWDicts().get(i).getKeyID())
                .listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(!success)
                        {
                            showErr("?????????????????????YXFW");
                        }
                    }
                });
            }

            for(int i = 0 ;i<data.getDangerLevels().size();i++)
            {
                data.getDangerLevels().get(i).saveOrUpdateAsync("KeyID=?",data.getDangerLevels().get(i).getKeyID())
                .listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(!success)
                        {
                            showErr("?????????????????????DangerLevels");
                        }
                    }
                });
            }


            for(int i = 0 ;i<data.getEvaluateMethod().size();i++)
            {
                data.getEvaluateMethod().get(i).saveOrUpdateAsync("Value=?",data.getEvaluateMethod().get(i).getValue()+"")
                .listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(!success)
                        {
                            showErr("?????????????????????EvaluateMethod");
                        }
                    }
                });
            }

            for(int i = 0 ;i<data.getTroubleLevels().size();i++)
            {
                data.getTroubleLevels().get(i).setName("TroubleLevel");
                data.getTroubleLevels().get(i).saveOrUpdateAsync("KeyID=?",data.getTroubleLevels().get(i).getKeyID())
                .listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(!success)
                        {
                            showErr("?????????????????????TroubleLevel");
                        }
                    }
                });
            }

            for(int i = 0 ;i<data.getEmps().size();i++)
            {   Logger.d("-------------->" + "name:" + data.getEmps().get(i).getCNName() + data.getEmps().get(i).getOrgID());
                data.getEmps().get(i).saveOrUpdateAsync("KeyID=?",data.getEmps().get(i).getKeyID()).listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(!success)
                        {
                            showErr("?????????????????????Emps");
                        }
                    }
                });
            }

            for(int i = 0 ;i<data.getOrgs().size();i++)
            {   Logger.d("-------------->" + "orgskey:" + data.getOrgs().get(i).getOrgName() + data.getOrgs().get(i).getKeyID());
                Logger.d("-------------->" + "orgsparent:" + data.getOrgs().get(i).getOrgName() + data.getOrgs().get(i).getParentID());
                data.getOrgs().get(i).saveOrUpdateAsync("KeyID=?",data.getOrgs().get(i).getKeyID())
                .listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if(!success)
                        {
                            showErr("?????????????????????Orgs");
                        }
                    }
                });
            }
        }


        onComplete();
    }

    @Override
    public void getEmpTaskByQRCoderSuc(GetEmpTaskByQRCoderModel.Response response) {
        if (response.getData().size() > 0)//?????????  ?????????????????????
        {
            startActivity(new Intent(mContext, TaskScanningActivity.class).putExtra("pointID", result));
        } else {//??????????????????????????????????????????
            startActivity(new Intent(mContext, WindControlScanningActivity.class).putExtra("pointID", result));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            //??????????????????????????????????????????
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    result = bundle.getString(CodeUtils.RESULT_STRING);
                    Logger.d(result);
//                    fragments.get(showPosition).reserve(result);
                    GetEmpTaskByQRCoderModel.Request request = new GetEmpTaskByQRCoderModel.Request();
                    request.setDangerPointID(result);
                    mPresenter.getEmpTaskByQRCoder(request);
//                    Toast.makeText(this, "????????????:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    showToast("?????????????????????");
                }
            }
        }
    }

    /**
     * ????????????
     */
    private void getPermissions() {
        rxPermissions.requestEachCombined(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        )
                .subscribe(permission -> { // will emit 1 Permission object
                    if (permission.granted) {
                        Intent intent = new Intent(mContext, CaptureActivity.class);
                        startActivityForResult(intent, REQUEST_CODE);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        //?????????????????????????????????
                        showToast("?????????????????????");
                    } else {
                        //?????????????????????????????????????????????????????????
                        showToast("???????????????????????????????????????");
                    }
                });
    }
}
