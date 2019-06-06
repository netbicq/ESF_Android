package kkkj.android.esafety.common.pickdangerpoint;

import android.view.View;

import me.texy.treeview.base.BaseNodeViewBinder;
import me.texy.treeview.base.BaseNodeViewFactory;


/**
 * Created by zxy on 17/4/23.
 */

public class DangerNodeViewFactory extends BaseNodeViewFactory {
    ChildDangerNodeViewBinder.ChooseCallBack chooseCallBack;

    public DangerNodeViewFactory(ChildDangerNodeViewBinder.ChooseCallBack chooseCallBack) {
        this.chooseCallBack = chooseCallBack;
    }

    @Override
    public BaseNodeViewBinder getNodeViewBinder(View view, int level) {
        switch (level) {
            case 0:
                return new RootDangerNodeViewBinder(view);
            case -1:
                return new ChildDangerNodeViewBinder(view,chooseCallBack);
            default:
                return new FatherDangerNodeViewBinder(view,level);
        }
    }
}
