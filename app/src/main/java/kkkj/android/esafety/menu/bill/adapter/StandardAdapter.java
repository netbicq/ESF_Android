package kkkj.android.esafety.menu.bill.adapter;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import kkkj.android.esafety.R;
import kkkj.android.esafety.bean.SubStandard;
public class StandardAdapter extends BaseQuickAdapter<SubStandard, BaseViewHolder> {
    public StandardAdapter(int layoutResId, @Nullable List<SubStandard> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubStandard item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_Controls,item.getControls());
        helper.setText(R.id.tv_Engineering,item.getEngineering());
        helper.setText(R.id.tv_Accident,item.getAccident());
        helper.setText(R.id.tv_Individual,item.getIndividual());
    }
}
