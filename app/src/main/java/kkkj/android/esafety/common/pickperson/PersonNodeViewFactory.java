package kkkj.android.esafety.common.pickperson;

import android.view.View;

import me.texy.treeview.base.BaseNodeViewBinder;
import me.texy.treeview.base.BaseNodeViewFactory;


/**
 * Created by zxy on 17/4/23.
 */

public class PersonNodeViewFactory extends BaseNodeViewFactory {
    ChildNodeViewBinder.ChooseCallBack chooseCallBack;

    public PersonNodeViewFactory(ChildNodeViewBinder.ChooseCallBack chooseCallBack) {
        this.chooseCallBack = chooseCallBack;
    }

    @Override
    public BaseNodeViewBinder getNodeViewBinder(View view, int level) {
        switch (level) {
            case 0:
                return new RootNodeViewBinder(view);
            case -1:
                return new ChildNodeViewBinder(view,chooseCallBack);
            default:
                return new FatherNodeViewBinder(view,level);
        }
    }
}
