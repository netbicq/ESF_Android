package kkkj.android.esafety.common.pickdangerpoint;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.CSub;
import kkkj.android.esafety.bean.InspectTaskSubjectNew;
import kkkj.android.esafety.bean.Sub;
import kkkj.android.esafety.bean.SubjectSelectModel;
import kkkj.android.esafety.common.getpic.GetPicModel;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.mvpInterface.MvpPresenter;
import me.texy.treeview.TreeNode;
import me.texy.treeview.TreeView;

public class PickDangersActivity extends MvpBaseActivity {
    List<CSub> mList;
    private ViewGroup viewGroup;
    private TreeNode root;
    private TreeView treeView;

    List<SubjectSelectModel> subjectSelectModels;
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
        action_bar_title.setText("风控项选择");
        action_bar_right.setText("确定");

        action_bar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("subjectSelectModels", (Serializable) subjectSelectModels);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        viewGroup = findViewById(R.id.container);
        root = TreeNode.root();
        subjectSelectModels = new ArrayList<>();
        mList = (List<CSub>) getIntent().getSerializableExtra("CSubs");
        for(int i = 0 ;i<mList.size();i++)
        {
            if(mList.get(i).getSubs().size()>0)
            {
                for(int j = 0 ;j<mList.get(i).getSubs().size();j++)
                {
                    mList.get(i).getSubs().get(j).setSubTypeID(mList.get(i).getSubTypeID());
                    for(int k = 0 ;k<mList.get(i).getSubs().get(j).getDangers().size();k++)
                    {
                        Logger.d(mList.get(i).getSubs().get(j).getDangers().get(k).getDangerName()+"---"+mList.get(i).getSubs().get(j).getSubID());
                        mList.get(i).getSubs().get(j).getDangers().get(k)
                                .setSubID(mList.get(i).getSubs().get(j).getSubID());
                    }
                }
            }
            else {
//                mList.remove(i);
            }
        }
        buildTree();
        treeView = new TreeView(root, this, new DangerNodeViewFactory(new ChildDangerNodeViewBinder.ChooseCallBack() {
            @Override
            public void choose(Sub.Danger danger) {
                Logger.d(danger.getDangerName()+"______"+danger.isChecked()+"-subid-"+danger.getSubID()+"-dangerid-"+danger.getDangerID());
                for(int i = 0 ;i<mList.size();i++)
                {
                    for(int j  = 0 ;j<mList.get(i).getSubs().size();j++)
                    {
                        for(int k = 0 ;k<mList.get(i).getSubs().get(j).getDangers().size();k++)
                        {
                            if(mList.get(i).getSubs().get(j).getDangers().get(k).getDangerID().equals(danger.getDangerID())
                            &&mList.get(i).getSubs().get(j).getSubID().equals(danger.getSubID()))
                            {
                                if(danger.isChecked())
                                {
                                    SubjectSelectModel subjectSelectModel = new SubjectSelectModel();
                                    subjectSelectModel.setDangerID(danger.getDangerID());
                                    subjectSelectModel.setSubjectType(mList.get(i).getSubs().get(j).getSubTypeID());
                                    subjectSelectModel.setSubjectID(danger.getSubID());
                                    subjectSelectModel.setSubTypeName(mList.get(i).getSubs().get(j).getSubName());
                                    subjectSelectModel.setEntityTypeName(danger.getDangerName());
                                    subjectSelectModels.add(subjectSelectModel);
                                }
                                else {
                                    for(int m = 0 ;m<subjectSelectModels.size();m++)
                                    {
                                        if(danger.getDangerID().equals(subjectSelectModels.get(m).getDangerID())
                                               &&danger.getSubID().equals(subjectSelectModels.get(m).getSubjectID())
                                        )
                                        {
                                            subjectSelectModels.remove(m);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }));
        View view = treeView.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewGroup.addView(view);
    }

//    private void getChild(String keyID,int level, TreeNode treeNode)
//    {
//        level++;
//        List<Org> child = LitePal.where("ParentID=?",keyID). find(Org.class);
//        if(child.size()>0)
//        {
//            for(int i = 0 ;i<child.size();i++)
//            {
//                TreeNode treeNodeChild = new TreeNode(new String(child.get(i).getOrgName()));
//                treeNodeChild.setLevel(level);
//                treeNode.addChild(treeNodeChild);
//                getChild(child.get(i).getKeyID(),level,treeNodeChild);//递归调用
//            }
//        }
//        else {
//            List<Emp> person = LitePal.where("OrgID=?",keyID). find(Emp.class);
//            for(int i = 0 ;i<person.size();i++)
//            {
//                person.get(i).setLevel(level);
//                TreeNode personNode = new TreeNode(person.get(i));
//                personNode.setLevel(-1);
//                treeNode.addChild(personNode);
//            }
//        }
//    }

    private void buildTree() {
        for(int i = 0 ;i<mList.size();i++)
        {
            TreeNode treeNode = new TreeNode(new String(mList.get(i).getSubType()));
            treeNode.setLevel(0);
            for(int j  = 0 ;j<mList.get(i).getSubs().size();j++)
            {
                TreeNode treeNode2 = new TreeNode(new String(mList.get(i).getSubs().get(j).getSubName()));
                treeNode2.setLevel(1);
                for(int k = 0 ;k<mList.get(i).getSubs().get(j).getDangers().size();k++)
                {

                    TreeNode treeNode3 = new TreeNode(mList.get(i).getSubs().get(j).getDangers().get(k));
                    treeNode3.setLevel(-1);
                    treeNode2.addChild(treeNode3);
                }
                treeNode.addChild(treeNode2);
            }
            root.addChild(treeNode);

        }
    }
}
