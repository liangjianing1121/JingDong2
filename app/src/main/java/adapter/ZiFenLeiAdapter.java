package adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.jingdong.R;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import bean.ZiFenLei;

/**
 * Created by DELL on 2017/10/11.
 */

public class ZiFenLeiAdapter extends RecyclerView.Adapter<ZiFenLeiAdapter.ViewHolder>{
    private Context context;
    private List<ZiFenLei.DataBean> zifenleilist;


    public ZiFenLeiAdapter(Context context, List<ZiFenLei.DataBean> zifenleilist) {
        this.context = context;
        this.zifenleilist = zifenleilist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.zifenlei_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_zifenlei.setText(zifenleilist.get(position).name);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,3);
        holder.rv_zifenlei.setLayoutManager(gridLayoutManager);

        SanAdapter sanAdapter=new SanAdapter(context,zifenleilist.get(position).list);
        holder.rv_zifenlei.setAdapter(sanAdapter);
    }
    @Override
    public int getItemCount() {
        return zifenleilist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        private final TextView tv_zifenlei;
        private final RecyclerView rv_zifenlei;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_zifenlei = itemView.findViewById(R.id.tv_zifenlei);
            rv_zifenlei = itemView.findViewById(R.id.rv_zifenlei);
        }
    }
}
