package kkkj.android.esafety.common.pickperson;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kkkj.android.esafety.R;
import me.texy.treeview.TreeNode;
import me.texy.treeview.base.BaseNodeViewBinder;

/**
 * Created by zxy on 17/4/23.
 */

public class RootNodeViewBinder extends BaseNodeViewBinder {
    TextView textView;
    ImageView imageView;
    public RootNodeViewBinder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.name);
        imageView = (ImageView) itemView.findViewById(R.id.arrow_icon);
    }
    @Override
    public int getLayoutId() {
        return R.layout.item_persontree_root;
    }

    @Override
    public void bindView(final TreeNode treeNode) {
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
