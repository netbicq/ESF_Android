package kkkj.android.esafety.menu.work.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.OpreateBillFlow;
import kkkj.android.esafety.bean.OpreateBillFlowDetials;

public class WorkDetailsAdapter extends BaseQuickAdapter<OpreateBillFlow, BaseViewHolder> {
    public WorkDetailsAdapter(int layoutResId, @Nullable List<OpreateBillFlow> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OpreateBillFlow item) {
        ImageView iv_complete = helper.getView(R.id.iv_complete);
        View line = helper.getView(R.id.line);
        View line2 = helper.getView(R.id.line2);
        QMUIRadiusImageView indicator = helper.getView(R.id.indicator);
        View line3 = helper.getView(R.id.line3);
        TextView tv_index = helper.getView(R.id.tv_index);
        TextView tv_pointName = helper.getView(R.id.tv_pointName);
        TextView tv_postName = helper.getView(R.id.tv_postName);

        Button btn_complete = helper.getView(R.id.btn_complete);
        Button btn_stop = helper.getView(R.id.btn_stop);
        Button btn_back = helper.getView(R.id.btn_back);

        if(item.getFlowUEModel().isFinishEnable())
        {
            btn_complete.setVisibility(View.VISIBLE);
        }
        else {
            btn_complete.setVisibility(View.GONE);
        }
        if(item.getFlowUEModel().isStopEnable())
        {
            btn_stop.setVisibility(View.VISIBLE);
        }
        else {
            btn_stop.setVisibility(View.GONE);
        }
        if(item.getFlowUEModel().isReBackEnable())
        {
            btn_back.setVisibility(View.VISIBLE);
        }
        else {
            btn_back.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.btn_complete);
        helper.addOnClickListener(R.id.btn_stop);
        helper.addOnClickListener(R.id.btn_back);

        if(item.getFlowUEModel().isLeftLine())
        {
            tv_pointName.setTextColor(Color.parseColor("#2bb673"));
            tv_postName.setTextColor(Color.parseColor("#2bb673"));
            iv_complete.setVisibility(View.VISIBLE);
            tv_index.setVisibility(View.GONE);
        }
        else {
            iv_complete.setVisibility(View.GONE);
            tv_index.setVisibility(View.VISIBLE);
        }

        if(item.getFlowUEModel().isRightLien())
        {
            indicator.setImageResource(R.color.orange);
            line2.setBackgroundColor(Color.parseColor("#FFA500"));
        }
        else {
            indicator.setImageResource(R.color.gray);
            line2.setBackgroundColor(Color.parseColor("#dddddd"));
        }


        if(getData().size()-1==helper.getAdapterPosition())
        {
            line.setVisibility(View.INVISIBLE);
            line3.setVisibility(View.INVISIBLE);
        }
        if(helper.getAdapterPosition()==0)
        {
            line2.setVisibility(View.INVISIBLE);
        }
        tv_pointName.setText(item.getPointName());
        tv_postName.setText(item.getPostName());
        helper.setText(R.id.tv_index,item.getPointIndex()+"");

        //有执行结果的话 线就是绿色
        if(item.getDetials().size()>0)
        {
            line.setBackgroundResource(R.color.complete);
            RecyclerView recyclerView = helper.getView(R.id.recyclerview2);
            OpreateBillFlowDetials tabName = new OpreateBillFlowDetials();

            tabName.setFlowEmployeeName("执行人");
            tabName.setFlowMemo("执行结果");
            item.getDetials().add(0,tabName);
            OpreateFlowFetialsAdapter adapter = new OpreateFlowFetialsAdapter(R.layout.item_opreateflowdetials,item.getDetials());
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        }
        else {

        }
    }
}
