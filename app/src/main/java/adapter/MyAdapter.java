package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dell.jingdong.R;
import com.example.dell.jingdong.XiangqingActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import bean.XBanner;
import bean.XiangQing;

/**
 * Created by DELL on 2017/10/9.
 */

public class MyAdapter extends CommonAdapter<bean.XBanner.TuijianBean.ListBean> {
    public Context context;
    public int mlayoutId;
    private List<bean.XBanner.TuijianBean.ListBean> tuijianlist;
    private int item_list;

    public MyAdapter(Context context, int layoutId, List<bean.XBanner.TuijianBean.ListBean> tuijianlist) {
        super(context, layoutId, tuijianlist);
        this.context=context;
        this.mlayoutId=layoutId;
        this.tuijianlist=tuijianlist;
    }
    @Override
    protected void convert(ViewHolder holder, XBanner.TuijianBean.ListBean listBean, final int position) {

        holder.setText(R.id.tv_name,tuijianlist.get(position-4).title);
        holder.setText(R.id.tv_price,"￥"+tuijianlist.get(position-4).price);
        ImageView iv_shop = holder.getView(R.id.iv_shop);
        String images = tuijianlist.get(position-4).images;
        String[] img=images.split("\\|");
        Glide.with(context).load(img[0]).into(iv_shop);

       /* iv_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, XiangqingActivity.class);
                intent.putExtra("uid",tuijianlist.get(position).pid);
                context.startActivity(intent);
            }
        });*/


    }

    @Override
    public int getItemViewType(int position) {
        item_list = R.layout.item_list;
        return item_list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.createViewHolder(context,parent,mlayoutId);
        return viewHolder;

    }




    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        super.onViewHolderCreated(holder, itemView);
    }

    @Override
    public int getItemCount() {
        return tuijianlist.size();
    }

}
