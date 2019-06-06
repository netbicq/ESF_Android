package kkkj.android.esafety.menu.temptask.view;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.CSub;
import kkkj.android.esafety.menu.temptask.adapter.CSubAdapter;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.mvpInterface.MvpPresenter;

public class ChooseSubjectActivity extends MvpBaseActivity {

    List<CSub> mList;
    CSubAdapter adapter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @Override
    protected int getLayout() {
        return R.layout.activity_choose_subject;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_right.setText("确定");
        action_bar_title.setText("选择风控项");
        mList = new ArrayList<>();
        mList = (List<CSub>) getIntent().getSerializableExtra("CSubs");
        if(mList!=null){

        }
        for(int i = 0 ;i<mList.size();i++)
        {
            if(mList.get(i).getSubs().size()>0)
            {
                for(int j = 0 ;j<mList.get(i).getSubs().size();j++)
                {
                    mList.get(i).getSubs().get(j).setSubTypeID(mList.get(i).getSubTypeID());
                }
            }
            else {
                mList.remove(i);
            }
        }
        adapter = new CSubAdapter(R.layout.item_choose_subject,mList);

        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));



//        GetPicModel data = new GetPicModel();
//        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), firstFrame, null, null));
//        data.setImagePath(getRealPathFromUri(mContext, uri));
//        data.setType(1);
//        data.setMp4Path(url);
//        Intent intent = new Intent();
//        intent.putExtra("result", data);
//        setResult(Activity.RESULT_OK, intent);
//        finish();

    }
}
