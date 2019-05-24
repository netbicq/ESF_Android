package kkkj.android.esafety.menu.mine.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.menu.mine.adapter.MyMessageAdapter;
import kkkj.android.esafety.menu.mine.model.MyMessage;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.mvpInterface.MvpPresenter;

public class MyMessageActivity extends MvpBaseActivity {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    MyMessageAdapter adapter;
    List<MyMessage.Data> mList;
    @Override
    protected int getLayout() {
        return R.layout.activity_mymessage;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("我的消息");

        mList = new ArrayList<>();

        setData();
        adapter = new MyMessageAdapter(R.layout.item_mymessage,mList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
    }

    public void setData()
    {
        MyMessage.Data data = new MyMessage.Data();
        data.setTitle("您有一条新消息");
        data.setName("系统消息");
        data.setImg("http://img2.imgtn.bdimg.com/it/u=234634259,4236876085&fm=26&gp=0.jpg");
        mList.add(data);
        mList.add(data);
        mList.add(data);
        mList.add(data);
        mList.add(data);
    }
}
