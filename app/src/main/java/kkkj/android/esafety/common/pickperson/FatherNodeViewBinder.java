package kkkj.android.esafety.common.pickperson;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import kkkj.android.esafety.R;
import kkkj.android.esafety.utils.SizeTransform;
import me.texy.treeview.TreeNode;
import me.texy.treeview.base.BaseNodeViewBinder;

/**
 * Created by zxy on 17/4/23.
 */

public class FatherNodeViewBinder extends BaseNodeViewBinder {

    TextView textView;
    ImageView imageView;
    int value =1;
    public FatherNodeViewBinder(View itemView,int value) {
        super(itemView);
        this.textView = (TextView) itemView.findViewById(R.id.name);
        this.imageView = (ImageView) itemView.findViewById(R.id.arrow_icon);
        this.value = value;
    }
    @Override
    public int getLayoutId() {
        return R.layout.item_persontree_father;
    }

    @Override
    public void bindView(final TreeNode treeNode) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(imageView.getLayoutParams());
        lp.setMargins(value* SizeTransform.dip2px(10), 5, 5, 5);
        imageView.setLayoutParams(lp);
        textView.setText(treeNode.getValue().toString());
        imageView.setRotation(treeNode.isExpanded() ? 90 : 0);
    }

    @Override
    public void onNodeToggled(TreeNode treeNode, boolean expand) {
        if (expand) {
            imageView.animate().rotation(90).setDuration(200).start();
        } else {
            imageView.animate().rotation(0).setDuration(200).start();
        }
    }
}
