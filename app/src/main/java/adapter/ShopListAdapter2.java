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
import com.example.dell.jingdong.XiangqingActivity;

import java.util.List;

import bean.Shop;
import bean.ShopList;

/**
 * Created by DELL on 2017/10/16.
 */

public class ShopListAdapter2 extends RecyclerView.Adapter<ShopListAdapter2.ViewHolder>{

    private Context context;
    private List<Shop.DataBean> data;
    private View view;

    private boolean leixing;
    public ShopListAdapter2(Context context, List<Shop.DataBean> data,boolean leixing) {
        this.leixing=leixing;
        this.context = context;
        this.data = data;
    }

    @Override
    public ShopListAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (leixing) {
            view = View.inflate(context, R.layout.rv_shop_item, null);
        }
        else
        {
            view = View.inflate(context, R.layout.rv_shop_item2, null);
        }
        ShopListAdapter2.ViewHolder holder = new ShopListAdapter2.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_price.setText("￥"+data.get(position).price+"");
        holder.tv_title.setText(data.get(position).title);
        String images = data.get(position).images;
        String[] img = images.split("\\|");
        Glide.with(context).load(img[0]).into(holder.iv);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, XiangqingActivity.class);
                intent.putExtra("pid",data.get(position).pid);
                context.startActivity(intent);
            }
        });
    }

    public void setgeshi(boolean geshi){
        leixing=geshi;

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv;
        private final TextView tv_title;
        private final TextView tv_price;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
