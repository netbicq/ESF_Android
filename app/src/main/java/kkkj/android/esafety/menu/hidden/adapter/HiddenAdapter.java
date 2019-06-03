package kkkj.android.esafety.menu.hidden.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.APPTroubleCtrView;
import kkkj.android.esafety.utils.SizeTransform;


public class HiddenAdapter extends BaseQuickAdapter<APPTroubleCtrView, BaseViewHolder> {
    public HiddenAdapter(int layoutResId, @Nullable List<APPTroubleCtrView> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, APPTroubleCtrView item) {
        //当前人
        // 1负责人 处理 归档 转让责任人 快速处理
        // 2执行人 申请验收
        // 3验收人 验收
        TextView tv_menu1 = helper.getView(R.id.tv_menu1);
        TextView tv_menu2 = helper.getView(R.id.tv_menu2);
        TextView tv_menu3 = helper.getView(R.id.tv_menu3);
        TextView tv_menu4 = helper.getView(R.id.tv_menu4);
        TextView tv_menu5 = helper.getView(R.id.tv_menu5);
        TextView tv_menu6 = helper.getView(R.id.tv_menu6);
        LinearLayout ll_container = helper.getView(R.id.ll_container);

        if (item.getCuser() == 1) {
            //1负责人 处理 归档 转让责任人 快速处理
            tv_menu3.setVisibility(View.VISIBLE);
            tv_menu4.setVisibility(View.VISIBLE);
            tv_menu5.setVisibility(View.VISIBLE);
            tv_menu6.setVisibility(View.VISIBLE);

            if (item.getState() == 4) {
                tv_menu3.setVisibility(View.GONE);
                tv_menu4.setVisibility(View.GONE);
                tv_menu5.setVisibility(View.GONE);
                tv_menu6.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 0), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 3) {
                tv_menu3.setVisibility(View.GONE);
                tv_menu5.setVisibility(View.GONE);
                tv_menu6.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 60), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 2) {
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 4 * 60), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 1) {
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 4 * 60), ViewGroup.LayoutParams.MATCH_PARENT));
            }

        } else if (item.getCuser() == 2) {
            tv_menu1.setVisibility(View.VISIBLE);
            if (item.getState() == 4) {
                tv_menu1.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 0), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 3) {
                tv_menu1.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 0), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 2) {
                tv_menu1.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 0), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 1) {
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 60), ViewGroup.LayoutParams.MATCH_PARENT));
            }


        } else if (item.getCuser() == 3) {
            tv_menu2.setVisibility(View.VISIBLE);

            if (item.getState() == 4) {
                tv_menu2.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 0), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 3) {
                tv_menu2.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 0), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 2) {
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 60), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 1) {
                tv_menu2.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 0), ViewGroup.LayoutParams.MATCH_PARENT));
            }
        } else if (item.getCuser() == 4) {
            //4负责人 执行人 处理 归档 转让责任人 快速处理
            tv_menu1.setVisibility(View.VISIBLE);
            tv_menu3.setVisibility(View.VISIBLE);
            tv_menu4.setVisibility(View.VISIBLE);
            tv_menu5.setVisibility(View.VISIBLE);
            tv_menu6.setVisibility(View.VISIBLE);

            if (item.getState() == 4) {
                tv_menu1.setVisibility(View.GONE);
                tv_menu3.setVisibility(View.GONE);
                tv_menu4.setVisibility(View.GONE);
                tv_menu5.setVisibility(View.GONE);
                tv_menu6.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 0), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 3) {
                tv_menu1.setVisibility(View.GONE);
                tv_menu3.setVisibility(View.GONE);
                tv_menu5.setVisibility(View.GONE);
                tv_menu6.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 60), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 2) {
                tv_menu1.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 4 * 60), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 1) {
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 5 * 60), ViewGroup.LayoutParams.MATCH_PARENT));
            }

        } else if (item.getCuser() == 5) {
            tv_menu2.setVisibility(View.VISIBLE);
            tv_menu3.setVisibility(View.VISIBLE);
            tv_menu4.setVisibility(View.VISIBLE);
            tv_menu5.setVisibility(View.VISIBLE);
            tv_menu6.setVisibility(View.VISIBLE);

            if (item.getState() == 4) {
                tv_menu2.setVisibility(View.GONE);
                tv_menu3.setVisibility(View.GONE);
                tv_menu4.setVisibility(View.GONE);
                tv_menu5.setVisibility(View.GONE);
                tv_menu6.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 0), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 3) {
                tv_menu2.setVisibility(View.GONE);
                tv_menu3.setVisibility(View.GONE);
                tv_menu5.setVisibility(View.GONE);
                tv_menu6.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 60), ViewGroup.LayoutParams.MATCH_PARENT));

            } else if (item.getState() == 2) {
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 5 * 60), ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (item.getState() == 1) {
                tv_menu2.setVisibility(View.GONE);
                ll_container.setLayoutParams(new LinearLayout.LayoutParams(SizeTransform.dip2px(mContext, 4 * 60), ViewGroup.LayoutParams.MATCH_PARENT));
            }

        }


        if (!TextUtils.isEmpty(item.getDangerPoint())) {
            helper.setText(R.id.tv_left1, item.getDangerPoint());
        }

        if (item.getState() == 1) {
            helper.setText(R.id.tv_right1, "管控中");
        } else if (item.getState() == 2) {
            helper.setText(R.id.tv_right1, "申请验收");
        } else if (item.getState() == 3) {
            helper.setText(R.id.tv_right1, "已验收");
        } else if (item.getState() == 4) {
            helper.setText(R.id.tv_right1, "已归档");
        }


//        if(item.getState()==4)
//        {
//            tv_menu1.setVisibility(View.GONE);
//            tv_menu2.setVisibility(View.GONE);
//            tv_menu3.setVisibility(View.GONE);
//            tv_menu4.setVisibility(View.GONE);
//            tv_menu5.setVisibility(View.GONE);
//            tv_menu6.setVisibility(View.GONE);
//        }
//        else if(item.getState()==3){
//            tv_menu1.setVisibility(View.GONE);
//            tv_menu2.setVisibility(View.GONE);
//            tv_menu3.setVisibility(View.GONE);
//            tv_menu5.setVisibility(View.GONE);
//            tv_menu6.setVisibility(View.GONE);
//
//        }else if(item.getState()==2){
//            tv_menu1.setVisibility(View.GONE);
//        }else if(item.getState()==1){
//            tv_menu2.setVisibility(View.GONE);
//        }

        if (!TextUtils.isEmpty(item.getCDangerLevelName())) {
            helper.setText(R.id.tv_left2, item.getCDangerLevelName());
        }

        helper.setText(R.id.tv_right2, item.getTroubleLevelName());

        helper.addOnClickListener(R.id.tv_menu1);//申请验收
        helper.addOnClickListener(R.id.tv_menu2);//验收
        helper.addOnClickListener(R.id.tv_menu3);//处理
        helper.addOnClickListener(R.id.tv_menu4);//归档
        helper.addOnClickListener(R.id.tv_menu5);//转让责任人
        helper.addOnClickListener(R.id.tv_menu6);//快速处理
    }
}
