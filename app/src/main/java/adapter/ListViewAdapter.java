package adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
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
    private int index;
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
        ViewHolder holder=null;
        if(view==null) {
            holder=new ViewHolder();
            view = View.inflate(context, R.layout.lv_item, null);
            holder.tv_fenlei =view.findViewById(R.id.tv_fenlei);
           // holder.tv_fenlei.setSelected(true);
            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }
        holder.tv_fenlei.setText(fenleilist.get(i).name);
        return view;
    }


   public class ViewHolder{
       TextView tv_fenlei;

   }
}
