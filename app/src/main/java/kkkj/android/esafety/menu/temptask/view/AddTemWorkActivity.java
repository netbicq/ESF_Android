package kkkj.android.esafety.menu.temptask.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.smtt.sdk.TbsVideo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.AttachFileNew;
import kkkj.android.esafety.bean.CSub;
import kkkj.android.esafety.bean.InspectTaskSubjectNew;
import kkkj.android.esafety.bean.Sub;
import kkkj.android.esafety.bean.SubjectSelectModel;
import kkkj.android.esafety.bean.TempTaskSelector;
import kkkj.android.esafety.common.getpic.GetPicModel;
import kkkj.android.esafety.common.getpic.GetPicOrMP4Activity;
import kkkj.android.esafety.common.getpic.PhotoViewActivity;
import kkkj.android.esafety.common.pickdangerpoint.PickDangersActivity;
import kkkj.android.esafety.customer.MyOptionsPicker;
import kkkj.android.esafety.customer.MyTimePicker;
import kkkj.android.esafety.menu.bill.adapter.PicOrMp4Adapter;
import kkkj.android.esafety.menu.temptask.adapter.SubAdapter;
import kkkj.android.esafety.menu.temptask.contract.AddTemWorkContract;
import kkkj.android.esafety.menu.temptask.model.AddTempTaskModel;
import kkkj.android.esafety.menu.temptask.model.GetDangerSelectorModel;
import kkkj.android.esafety.menu.temptask.model.GetSelectorModel;
import kkkj.android.esafety.menu.temptask.presenter.AddTemWorkPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.utils.SoftHideKeyBoardUtil;

public class AddTemWorkActivity extends MvpBaseActivity<AddTemWorkPresenter> implements View.OnClickListener, AddTemWorkContract.View {
    @BindView(R.id.ll_danger)
    LinearLayout ll_danger;
    @BindView(R.id.ll_startt)
    LinearLayout ll_startt;
    @BindView(R.id.ll_endt)
    LinearLayout ll_endt;
    @BindView(R.id.ll_subject)
    LinearLayout ll_subject;
    @BindView(R.id.ll_post)
    LinearLayout ll_post;

//    @BindView(R.id.ll_subjectType)
//    LinearLayout ll_subjectType;

    @BindView(R.id.tv_danger)
    TextView tv_danger;
    @BindView(R.id.tv_startt)
    TextView tv_startt;
    @BindView(R.id.tv_endt)
    TextView tv_endt;
    @BindView(R.id.tv_post)
    TextView tv_post;


    @BindView(R.id.tv_subject)
    TextView tv_subject;

//    @BindView(R.id.tv_subjectType)
//    TextView tv_subjectType;

    @BindView(R.id.ed_content)
    EditText ed_content;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @BindView(R.id.tv_addFile)
    TextView tv_addFile;
    @BindView(R.id.recyclerview2)
    RecyclerView recyclerview2;
    List<GetPicModel> picOrMp4List;
    PicOrMp4Adapter picOrMp4Adapter;


    OptionsPickerView SubPicker, PostPicker, DangersPicker, SubTypesPicker;
    List<SubjectSelectModel> mList;
    List<CSub> CSubs = new ArrayList<>();
    List<TempTaskSelector.Danger> Dangers = new ArrayList<>();
    List<TempTaskSelector.SubType> SubTypes = new ArrayList<>();
    List<TempTaskSelector.Post> Posts = new ArrayList<>();

    List<ArrayList<String>> Subjects = new ArrayList<>();//??????
    List<ArrayList<ArrayList<TempTaskSelector.Sub.EntityType.Entity>>> Entities = new ArrayList<>();//????????????

    TimePickerView StartTimePicker, EndTimePicker;

    AddTempTaskModel.Request request;
    GetDangerSelectorModel.Request request2;
    SubAdapter adapter;
    List<AttachFileNew> attachFileNewList;

    @Override
    protected int getLayout() {
        return R.layout.activity_addtemwork;
    }

    @Override
    protected AddTemWorkPresenter getPresenter() {
        return new AddTemWorkPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        SoftHideKeyBoardUtil.assistActivity(mActivity);
        action_bar_title.setText("??????????????????");
        action_bar_right.setText("??????");
        ll_danger.setOnClickListener(this);
        ll_startt.setOnClickListener(this);
        ll_endt.setOnClickListener(this);
        ll_post.setOnClickListener(this);
        ll_subject.setOnClickListener(this);
        tv_addFile.setOnClickListener(this);
//        ll_subjectType.setOnClickListener(this);
        request = new AddTempTaskModel.Request();
        request2 = new GetDangerSelectorModel.Request();
        action_bar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(ed_name.getText().toString().trim())) {
                    request.setName(ed_name.getText().toString().trim());
                } else {
                    showToast("?????????????????????");
                    return;
                }
                if (!TextUtils.isEmpty(ed_content.getText().toString().trim())) {
                    request.setTaskDescription(ed_content.getText().toString().trim());
                } else {
                    showToast("?????????????????????");
                    return;
                }

                if (TextUtils.isEmpty(request.getDangerID())) {
                    showToast("??????????????????");
                    return;
                }
                if (TextUtils.isEmpty(request.getStartTime())) {
                    showToast("???????????????????????????");
                    return;
                }
                if (TextUtils.isEmpty(request.getEndTime())) {
                    showToast("???????????????????????????");
                    return;
                }

//                if(TextUtils.isEmpty(request.getExecutePostID()))
//                {
//                    showToast("?????????????????????");
//                    return;
//                }

                if (mList.size() > 0) {
                    List<InspectTaskSubjectNew> taskSubjects = new ArrayList<>();
                    for (int i = 0; i < mList.size(); i++) {
                        InspectTaskSubjectNew inspectTaskSubjectNew = new InspectTaskSubjectNew();
                        inspectTaskSubjectNew.setSubjectType(mList.get(i).getSubjectType());
                        inspectTaskSubjectNew.setSubjectID(mList.get(i).getSubjectID());
                        inspectTaskSubjectNew.setDangerID(mList.get(i).getDangerID());
                        taskSubjects.add(inspectTaskSubjectNew);
                    }
                    request.setTaskSubjects(taskSubjects);
                } else {
                    showToast("???????????????");
                    return;
                }

                picOrMp4List = picOrMp4Adapter.getData();
                for (int n = 0; n < picOrMp4List.size(); n++) {
                    if (TextUtils.isEmpty(picOrMp4List.get(n).getContent())) {
                        showToast("???????????????????????????????????????");
                        return;
                    }
                }
                attachFileNewList = new ArrayList<>();
                for (int i = 0; i < picOrMp4List.size(); i++) {
                    AttachFileNew attachFileNew = new AttachFileNew();
                    attachFileNew.setFileTitle(picOrMp4List.get(i).getContent());
                    if (picOrMp4List.get(i).getType() == 0) {
                        attachFileNew.setFileUrl(picOrMp4List.get(i).getImagePath());
                        attachFileNew.setFileType("jpg");
                    } else {
                        attachFileNew.setFileUrl(picOrMp4List.get(i).getMp4Path());
                        attachFileNew.setFileType("mp4");
                    }
                    attachFileNewList.add(attachFileNew);
                }
                request.setAttachFiles(attachFileNewList);
                mPresenter.addtasksubject(request);
            }
        });

        mList = new ArrayList<>();
        picOrMp4List = new ArrayList<>();
        adapter = new SubAdapter(R.layout.item_subjects, mList);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_delete) {
                    mList.remove(position);
                    adapter.notifyItemRemoved(position);
                }
            }
        });
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        picOrMp4Adapter = new PicOrMp4Adapter(R.layout.item_picormp4, picOrMp4List);
        picOrMp4Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                picOrMp4List = picOrMp4Adapter.getData();
                switch (view.getId()) {
                    case R.id.img:
                    case R.id.mp4:
                        Logger.d("????????????:" + picOrMp4List.get(position).getImagePath());
                        Logger.d("MP4??????:" + picOrMp4List.get(position).getMp4Path());
                        if (picOrMp4List.get(position).getType() == 0) {
                            startActivityForResult(new Intent(mContext, PhotoViewActivity.class).putExtra("picUrl", picOrMp4List.get(position).getImagePath()), 200);
                        } else {
                            //?????????TBS????????????
                            //????????????????????????
                            if (TbsVideo.canUseTbsPlayer(getApplicationContext())) {
                                //????????????
                                TbsVideo.openVideo(getApplicationContext(), picOrMp4List.get(position).getMp4Path());
                            } else {
                                showToast("TBS?????????????????????");
                            }
                        }
                        break;
                    case R.id.iv_delete:
                        picOrMp4List.remove(position);
                        picOrMp4Adapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        recyclerview2.setAdapter(picOrMp4Adapter);
        recyclerview2.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mPresenter.getselector();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        MyTimePicker myTimePicker = new MyTimePicker();
        switch (v.getId()) {
//            case R.id.ll_subjectType:
//                if(SubTypes.size() > 0)
//                {
//                    SubTypesPicker.show();
//                }
//                else {
//                    showToast("????????????????????????");
//                }
//                break;
            case R.id.tv_addFile:
                getPermissions();
                break;
            case R.id.ll_danger:
                //????????????
                if(Dangers.size() > 0)
                {
                    DangersPicker.show();
                }
                else {
                    showToast("?????????????????????");
                }
                break;
            case R.id.ll_startt:
                StartTimePicker = myTimePicker.getPicker(mContext, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        tv_startt.setText(df.format(date));
                        request.setStartTime(df.format(date));
                    }
                });
                StartTimePicker.show();
                break;
            case R.id.ll_endt:
                //??????????????????
                EndTimePicker = myTimePicker.getPicker(mContext, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        tv_endt.setText(df.format(date));
                        request.setEndTime(df.format(date));
                    }
                });
                EndTimePicker.show();
                break;
            case R.id.ll_subject:
                //?????????
                if(CSubs.size()>0)
                {
                    startActivityForResult(new Intent(mContext, PickDangersActivity.class).putExtra("CSubs", (Serializable) CSubs),201);
                }
                else {
                    if(TextUtils.isEmpty(request2.getDangerPointID()))
                    {
                        showToast("?????????????????????");
                    }
                    else {
                        showToast("??????????????????????????????????????????");
                    }
                }
                break;
            case R.id.ll_post:
                //????????????
                if(Posts.size() > 0)
                {
                    PostPicker.show();
                }
                else {
                    showToast("??????????????????");
                }

                break;
        }
    }


    @Override
    public void addtasksubjectSuc(AddTempTaskModel.Response response) {
        if (response.isData()) {
            showToast("???????????????????????????");
            finish();
        }
    }

    public void getdangerselector() {
        if (TextUtils.isEmpty(request2.getDangerPointID())) {
            return;
        }
        mPresenter.getdangerselector(request2);
    }

    @Override
    public void getdangerselectorSuc(GetDangerSelectorModel.Response response) {
//????????????????????????
        CSubs = response.getData();
        mList.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getselectorSuc(GetSelectorModel.Response response) {
        MyOptionsPicker picker = new MyOptionsPicker();
        //??????????????????????????????
//        SubTypes = response.getData().getSubTypes();
//        SubTypesPicker = picker.getPicker(mContext, "??????????????????", new OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                if (SubTypes.size() > 0) {
//                    tv_subjectType.setText(SubTypes.get(options1).getSubTypeName());
//                    getdangerselector();
//                }
//            }
//        });
//        SubTypesPicker.setPicker(SubTypes, null, null);

        //?????????????????????
        Dangers = response.getData().getDangers();
        DangersPicker = picker.getPicker(mContext, "???????????????", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (Dangers.size() > 0) {
                    tv_danger.setText(Dangers.get(options1).getDangerName());
                    request.setDangerID(Dangers.get(options1).getDangerID());
                    request2.setDangerPointID(Dangers.get(options1).getDangerID());
                    getdangerselector();

                }
            }
        });
        DangersPicker.setPicker(Dangers, null, null);
        //????????????????????????
        Posts = response.getData().getPosts();
        PostPicker = picker.getPicker(mContext, "????????????", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (Posts.size() > 0) {
                    tv_post.setText(Posts.get(options1).getPostName());
                    request.setExecutePostID(Posts.get(options1).getPostID());
                }

            }
        });
        PostPicker.setPicker(Posts, null, null);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 200) {
            if (resultCode == Activity.RESULT_OK) {
                picOrMp4List = picOrMp4Adapter.getData();
                GetPicModel picOrMp4 = new GetPicModel();
                picOrMp4 = (GetPicModel) data.getSerializableExtra("result");
//                picOrMp4.setRecordId(recordId);
                picOrMp4.setIsUpload(1);
//                picOrMp4.setSubjectID(subjectID);
                picOrMp4List.add(picOrMp4);
                picOrMp4Adapter.notifyDataSetChanged();
            }
        }
        else if(requestCode==201){
            if (resultCode == Activity.RESULT_OK) {
                mList.clear();
                mList.addAll ((List<SubjectSelectModel>) data.getSerializableExtra("subjectSelectModels"));
                adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * ????????????
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
                        //?????????????????????????????????
                        showToast("?????????????????????");
                    } else {
                        //?????????????????????????????????????????????????????????
                        showToast("???????????????????????????????????????");
                    }
                });
    }
}
