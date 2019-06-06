package kkkj.android.esafety.common.pickdangerpoint;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.Emp;
import kkkj.android.esafety.bean.Sub;
import kkkj.android.esafety.utils.SizeTransform;
import me.texy.treeview.TreeNode;
import me.texy.treeview.base.BaseNodeViewBinder;

/**
 * Created by zxy on 17/4/23.
 */

public class ChildDangerNodeViewBinder extends BaseNodeViewBinder {
    CheckBox checkBox;
    LinearLayout ll_child;
    ChooseCallBack callBack;
    public interface ChooseCallBack
    {
        void choose(Sub.Danger danger);
    }
    public ChildDangerNodeViewBinder(View itemView, ChooseCallBack chooseCallBack) {
        super(itemView);
        checkBox = (CheckBox) itemView.findViewById(R.id.name);
        ll_child = (LinearLayout)itemView.findViewById(R.id.ll_child);
        callBack = chooseCallBack;
    }
    @Override
    public int getLayoutId() {
        return R.layout.item_persontree_child;
    }

    @Override
    public void bindView(TreeNode treeNode) {
        Sub.Danger danger = (Sub.Danger)treeNode.getValue();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(checkBox.getLayoutParams());
        lp.setMargins(5* SizeTransform.dip2px(10), 0, 0, 0);
        checkBox.setLayoutParams(lp);
        checkBox.setText(danger.getDangerName());
        checkBox.setChecked(danger.isChecked());
        checkBox.setClickable(false);
        ll_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(!checkBox.isChecked());
                danger.setChecked(checkBox.isChecked());
                callBack.choose(danger);
            }
        });
    }
}
