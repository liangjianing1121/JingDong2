package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.jingdong.R;

import java.util.List;

import bean.Fenlei;
import fragment.Fragment2;

/**
 * Created by DELL on 2017/10/10.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Fenlei.DataBean> fenleilist;

    public ListViewAdapter(Context context, List<Fenlei.DataBean> fenleilist) {
        this.context = context;
        this.fenleilist = fenleilist;
    }

    @Override
    public int getCount() {
        return fenleilist.size();
    }

    @Override
    public Object getItem(int i) {
        return fenleilist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null) {
            view = View.inflate(context, R.layout.lv_item, null);
            TextView tv_fenlei = view.findViewById(R.id.tv_fenlei);
            //tv_fenlei.setSelected(true);
            tv_fenlei.setText(fenleilist.get(i).name);
            if(i== Fragment2.position)
            {
                tv_fenlei.setTextColor(Color.RED);
            }
            else
            {
                tv_fenlei.setTextColor(Color.BLACK);
            }

            notifyDataSetChanged();
        }
        return view;
    }
}
