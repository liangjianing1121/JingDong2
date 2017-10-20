package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.jingdong.R;
import com.example.dell.jingdong.ShopActivity;

import java.util.List;

import bean.ZiFenLei;

/**
 * Created by DELL on 2017/10/11.
 */

public class SanAdapter  extends RecyclerView.Adapter<SanAdapter.ViewHolder>{
    private Context context;
    private List<ZiFenLei.DataBean.ListBean> zifenleilist;
    private View view;

    public SanAdapter(Context context, List<ZiFenLei.DataBean.ListBean> zifenleilist) {
        this.context = context;
        this.zifenleilist = zifenleilist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.rv_item2, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tv_san.setText(zifenleilist.get(position).name);
        Glide.with(context).load(zifenleilist.get(position).icon).into(holder.iv_san);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ShopActivity.class);
                intent.putExtra("pscid",zifenleilist.get(position).pscid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return zifenleilist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_san;
        private final ImageView iv_san;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_san = itemView.findViewById(R.id.tv_san);
            iv_san = itemView.findViewById(R.id.iv_san);
        }


    }
}
