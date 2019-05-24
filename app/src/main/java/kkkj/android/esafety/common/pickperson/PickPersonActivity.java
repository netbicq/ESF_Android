package kkkj.android.esafety.common.pickperson;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import org.litepal.LitePal;


import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.Emp;
import kkkj.android.esafety.bean.Org;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import me.texy.treeview.TreeNode;
import me.texy.treeview.TreeView;

public class PickPersonActivity extends MvpBaseActivity {

    private ViewGroup viewGroup;
    private TreeNode root;
    private TreeView treeView;
    @Override
    protected int getLayout() {
        return R.layout.activity_pickperson;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("人员选择");
        viewGroup = findViewById(R.id.container);
        root = TreeNode.root();
        buildTree();
        treeView = new TreeView(root, this, new PersonNodeViewFactory(new ChildNodeViewBinder.ChooseCallBack() {
            @Override
            public void choose(String id,String name) {
                //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("PID", id);
                intent.putExtra("PName", name);
                //设置返回数据
                mActivity.setResult(RESULT_OK, intent);
                //关闭Activity
                finish();
            }
        }));
        View view = treeView.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewGroup.addView(view);
    }

    private void getChild(String keyID,int level, TreeNode treeNode)
    {
        level++;
        List<Org> child = LitePal.where("ParentID=?",keyID). find(Org.class);
        if(child.size()>0)
        {
            for(int i = 0 ;i<child.size();i++)
            {
                TreeNode treeNodeChild = new TreeNode(new String(child.get(i).getOrgName()));
                treeNodeChild.setLevel(level);
                treeNode.addChild(treeNodeChild);
                getChild(child.get(i).getKeyID(),level,treeNodeChild);//递归调用
            }
        }
        else {
            List<Emp> person = LitePal.where("OrgID=?",keyID). find(Emp.class);
            for(int i = 0 ;i<person.size();i++)
            {
                person.get(i).setLevel(level);
                TreeNode personNode = new TreeNode(person.get(i));
                personNode.setLevel(-1);
                treeNode.addChild(personNode);
            }
        }
    }

    private void buildTree() {
        List<Org> rootOrg = LitePal.where("ParentID=?","00000000-0000-0000-0000-000000000000"). find(Org.class);
        Logger.d(rootOrg.size());
        for(int i = 0 ;i<rootOrg.size();i++)
        {
            TreeNode treeNode = new TreeNode(new String(rootOrg.get(i).getOrgName()));
            treeNode.setLevel(0);
            getChild(rootOrg.get(i).getKeyID(),1,treeNode);
            root.addChild(treeNode);
        }
    }
}
