package kkkj.android.esafety.menu.bill.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.Logger;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.SubStandard;
import kkkj.android.esafety.menu.bill.adapter.StandardAdapter;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.mvpInterface.MvpPresenter;

public class ViewStandardActivity extends MvpBaseActivity {
    String  TaskSubjectViewKeyID;

    List<SubStandard> mList;

    StandardAdapter adapter ;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @Override
    protected int getLayout() {
        return R.layout.activity_view_standard;
    }
    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }
    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("查看标准");
        TaskSubjectViewKeyID = getIntent().getStringExtra("TaskSubjectViewKeyID");
        mList = LitePal.where("keyID = ?",TaskSubjectViewKeyID).find(SubStandard.class);
        adapter = new StandardAdapter(R.layout.item_look,mList);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
    }
}
