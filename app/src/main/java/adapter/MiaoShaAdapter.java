package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.jingdong.R;
import com.example.dell.jingdong.SYXiangQingActivity;
import com.example.dell.jingdong.XiangqingActivity;

import java.util.List;

import bean.XBanner;

/**
 * Created by DELL on 2017/10/10.
 */

public class MiaoShaAdapter  extends  RecyclerView.Adapter<MiaoShaAdapter.ViewHolder> {

    private Context context;
    private List<bean.XBanner.MiaoshaBean.ListBeanX> miaoshaList;
    private View view;

    public MiaoShaAdapter(Context context, List<XBanner.MiaoshaBean.ListBeanX> miaoshaList) {
        this.context = context;
        this.miaoshaList = miaoshaList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.miaosha_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_prie.setText("￥"+miaoshaList.get(position).price);
        holder.tv_beginprice.setText("￥"+miaoshaList.get(position).bargainPrice);
        String images = miaoshaList.get(position).images;
        String[] img=images.split("\\|");
        Glide.with(context).load(img[0]).into(holder.iv_miaosha);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent=new Intent(context, XiangqingActivity.class);
                intent.putExtra("pid",miaoshaList.get(position).pid+"");
                intent.putExtra("b",0);
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return miaoshaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private  ImageView iv_miaosha;
        private  TextView tv_prie;
        private  TextView tv_beginprice;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_miaosha = itemView.findViewById(R.id.iv_miaosha);
            tv_prie = itemView.findViewById(R.id.tv_price);
            tv_beginprice = itemView.findViewById(R.id.tv_beginprice);
            tv_beginprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }



}
