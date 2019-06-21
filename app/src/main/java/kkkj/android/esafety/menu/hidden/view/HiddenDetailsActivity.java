package kkkj.android.esafety.menu.hidden.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.smtt.sdk.TbsVideo;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.APPTroubleCtrView;
import kkkj.android.esafety.bean.Bll_AttachFile;
import kkkj.android.esafety.bean.DangerLevelDict;
import kkkj.android.esafety.bean.Dict;
import kkkj.android.esafety.common.getpic.GetPicModel;
import kkkj.android.esafety.common.getpic.PhotoViewActivity;
import kkkj.android.esafety.http.ApiConfig;
import kkkj.android.esafety.http.RetrofitServiceManager;
import kkkj.android.esafety.http.api.APIAttachfile;
import kkkj.android.esafety.menu.bill.adapter.PicOrMp4Adapter;
import kkkj.android.esafety.menu.bill.model.GetSubResultModel;
import kkkj.android.esafety.menu.hidden.contract.HiddenDetailsContract;
import kkkj.android.esafety.menu.hidden.presenter.HiddenDetailsPresenter;
import kkkj.android.esafety.model.DownLoadFileModel;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.utils.SoftHideKeyBoardUtil;

import static kkkj.android.esafety.mvpInterface.MvpModel.RESPONSE_OK;

/**
 * 管控项详情
 */
public class HiddenDetailsActivity extends MvpBaseActivity<HiddenDetailsPresenter> implements HiddenDetailsContract.View {

    @BindView(R.id.tv_zhixr)
    TextView tv_执行人;
    @BindView(R.id.tv_yansr)
    TextView tv_验收人;
    @BindView(R.id.tv_time)
    TextView tv_预计完成时间;
    @BindView(R.id.tv_fxdj)
    TextView tv_风险等级;
    @BindView(R.id.tv_yhdj)
    TextView tv_隐患等级;
    @BindView(R.id.tv_zt)
    TextView tv_主体;
    @BindView(R.id.tv_fkx)
    TextView tv_风控项;
    @BindView(R.id.ed_describe)
    EditText tv_管控内容;
    @BindView(R.id.id_tv_findMan)
    TextView tv_发现人;
    @BindView(R.id.ed_describe1)
    EditText et_问题描述;
    @BindView(R.id.id_recyclerView)
    RecyclerView recyclerView;

    private List<GetPicModel> mList;
    private PicOrMp4Adapter picOrMp4Adapter;


    List<DangerLevelDict> DangerLevels = new ArrayList<>();
    List<Dict> TroubleLevels = new ArrayList<>();
    APPTroubleCtrView trouble;

    @Override
    protected int getLayout() {
        return R.layout.activity_hidden_details;
    }

    @Override
    protected HiddenDetailsPresenter getPresenter() {
        return new HiddenDetailsPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        SoftHideKeyBoardUtil.assistActivity(mActivity);
        action_bar_title.setText("处理管控项");
        DangerLevels = LitePal.findAll(DangerLevelDict.class);
        TroubleLevels = LitePal.findAll(Dict.class);
        trouble = (APPTroubleCtrView) getIntent().getSerializableExtra("Trouble");
        tv_执行人.setText(trouble.getExecutor());
        tv_验收人.setText(trouble.getAcceptor());
        tv_预计完成时间.setText(trouble.getEstimatedDate());
        tv_隐患等级.setText(trouble.getTroubleLevelName());
        tv_风险等级.setText(trouble.getCDangerLevelName());
        tv_主体.setText(trouble.getSubName());
        tv_风控项.setText(trouble.getDangerName());
        tv_管控内容.setText(trouble.getCtrTarget());
        tv_管控内容.setEnabled(false);
        tv_发现人.setText("发现人:" + trouble.getFEmp());
        et_问题描述.setText(trouble.getTroubleDetails());
        et_问题描述.setEnabled(false);

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

                }
            }
        });
        recyclerView.setAdapter(picOrMp4Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(trouble.getBID())) {
            DownLoadFileModel.Request request = new DownLoadFileModel.Request();
            request.setBusinessid(trouble.getBID());
            mPresenter.getfiles(request);
        }
    }

    @Override
    public void getfilesSuc(List<Bll_AttachFile> data) {
        mList.clear();
        for (int i = 0; i < data.size(); i++) {
            GetPicModel picOrMp4 = new GetPicModel();
            if (data.get(i).getFileType().equals("jpg")) {
                picOrMp4.setType(0);//照片
                picOrMp4.setImagePath(data.get(i).getFileUrl().replace("~", ApiConfig.BASE_URL));
            } else {
                picOrMp4.setType(1);//视频
                picOrMp4.setImagePath("default");
                picOrMp4.setMp4Path(data.get(i).getFileUrl().replace("~", ApiConfig.BASE_URL));
            }
            picOrMp4.setContent(data.get(i).getFileTitle());
            picOrMp4.setIsDwon(1);
            mList.add(picOrMp4);
        }
        picOrMp4Adapter.notifyDataSetChanged();
    }
}

