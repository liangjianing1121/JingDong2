package adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.jingdong.R;

import java.io.IOException;
import java.util.List;

import bean.OrderList;
import okhttp3.Call;
import pay.PayDemoActivity;
import presenter.QuXiaoPresenter;
import view.QuXiaoView;

/**
 * Created by DELL on 2017/10/22.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> implements QuXiaoView {

    private Context context;
    List<OrderList.DataBean> data;
    private View view;

    public OrderListAdapter(Context context, List<OrderList.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.order_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_id.setText(data.get(position).orderid+"");
        holder.tv_order_price.setText(data.get(position).price+"");

        if(data.get(position).status==0)
        {
            holder.tv_status.setText("待支付");
        }
        else if(data.get(position).status==1)
        {
            holder.tv_status.setText("已支付");
        }
        else
        {
            holder.tv_status.setText("已取消");
        }

        holder.btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuXiaoPresenter presenter=new QuXiaoPresenter(OrderListAdapter.this);
                SharedPreferences sp = context.getSharedPreferences("uid", Context.MODE_PRIVATE);
                int uid = sp.getInt("uid", 0);
                presenter.requestQuXiao(uid,2,data.get(position).orderid);
                holder.iv_over.setImageResource(R.drawable.quxiao);
                holder.iv_over.setVisibility(View.VISIBLE);
                holder.tv_status.setText("已取消");

            }
        });
        holder.btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuXiaoPresenter presenter=new QuXiaoPresenter(OrderListAdapter.this);
                SharedPreferences sp = context.getSharedPreferences("uid", Context.MODE_PRIVATE);
                int uid = sp.getInt("uid", 0);
                presenter.requestQuXiao(uid,1,data.get(position).orderid);


                Intent intent=new Intent(context, PayDemoActivity.class);
                context.startActivity(intent);

                holder.iv_over.setImageResource(R.drawable.over);
                holder.iv_over.setVisibility(View.VISIBLE);
                holder.tv_status.setText("已支付");


            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onQuXiaoSuccess(final String msg) {
        if(context!=null)
        {
            new Handler(context.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                }
            });



        }
    }

    @Override
    public void onQuXiaoFailure(String msg) {

    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_id;
        private final TextView tv_order_price;
        private final Button btn_quxiao;
        private final Button btn_buy;
        private final TextView tv_status;
        private final ImageView iv_over;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_order_price = itemView.findViewById(R.id.tv_order_price);
            btn_quxiao = itemView.findViewById(R.id.btn_quxiao);
            btn_buy = itemView.findViewById(R.id.btn_buy);
            tv_status = itemView.findViewById(R.id.tv_status);
            iv_over = itemView.findViewById(R.id.iv_over);
        }
    }



}
