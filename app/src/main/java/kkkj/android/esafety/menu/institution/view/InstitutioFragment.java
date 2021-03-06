package kkkj.android.esafety.menu.institution.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.PhoneDocInstitutionView;
import kkkj.android.esafety.menu.institution.adapter.InstitutionAdapter;
import kkkj.android.esafety.menu.institution.contract.InstitutioFContract;
import kkkj.android.esafety.menu.institution.presenter.InstitutioFPresenter;
import kkkj.android.esafety.mvpInterface.MvpBaseFragment;

/**
 * 制度
 */
public class InstitutioFragment extends MvpBaseFragment<InstitutioFPresenter> implements InstitutioFContract.View {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    List<PhoneDocInstitutionView> mList;
    InstitutionAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.smart_recycler;
    }

    @Override
    protected InstitutioFPresenter getPresenter() {
        return new InstitutioFPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        smartRefreshLayout.autoRefresh();
    }

    @Override
    protected void initMonitorAndData() {
        mList = new ArrayList<>();
        smartRefreshLayout.setEnableLoadMore(false);
        adapter = new InstitutionAdapter(R.layout.item_institutio, mList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext,InstitutionContentActivity.class)
                        .putExtra("insid",mList.get(position).getInstitutionID())
                        .putExtra("name",mList.get(position).getName()));
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getdocinslist();
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
    public void getdocinslistSuc(List<PhoneDocInstitutionView> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
