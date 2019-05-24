package kkkj.android.esafety.menu.institution.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.Basic_Dict;
import kkkj.android.esafety.bean.PhoneDocSolutionView;
import kkkj.android.esafety.customer.MyOptionsPicker;
import kkkj.android.esafety.menu.institution.adapter.ReservePlanAdapter;
import kkkj.android.esafety.menu.institution.contract.ReservePlanContract;
import kkkj.android.esafety.menu.institution.model.GetdocSolutionListModel;
import kkkj.android.esafety.menu.institution.presenter.ReservePlanPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;

/**
 * 预案
 */
public class ReservePlanFragment extends MvpBaseFragment<ReservePlanPresenter> implements ReservePlanContract.View {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.ll_danger)
    LinearLayout ll_danger;
    List<PhoneDocSolutionView> mList;
    List<Basic_Dict> mData;
    ReservePlanAdapter adapter;
    int result = 0;
    OptionsPickerView DangersPicker;
    List<String> Dangers = new ArrayList<>();
    @BindView(R.id.tv_danger)
    TextView tv_danger;
    @Override
    protected int getLayout() {
        return R.layout.fragment_reserve_plan;
    }

    @Override
    protected ReservePlanPresenter getPresenter() {
        return new ReservePlanPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getdangerdict();
        smartRefreshLayout.autoRefresh();
    }

    @Override
    protected void initMonitorAndData() {
        mList = new ArrayList<>();
        mData=new ArrayList<>();

        smartRefreshLayout.setEnableLoadMore(false);
        adapter = new ReservePlanAdapter(R.layout.item_reserve_plan, mList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext,RPlanContentActivity.class)
                        .putExtra("docsolutionid",mList.get(position).getDocSolutionID())
                        .putExtra("name",mList.get(position).getName()));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                GetdocSolutionListModel.Request request = new GetdocSolutionListModel.Request();
                if(result==0)
                {
                    tv_danger.setText("默认");
                }
                else {
                    request.setDangerlevelid(mData.get(result).getID());
                }

                mPresenter.getdocsolutionlist(request);
            }
        });
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
    public void getdocsolutionlistSuc(List<PhoneDocSolutionView> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getdangerdict(List<Basic_Dict> data) {
        mData.clear();
        Basic_Dict basic_dict =new Basic_Dict();
        basic_dict.setDictName("默认");
        basic_dict.setID("00000000-0000-0000-0000-000000000000");
        mData.add(basic_dict);
        mData.addAll(data);
        List<String> list1= new ArrayList();
        list1.add("默认");
        for(int i = 0;i<data.size();i++)
        {
            list1.add(data.get(i).getDictName());
        }

        MyOptionsPicker picker = new MyOptionsPicker();
        //初始化风险选择
        Dangers = list1;
        DangersPicker = picker.getPicker(mContext,"风险点选择", new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_danger.setText(Dangers.get(options1));
                result = options1;
                smartRefreshLayout.autoRefresh();
            }
        });
        DangersPicker.setPicker(Dangers, null, null);
        ll_danger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangersPicker.show();
            }
        });
    }
}
