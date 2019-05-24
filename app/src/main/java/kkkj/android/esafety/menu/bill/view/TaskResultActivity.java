package kkkj.android.esafety.menu.bill.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.tencent.smtt.sdk.TbsVideo;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.AttachFileNew;
import kkkj.android.esafety.bean.*;
import kkkj.android.esafety.common.pickperson.PickPersonActivity;
import kkkj.android.esafety.common.getpic.GetPicModel;
import kkkj.android.esafety.common.getpic.GetPicOrMP4Activity;
import kkkj.android.esafety.customer.MyOptionsPicker;
import kkkj.android.esafety.common.getpic.PhotoViewActivity;
import kkkj.android.esafety.http.ApiConfig;
import kkkj.android.esafety.menu.bill.adapter.PicOrMp4Adapter;
import kkkj.android.esafety.menu.bill.contract.TaskResultContract;
import kkkj.android.esafety.menu.bill.model.AddTaskSubjectModel;
import kkkj.android.esafety.menu.bill.model.GetSubResultModel;
import kkkj.android.esafety.menu.bill.presenter.TaskResultPresenter;
import kkkj.android.esafety.model.DownLoadFileModel;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.utils.SoftHideKeyBoardUtil;

public class TaskResultActivity extends MvpBaseActivity<TaskResultPresenter> implements TaskResultContract.View, View.OnClickListener {
    @BindView(R.id.tv_reason)
    TextView tv_reason;

    @BindView(R.id.tv_type)
    TextView tv_type;

    @BindView(R.id.tv_consequence)
    TextView tv_consequence;

    @BindView(R.id.tv_range)
    TextView tv_range;

    @BindView(R.id.tv_evalFunc)
    TextView tv_evalFunc;

    @BindView(R.id.tv_dangerLevel)
    TextView tv_dangerLevel;

    @BindView(R.id.tv_hiddenDanger)
    TextView tv_hiddenDanger;

    @BindView(R.id.tv_Principal)
    TextView tv_Principal;

    @BindView(R.id.tv_evalLECD_C)
    TextView tv_evalLECD_C;
    @BindView(R.id.tv_evalLECD_L)
    TextView tv_evalLECD_L;
    @BindView(R.id.tv_evalLECD_E)
    TextView tv_evalLECD_E;
    @BindView(R.id.tv_evalLSD_S)
    TextView tv_evalLSD_S;
    @BindView(R.id.tv_evalLSD_C)
    TextView tv_evalLSD_C;


    @BindView(R.id.tv_danger)
    TextView tv_danger;
    @BindView(R.id.ed_describe)
    EditText ed_describe;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_addFile)
    TextView tv_addFile;


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.ll_result)
    LinearLayout ll_result;

    @BindView(R.id.ll_evalLECD)
    LinearLayout ll_evalLECD;
    @BindView(R.id.ll_evalLCD)
    LinearLayout ll_evalLCD;


    List<GetPicModel> mList;
    PicOrMp4Adapter picOrMp4Adapter;
    String recordId;
    String subresultid;
    List<AttachFileNew> attachFileNewList;
    int result = 1;
    int subjectType;
    String billID = "";
    String subjectID = "";
    String DangerID = "";
    OptionsPickerView DangersPicker;
    List<String> Dangers;
    boolean IsControl = false;
    OptionsPickerView WXYSPicker, SGLXPicker, SGHGPicker, YXFWPicker, DangerLevelsPicker, EvaluateMethodPicker, TroubleLevelsPicker;

    OptionsPickerView LECD_LsPicker, LECD_EsPicker, LECD_CsPicker, LSD_LsPicker, LSD_SsPicker;

    List<WHYSDict> WHYSDicts = new ArrayList<>();
    List<Dict> SGLXDicts = new ArrayList<>();
    List<Dict> SGHGDicts = new ArrayList<>();
    List<Dict> YXFWDicts = new ArrayList<>();
    List<DangerLevelDict> DangerLevels = new ArrayList<>();
    DangerLevelDict cDangerLevel;
    List<EnumItem> EvaluateMethod = new ArrayList<>();
    List<Dict> TroubleLevels = new ArrayList<>();

    List<Eval_Dict> LECD_Ls = new ArrayList<>();
    List<Eval_Dict> LECD_Es = new ArrayList<>();
    List<Eval_Dict> LECD_Cs = new ArrayList<>();
    List<Eval_Dict> LSD_Ls = new ArrayList<>();
    List<Eval_Dict> LSD_Ss = new ArrayList<>();
    Eval_Dict cLECD_Ls, cLECD_Es, cLECD_Cs, cLSD_Ls, cLSD_Ss;

    AddTaskSubjectModel.Request requestNew;
    int PickRequest = 102;

    @Override
    protected int getLayout() {
        return R.layout.activity_mytask_result2;
    }

    @Override
    protected TaskResultPresenter getPresenter() {
        return new TaskResultPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        SoftHideKeyBoardUtil.assistActivity(mActivity);
        action_bar_title.setText("任务检查结果");
        action_bar_right.setText("保存");
        action_bar_right.setOnClickListener(this);
        tv_addFile.setOnClickListener(this);
        subresultid = getIntent().getStringExtra("subresultid");
        subjectType = getIntent().getIntExtra("subjectType", 0);
        billID = getIntent().getStringExtra("billID");
        subjectID = getIntent().getStringExtra("subjectID");
        DangerID = getIntent().getStringExtra("DangerID");
        requestNew = new AddTaskSubjectModel.Request();
        IsControl = getIntent().getBooleanExtra("IsControl", false);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        tv_time.setEnabled(false);
        tv_time.setText(df.format(new Date()));
        Dangers = new ArrayList<>();
        Dangers.add("正常");
        Dangers.add("异常");
        Dangers.add("管控中");
        Dangers.add("处理后正常");
        MyOptionsPicker picker = new MyOptionsPicker();
        //初始化风险选择
        DangersPicker = picker.getPicker(mContext, "风险点选择", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_danger.setText(Dangers.get(options1));
                if (Dangers.get(options1).equals("异常")) {
                    initPickers();
                    ll_result.setVisibility(View.VISIBLE);

                } else {
                    ll_result.setVisibility(View.GONE);
                }
                options1++;
                result = options1;
            }
        });
        DangersPicker.setPicker(Dangers, null, null);

        if (IsControl) {
            tv_danger.setEnabled(false);
            DangersPicker.setSelectOptions(2);
        } else {
            tv_danger.setEnabled(true);
        }
        tv_danger.setOnClickListener(this);
        tv_reason.setOnClickListener(this);
        tv_type.setOnClickListener(this);
        tv_consequence.setOnClickListener(this);
        tv_range.setOnClickListener(this);
        tv_evalFunc.setOnClickListener(this);
        tv_dangerLevel.setOnClickListener(this);
        tv_hiddenDanger.setOnClickListener(this);
        tv_Principal.setOnClickListener(this);

        tv_evalLECD_C.setOnClickListener(this);
        tv_evalLECD_L.setOnClickListener(this);
        tv_evalLECD_E.setOnClickListener(this);
        tv_evalLSD_S.setOnClickListener(this);
        tv_evalLSD_C.setOnClickListener(this);

        mList = new ArrayList<>();


        picOrMp4Adapter = new PicOrMp4Adapter(R.layout.item_picormp4, mList);
        picOrMp4Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mList = picOrMp4Adapter.getData();
                switch (view.getId()) {
                    case R.id.img:
                    case R.id.mp4:
                        Logger.d("图片路径:" + mList.get(position).getImagePath());
                        Logger.d("MP4路径:" + mList.get(position).getMp4Path());
                        if (mList.get(position).getType() == 0) {
                            startActivityForResult(new Intent(mContext, PhotoViewActivity.class).putExtra("picUrl", mList.get(position).getImagePath()), 200);
                        } else {
                            //用腾讯TBS播放视频
                            //判断当前是否可用
                            if (TbsVideo.canUseTbsPlayer(getApplicationContext())) {
                                //播放视频
                                TbsVideo.openVideo(getApplicationContext(), mList.get(position).getMp4Path());
                            } else {
                                showToast("TBS视频播放器异常");
                            }
                        }
                        break;
                    case R.id.iv_delete:
                        mList.remove(position);
                        picOrMp4Adapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        recyclerview.setAdapter(picOrMp4Adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        WHYSDicts = LitePal.where("BillID = ? ", billID).find(WHYSDict.class);
        SGLXDicts = LitePal.where("Name = ?", "SGLX").find(Dict.class);
        SGHGDicts = LitePal.where("Name = ?", "SGHG").find(Dict.class);
        YXFWDicts = LitePal.where("Name = ?", "YXFW").find(Dict.class);
        TroubleLevels = LitePal.where("Name = ?", "TroubleLevel").find(Dict.class);
        DangerLevels = LitePal.findAll(DangerLevelDict.class);
        EvaluateMethod = LitePal.findAll(EnumItem.class);

        LECD_Ls = LitePal.where("Name = ? ", "LECD_Ls").find(Eval_Dict.class);
        LECD_Es = LitePal.where("Name = ? ", "LECD_Es").find(Eval_Dict.class);
        LECD_Cs = LitePal.where("Name = ? ", "LECD_Cs").find(Eval_Dict.class);
        LSD_Ls = LitePal.where("Name = ? ", "LSD_Ls").find(Eval_Dict.class);
        LSD_Ss = LitePal.where("Name = ? ", "LSD_Ss").find(Eval_Dict.class);


        List<Subject> subjects = LitePal.where("SubjectID=? and BillID = ? and DangerID=?", subjectID, billID, DangerID).find(Subject.class);
        if (subjects != null) {
            for (int i = 0; subjects.size() > i; i++) {
                tv_time.setText(subjects.get(i).getTime() + "");
                ed_describe.setText(subjects.get(i).getTaskResultMemo() + "");
//                spinner.setSelection(subjects.get(i).getTaskResult()+0);
                tv_danger.setText(Dangers.get(subjects.get(i).getTaskResult() - 1) + "");
                result = subjects.get(i).getTaskResult();
                if (result == 2) {
                    initPickers();
                    ll_result.setVisibility(View.VISIBLE);

                    List<Dict> SGLXs = LitePal.where("KeyID=?", subjects.get(i).getEval_SGLX()).find(Dict.class);

                    List<WHYSDict> WHYSs = LitePal.where("BillID = ? and KeyID=?", billID, subjects.get(i).getEval_WHYS()).find(WHYSDict.class);

                    List<Dict> SGHGs = LitePal.where("KeyID=?", subjects.get(i).getEval_SGJG()).find(Dict.class);

                    List<Dict> YXFWs = LitePal.where("KeyID=?", subjects.get(i).getEval_YXFW()).find(Dict.class);

                    List<DangerLevelDict> Dangers = LitePal.where("KeyID=?", subjects.get(i).getDangerLevel()).find(DangerLevelDict.class);

                    List<EnumItem> EvaMethods = LitePal.where("Value=?", subjects.get(i).getEval_Method() + "").find(EnumItem.class);

                    List<Dict> Troubles = LitePal.where("KeyID=?", subjects.get(i).getTroubleLevel() + "").find(Dict.class);

                    List<Emp> person = LitePal.where("KeyID=?", subjects.get(i).getPrincipalID()).find(Emp.class);

                    List<Eval_Dict> LECD_Ls = LitePal.where("KeyID=?", subjects.get(i).getLECD_L()).find(Eval_Dict.class);
                    List<Eval_Dict> LECD_Es = LitePal.where("KeyID=?", subjects.get(i).getLECD_E()).find(Eval_Dict.class);
                    List<Eval_Dict> LECD_Cs = LitePal.where("KeyID=?", subjects.get(i).getLECD_C()).find(Eval_Dict.class);

                    List<Eval_Dict> LSDs = LitePal.where("KeyID=?", subjects.get(i).getLSD_L()).find(Eval_Dict.class);
                    List<Eval_Dict> LSDc = LitePal.where("KeyID=?", subjects.get(i).getLSD_S()).find(Eval_Dict.class);

                    if (SGLXs.size() > 0) {
                        tv_reason.setText(SGLXs.get(0).getDictName());
                    }

                    if (WHYSs.size() > 0) {
                        tv_type.setText(WHYSs.get(i).getDictName());

                    }

                    if (SGHGs.size() > 0) {
                        tv_consequence.setText(SGHGs.get(0).getDictName());

                    }

                    if (YXFWs.size() > 0) {
                        tv_range.setText(YXFWs.get(0).getDictName());
                    }

                    if (EvaMethods.size() > 0) {
                        tv_evalFunc.setText(EvaMethods.get(0).getCaption());
                        if (EvaMethods.get(0).getValue() == 2) {
                            ll_evalLECD.setVisibility(View.VISIBLE);
                            ll_evalLCD.setVisibility(View.GONE);
                            if (LECD_Ls.size() > 0) {
                                tv_evalLECD_L.setText(LECD_Ls.get(0).getDictName());
                            }
                            if (LECD_Es.size() > 0) {
                                tv_evalLECD_E.setText(LECD_Es.get(0).getDictName());
                            }
                            if (LECD_Cs.size() > 0) {
                                tv_evalLECD_C.setText(LECD_Cs.get(0).getDictName());
                            }
                        } else if (EvaMethods.get(0).getValue() == 3) {
                            ll_evalLCD.setVisibility(View.VISIBLE);
                            ll_evalLECD.setVisibility(View.GONE);

                            if (LSDs.size() > 0) {
                                tv_evalLSD_S.setText(LSDs.get(0).getDictName());
                            }

                            if (LSDc.size() > 0) {
                                tv_evalLSD_C.setText(LSDc.get(0).getDictName());
                            }
                        }
                    }

                    if (Dangers.size() > 0) {
                        tv_dangerLevel.setText(Dangers.get(0).getDictName());

                    }

                    if (Troubles.size() > 0) {
                        tv_hiddenDanger.setText(Troubles.get(0).getDictName());
                    }

                    if (person.size() > 0) {
                        tv_Principal.setText(person.get(0).getCNName());
                    }

                    requestNew.setEval_WHYS(subjects.get(i).getEval_WHYS());

                    requestNew.setEval_SGLX(subjects.get(i).getEval_SGLX());

                    requestNew.setEval_SGJG(subjects.get(i).getEval_SGJG());

                    requestNew.setEval_YXFW(subjects.get(i).getEval_YXFW());

                    requestNew.setEval_Method(subjects.get(i).getEval_Method());

                    requestNew.setDangerLevel(subjects.get(i).getDangerLevel());

                    requestNew.setTroubleLevel(subjects.get(i).getTroubleLevel());

                    requestNew.setPrincipalID(subjects.get(i).getPrincipalID());

                    requestNew.setLECD_L(subjects.get(i).getLECD_L());
                    requestNew.setLECD_E(subjects.get(i).getLECD_E());
                    requestNew.setLECD_C(subjects.get(i).getLECD_C());
                    requestNew.setLSD_S(subjects.get(i).getLSD_S());
                    requestNew.setLSD_L(subjects.get(i).getLSD_L());
                    requestNew.setDValue(subjects.get(i).getDValue());

                }

                if (subjects.get(i).getAttachFiles() != null) {
                    for (int j = 0; j < subjects.get(i).getAttachFiles().size(); j++) {
                        GetPicModel picOrMp4 = new GetPicModel();
                        if (subjects.get(i).getAttachFiles().get(j).getFileType().equals("jpg")) {
                            picOrMp4.setType(0);
                            picOrMp4.setImagePath(subjects.get(i).getAttachFiles().get(j).getFileUrl().replace("~", ApiConfig.BASE_URL));
                        } else {
                            picOrMp4.setType(1);
                            picOrMp4.setImagePath("default");
                            picOrMp4.setMp4Path(subjects.get(i).getAttachFiles().get(j).getFileUrl().replace("~", ApiConfig.BASE_URL));
                        }
                        picOrMp4.setContent(subjects.get(i).getAttachFiles().get(j).getFileTitle());
                        picOrMp4.setIsUpload(1);
                        mList.add(picOrMp4);
                    }
                }

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(subresultid)) {
            GetSubResultModel.Request request = new GetSubResultModel.Request();
            request.setSubresultid(subresultid);
            mPresenter.getsubresult(request);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_evalLECD_C:
                LECD_CsPicker.show();
                break;
            case R.id.tv_evalLECD_L:
                LECD_LsPicker.show();
                break;
            case R.id.tv_evalLECD_E:
                LECD_EsPicker.show();
                break;
            case R.id.tv_evalLSD_S:
                LSD_LsPicker.show();
                break;
            case R.id.tv_evalLSD_C:
                LSD_SsPicker.show();
                break;
            case R.id.tv_Principal:
                startActivityForResult(new Intent(mContext, PickPersonActivity.class), PickRequest);
                break;
            case R.id.tv_danger:

                DangersPicker.show();
                break;
            case R.id.tv_addFile:
                getPermissions();
                break;
            case R.id.action_bar_right:
                if (result == 2) {
                    if (TextUtils.isEmpty(requestNew.getEval_WHYS())) {
                        showToast("请选择危害因素");
                        return;
                    }

                    if (TextUtils.isEmpty(requestNew.getEval_SGLX())) {
                        showToast("请选择事故类型");
                        return;
                    }

                    if (TextUtils.isEmpty(requestNew.getEval_SGJG())) {
                        showToast("请选择事故后果");
                        return;
                    }

                    if (TextUtils.isEmpty(requestNew.getEval_YXFW())) {
                        showToast("请选择影响范围");
                        return;
                    }

                    if (requestNew.getEval_Method() == -1) {
                        showToast("请选择评测方法");
                        return;
                    }

                    if (TextUtils.isEmpty(requestNew.getDangerLevel())) {
                        showToast("请选择风险等级");
                        return;
                    }

                    if (TextUtils.isEmpty(requestNew.getTroubleLevel())) {
                        showToast("请选择隐患等级");
                        return;
                    }
                    if (TextUtils.isEmpty(requestNew.getPrincipalID())) {
                        showToast("请选择负责人");
                        return;
                    }
                }
//                if (!NetUtils.checkNetWork()) {
//                    mList = picOrMp4Adapter.getData();
//                    for (int i = 0; i < mList.size(); i++) {
//                        mList.get(i).saveOrUpdate("imagePath=?", mList.get(i).getImagePath());
//                    }
//                    showToast("暂无网络，将数据保存到本地");
//                    finish();
//                } else {
                if (TextUtils.isEmpty(ed_describe.getText().toString().trim())) {
                    showToast("请输入检查结果描述");
                    return;
                }
                mList = picOrMp4Adapter.getData();
                for (int n = 0; n < mList.size(); n++) {
                    if (TextUtils.isEmpty(mList.get(n).getContent())) {
                        showToast("请填写所有附件描述后再提交");
                        return;
                    }
                }
                attachFileNewList = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    AttachFileNew attachFileNew = new AttachFileNew();
                    attachFileNew.setFileTitle(mList.get(i).getContent());
                    if (mList.get(i).getType() == 0) {
                        attachFileNew.setFileUrl(mList.get(i).getImagePath());
                        attachFileNew.setFileType("jpg");
                    } else {
                        attachFileNew.setFileUrl(mList.get(i).getMp4Path());
                        attachFileNew.setFileType("mp4");
                    }
                    attachFileNew.setBillID(billID);
                    attachFileNew.setDanger(DangerID);
                    attachFileNew.setSubjectID(subjectID);
                    attachFileNewList.add(attachFileNew);
                }
                addTaskSub();
//                }
                break;
            case R.id.tv_reason:
                if (WHYSDicts.size() > 0) {
                    WXYSPicker.show();
                }

                break;
            case R.id.tv_type:
                if (SGLXDicts.size() > 0) {
                    SGLXPicker.show();
                }
                break;
            case R.id.tv_consequence:

                if (SGHGDicts.size() > 0) {
                    SGHGPicker.show();
                }
                break;
            case R.id.tv_range:

                if (YXFWDicts.size() > 0) {
                    YXFWPicker.show();
                }
                break;
            case R.id.tv_evalFunc:
                if (EvaluateMethod.size() > 0) {
                    EvaluateMethodPicker.show();
                }
                break;
            case R.id.tv_dangerLevel:

                if (DangerLevels.size() > 0) {
                    DangerLevelsPicker.show();
                }
                break;
            case R.id.tv_hiddenDanger:
                if (TroubleLevels.size() > 0) {
                    TroubleLevelsPicker.show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 200) {
            if (resultCode == Activity.RESULT_OK) {
                mList = picOrMp4Adapter.getData();
                GetPicModel picOrMp4 = new GetPicModel();
                picOrMp4 = (GetPicModel) data.getSerializableExtra("result");
                picOrMp4.setRecordId(recordId);
                picOrMp4.setIsUpload(1);
                picOrMp4.setSubjectID(subjectID);
                mList.add(picOrMp4);
                picOrMp4Adapter.notifyDataSetChanged();
            }
        } else if (requestCode == PickRequest) {
            if (resultCode == Activity.RESULT_OK) {
                String id = data.getStringExtra("PID");
                String name = data.getStringExtra("PName");
                requestNew.setPrincipalID(id);
                tv_Principal.setText(name);
            }
        }
    }

    @Override
    public void getfilesSuc(List<Bll_AttachFile> data) {
        mList.clear();
        for (int i = 0; i < data.size(); i++) {
            GetPicModel picOrMp4 = new GetPicModel();
            if (data.get(i).getFileType().equals("jpg")) {
                picOrMp4.setType(0);
                picOrMp4.setImagePath(data.get(i).getFileUrl().replace("~", ApiConfig.BASE_URL));
            } else {
                picOrMp4.setType(1);
                picOrMp4.setImagePath("default");
                picOrMp4.setMp4Path(data.get(i).getFileUrl().replace("~", ApiConfig.BASE_URL));
            }
            picOrMp4.setContent(data.get(i).getFileTitle());
            picOrMp4.setIsDwon(1);
            mList.add(picOrMp4);
        }
        picOrMp4Adapter.notifyDataSetChanged();
    }

    @Override
    public void addtasksubjectSuc() {
        showToast("新建任务主体检查结果成功");
        hideLoading();
        finish();
    }

    public void addTaskSub() {
        requestNew.setAttachFiles(attachFileNewList);
        requestNew.setTaskResult(result);
        requestNew.setTime(tv_time.getText().toString().trim());
        requestNew.setTaskResultMemo(ed_describe.getText().toString().trim() + "");
        if (subjectType != 0) {
            requestNew.setSubjectType(subjectType);
        } else {
            showToast("获取主体类型失败");
            return;
        }
        if (!TextUtils.isEmpty(billID)) {
            requestNew.setBillID(billID);
        } else {
            showToast("获取任务单据ID失败");
            return;
        }
        if (!TextUtils.isEmpty(subjectID)) {
            requestNew.setSubjectID(subjectID);
        } else {
            showToast("获取主体ID失败");
            return;
        }
        if (!TextUtils.isEmpty(DangerID)) {
            Logger.d("DangerID:" + DangerID);
            requestNew.setDangerID(DangerID);
        } else {
            showToast("获取风控项失败");
            return;
        }

        mPresenter.addtasksubject(requestNew);
    }

    @Override
    public void getsubresultSuc(SubResultView data) {
        if (!TextUtils.isEmpty(data.getResultTime())) {
            tv_time.setText(data.getResultTime());
            tv_time.setEnabled(false);
            ed_describe.setText(data.getTaskResultMemo());
            ed_describe.setEnabled(false);
            tv_danger.setText(Dangers.get(data.getTaskResult() - 1));
            tv_danger.setEnabled(false);
            if (data.getTaskResult() == 2) {
                tv_reason.setEnabled(false);
                tv_type.setEnabled(false);
                tv_consequence.setEnabled(false);
                tv_range.setEnabled(false);
                tv_evalFunc.setEnabled(false);
                tv_dangerLevel.setEnabled(false);
                tv_hiddenDanger.setEnabled(false);
                tv_Principal.setEnabled(false);
                tv_evalLECD_C.setEnabled(false);
                tv_evalLECD_L.setEnabled(false);
                tv_evalLECD_E.setEnabled(false);
                tv_evalLSD_S.setEnabled(false);
                tv_evalLSD_C.setEnabled(false);

                ll_result.setVisibility(View.VISIBLE);
                tv_reason.setText(data.getSGLXDict());
                tv_type.setText(data.getWHYSDict());
                tv_consequence.setText(data.getSGJGDict());
                tv_range.setText(data.getYXFWDict());
                tv_evalFunc.setText(data.getMethod());
                if (data.getMethod().equals("LECD法")) {
                    ll_evalLECD.setVisibility(View.VISIBLE);
                    ll_evalLCD.setVisibility(View.GONE);
                    tv_evalLECD_L.setText(data.getLECD_L());
                    tv_evalLECD_E.setText(data.getLECD_E());
                    tv_evalLECD_C.setText(data.getLECD_C());
                } else if (data.getMethod().equals("LSD法")) {
                    ll_evalLCD.setVisibility(View.VISIBLE);
                    ll_evalLECD.setVisibility(View.GONE);
                    tv_evalLSD_S.setText(data.getLSD_L());
                    tv_evalLSD_C.setText(data.getLSD_S());
                }
                tv_dangerLevel.setText(data.getDLevel());
                tv_hiddenDanger.setText(data.getTLevel());
                tv_Principal.setText(data.getCtrPrincipal());
            }

            action_bar_right.setVisibility(View.INVISIBLE);
            tv_addFile.setEnabled(false);
            DownLoadFileModel.Request request = new DownLoadFileModel.Request();
            request.setBusinessid(subresultid);
            mPresenter.getfiles(request);
        } else {

        }
    }

    /**
     * 获取权限
     */
    private void getPermissions() {
        rxPermissions
                .requestEachCombined(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO)
                .subscribe(permission -> { // will emit 1 Permission object
                    if (permission.granted) {
                        startActivityForResult(new Intent(mContext, GetPicOrMP4Activity.class), 200);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        //有至少一个权限没有同意
                        showToast("请同意全部权限");
                    } else {
                        //有至少一个权限没有同意且勾选了不在提示
                        showToast("请在权限管理中打开相关权限");
                    }
                });
    }

    private void initPickers() {
        MyOptionsPicker picker = new MyOptionsPicker();
        //初始化危险因素选择
        WXYSPicker = picker.getPicker(mContext, "危险因素选择", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_reason.setText(WHYSDicts.get(options1).getDictName());
                requestNew.setEval_WHYS(WHYSDicts.get(options1).getKeyID());

                tv_reason.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showLongMessageDialog(WHYSDicts.get(options1).getMemo());
                        Logger.d(WHYSDicts.get(options1).getMemo()+"~~~~~~~~~~~~~~~~~~");
                        return false;
                    }
                });
            }
        });
        WXYSPicker.setPicker(WHYSDicts, null, null);

        //初始化事故类型选择
        SGLXPicker = picker.getPicker(mContext, "事故类型选择", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_type.setText(SGLXDicts.get(options1).getDictName());
                requestNew.setEval_SGLX(SGLXDicts.get(options1).getKeyID());

                tv_type.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showLongMessageDialog(SGLXDicts.get(options1).getMemo());
                        return false;
                    }
                });
            }
        });
        SGLXPicker.setPicker(SGLXDicts, null, null);

        //初始化事故后果选择
        SGHGPicker = picker.getPicker(mContext, "事故后果选择", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_consequence.setText(SGHGDicts.get(options1).getDictName());
                requestNew.setEval_SGJG(SGHGDicts.get(options1).getKeyID());

                tv_consequence.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showLongMessageDialog(SGHGDicts.get(options1).getMemo());
                        return false;
                    }
                });
            }
        });
        SGHGPicker.setPicker(SGHGDicts, null, null);

        //初始化影响范围选择
        YXFWPicker = picker.getPicker(mContext, "影响范围选择", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_range.setText(YXFWDicts.get(options1).getDictName());
                requestNew.setEval_YXFW(YXFWDicts.get(options1).getKeyID());

                tv_range.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showLongMessageDialog(YXFWDicts.get(options1).getMemo());
                        return false;
                    }
                });
            }
        });
        YXFWPicker.setPicker(YXFWDicts, null, null);

        //初始化评测方法选择
        EvaluateMethodPicker = picker.getPicker(mContext, "评测方法选择", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_evalFunc.setText(EvaluateMethod.get(options1).getCaption());
                requestNew.setEval_Method(EvaluateMethod.get(options1).getValue());
                //选择  LECD LSD或者自选
                if (EvaluateMethod.get(options1).getValue() == 2)//LECD
                {
                    ll_evalLECD.setVisibility(View.VISIBLE);
                    ll_evalLCD.setVisibility(View.GONE);
                } else if (EvaluateMethod.get(options1).getValue() == 3)//LSD
                {
                    ll_evalLCD.setVisibility(View.VISIBLE);
                    ll_evalLECD.setVisibility(View.GONE);
                } else {
                    ll_evalLCD.setVisibility(View.GONE);
                    ll_evalLECD.setVisibility(View.GONE);
                }
            }
        });
        EvaluateMethodPicker.setPicker(EvaluateMethod, null, null);


        //初始化风险等级选择
        DangerLevelsPicker = picker.getPicker(mContext, "风险等级选择", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_dangerLevel.setText(DangerLevels.get(options1).getDictName());
                requestNew.setDangerLevel(DangerLevels.get(options1).getKeyID());
                cDangerLevel = DangerLevels.get(options1);
                requestNew.setDValue(0);

                tv_dangerLevel.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showLongMessageDialog(DangerLevels.get(options1).getMemo());
                        return false;
                    }
                });
            }
        });
        DangerLevelsPicker.setPicker(DangerLevels, null, null);


        //初始化隐患等级选择
        TroubleLevelsPicker = picker.getPicker(mContext, "隐患等级选择", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_hiddenDanger.setText(TroubleLevels.get(options1).getDictName());
                requestNew.setTroubleLevel(TroubleLevels.get(options1).getKeyID());

                tv_hiddenDanger.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showLongMessageDialog(TroubleLevels.get(options1).getMemo());
                        return false;
                    }
                });
            }
        });
        TroubleLevelsPicker.setPicker(TroubleLevels, null, null);

        //用于LECD方法计算的选项，L
        LECD_LsPicker = picker.getPicker(mContext, "LECD_L", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_evalLECD_L.setText(LECD_Ls.get(options1).getDictName());
                requestNew.setLECD_L(LECD_Ls.get(options1).getKeyID());
                cLECD_Ls = LECD_Ls.get(options1);
                calDangerLevel();

                tv_evalLECD_L.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showLongMessageDialog(LECD_Ls.get(options1).getMemo());
                        return false;
                    }
                });
            }
        });
        LECD_LsPicker.setPicker(LECD_Ls, null, null);

        //用于LECD方法计算的选项，E
        LECD_EsPicker = picker.getPicker(mContext, "LECD_E", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_evalLECD_E.setText(LECD_Es.get(options1).getDictName());
                requestNew.setLECD_E(LECD_Es.get(options1).getKeyID());
                cLECD_Es = LECD_Es.get(options1);
                calDangerLevel();

                tv_evalLECD_E.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showLongMessageDialog(LECD_Es.get(options1).getMemo());
                        return false;
                    }
                });
            }
        });
        LECD_EsPicker.setPicker(LECD_Es, null, null);

        //用于LECD方法计算的选项，C
        LECD_CsPicker = picker.getPicker(mContext, "LECD_E", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_evalLECD_C.setText(LECD_Cs.get(options1).getDictName());
                requestNew.setLECD_C(LECD_Cs.get(options1).getKeyID());
                cLECD_Cs = LECD_Cs.get(options1);
                calDangerLevel();

                tv_evalLECD_C.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showLongMessageDialog(LECD_Cs.get(options1).getMemo());
                        return false;
                    }
                });
            }
        });
        LECD_CsPicker.setPicker(LECD_Cs, null, null);


        //用于LSD方法计算的选项，S
        LSD_LsPicker = picker.getPicker(mContext, "LSD_S", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_evalLSD_S.setText(LSD_Ls.get(options1).getDictName());
                requestNew.setLSD_L(LSD_Ls.get(options1).getKeyID());
                cLSD_Ls = LSD_Ls.get(options1);
                calDangerLevel();

                tv_evalLSD_S.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showLongMessageDialog(LSD_Ls.get(options1).getMemo());
                        return false;
                    }
                });
            }
        });
        LSD_LsPicker.setPicker(LSD_Ls, null, null);


        //用于LSD方法计算的选项，C
        LSD_SsPicker = picker.getPicker(mContext, "LSD_C", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_evalLSD_C.setText(LSD_Ss.get(options1).getDictName());
                requestNew.setLSD_S(LSD_Ss.get(options1).getKeyID());
                cLSD_Ss = LSD_Ss.get(options1);
                calDangerLevel();

                tv_evalLSD_C.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showLongMessageDialog(LSD_Ss.get(options1).getMemo());
                        return false;
                    }
                });
            }
        });
        LSD_SsPicker.setPicker(LSD_Ss, null, null);
    }

    public void calDangerLevel() {
        MyOptionsPicker picker = new MyOptionsPicker();
        if (requestNew.getEval_Method() == 2)//LECD
        {
            if (cLECD_Ls == null || cLECD_Es == null || cLECD_Cs == null) {
                return;
            } else {
                double level = cLECD_Ls.getValue() * cLECD_Es.getValue() * cLECD_Cs.getValue();
                DangerLevelDict cDangerLevelDict = new DangerLevelDict();
                List<DangerLevelDict> dangerLevelDictList = LitePal.where("LECD_DMaxValue>=? and LECD_DMinValue<=?", level + "", level + "").find(DangerLevelDict.class);
                Logger.d(level + "~~~~~~~~~~~~~~~~~~~~~~~~~~~" + dangerLevelDictList.size());
                if (dangerLevelDictList.size() > 0) {
                    cDangerLevelDict = dangerLevelDictList.get(0);
                    tv_dangerLevel.setText(cDangerLevelDict.getDictName());
                    requestNew.setDangerLevel(cDangerLevelDict.getKeyID());
                    requestNew.setDValue(1);
                    cDangerLevel = cDangerLevelDict;
                    tv_dangerLevel.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            showLongMessageDialog(cDangerLevel.getMemo());
                            return false;
                        }
                    });
                }
                DangerLevels = LitePal.where("LECD_DMaxValue > ?"
                        , cDangerLevelDict.getLECD_DMaxValue() + "").find(DangerLevelDict.class);
                //初始化风险等级选择
                DangerLevelsPicker = picker.getPicker(mContext, "风险等级选择", new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        tv_dangerLevel.setText(DangerLevels.get(options1).getDictName());
                        requestNew.setDangerLevel(DangerLevels.get(options1).getKeyID());
                        cDangerLevel = DangerLevels.get(options1);
                        requestNew.setDValue(0);

                        tv_dangerLevel.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                showLongMessageDialog(DangerLevels.get(options1).getMemo());
                                return false;
                            }
                        });
                    }
                });
                DangerLevelsPicker.setPicker(DangerLevels, null, null);
            }
        } else if (requestNew.getEval_Method() == 3)//LSD
        {
            if (cLSD_Ls == null || cLSD_Ss == null) {
                return;
            } else {
                double level = cLSD_Ls.getValue() * cLSD_Ss.getValue();
                DangerLevelDict cDangerLevelDict = new DangerLevelDict();
                List<DangerLevelDict> dangerLevelDictList = LitePal.where("LSD_DMaxValue>=? and LSD_DMinValue<=?", level + "", level + "").find(DangerLevelDict.class);
                if (dangerLevelDictList.size() > 0) {
                    cDangerLevelDict = dangerLevelDictList.get(0);
                    tv_dangerLevel.setText(cDangerLevelDict.getDictName());
                    requestNew.setDangerLevel(cDangerLevelDict.getKeyID());
                    requestNew.setDValue(1);
                    cDangerLevel = cDangerLevelDict;


                    tv_dangerLevel.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            showLongMessageDialog(cDangerLevel.getMemo());
                            return false;
                        }
                    });
                }
                DangerLevels = LitePal.where("LSD_DMaxValue> ?",
                        cDangerLevelDict.getLECD_DMaxValue() + "").find(DangerLevelDict.class);
                //初始化风险等级选择
                DangerLevelsPicker = picker.getPicker(mContext, "风险等级选择", new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        tv_dangerLevel.setText(DangerLevels.get(options1).getDictName());
                        requestNew.setDangerLevel(DangerLevels.get(options1).getKeyID());
                        cDangerLevel = DangerLevels.get(options1);
                        requestNew.setDValue(0);

                        tv_dangerLevel.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                showLongMessageDialog(DangerLevels.get(options1).getMemo());
                                return false;
                            }
                        });
                    }
                });
                DangerLevelsPicker.setPicker(DangerLevels, null, null);
            }
        }
    }
    private void showLongMessageDialog(String msg) {
        if(TextUtils.isEmpty(msg))
        {
            return;
        }
        new QMUIDialog.MessageDialogBuilder(mActivity)
                .setTitle("说明")
                .setMessage(msg)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();
    }
}
