package kkkj.android.esafety.common.pickperson;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.Emp;
import kkkj.android.esafety.utils.SizeTransform;
import me.texy.treeview.TreeNode;
import me.texy.treeview.base.BaseNodeViewBinder;

/**
 * Created by zxy on 17/4/23.
 */

public class ChildNodeViewBinder extends BaseNodeViewBinder {
    TextView textView;
    LinearLayout ll_child;
    ChooseCallBack callBack;
    public interface ChooseCallBack
    {
        void choose(String id, String name);
    }
    public ChildNodeViewBinder(View itemView,ChooseCallBack chooseCallBack) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.name);
        ll_child = (LinearLayout)itemView.findViewById(R.id.ll_child);
        callBack = chooseCallBack;
    }
    @Override
    public int getLayoutId() {
        return R.layout.item_persontree_child;
    }

    @Override
    public void bindView(TreeNode treeNode) {
        Emp emp = (Emp)treeNode.getValue();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(textView.getLayoutParams());

        lp.setMargins(emp.getLevel()* SizeTransform.dip2px(10), 0, 0, 0);
        textView.setLayoutParams(lp);

        textView.setText(emp.getCNName());

        ll_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.choose(emp.getKeyID(),emp.getCNName());
            }
        });
    }
}
