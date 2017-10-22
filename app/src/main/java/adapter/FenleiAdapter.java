package adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.jingdong.R;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.w3c.dom.Text;

import java.util.List;

import bean.Fenlei2;

/**
 * Created by DELL on 2017/10/10.
 */

public class FenleiAdapter extends  RecyclerView.Adapter<FenleiAdapter.ViewHolder>{

    private Context context;
    private List<Fenlei2> fenleilist;
    private View view;

    public FenleiAdapter(Context context, List<Fenlei2> fenleilist) {
        this.context = context;
        this.fenleilist = fenleilist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        view = LayoutInflater.from(context).inflate(R.layout.rv_item, null);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_name.setText(fenleilist.get(position).getName());
        Glide.with(context).load(fenleilist.get(position).getIcon()).into(holder.iv_icon);

    }


    @Override
    public int getItemCount() {
        return fenleilist.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_name;
        public ImageView iv_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_icon = itemView.findViewById(R.id.iv_icon);
        }
    }

}
