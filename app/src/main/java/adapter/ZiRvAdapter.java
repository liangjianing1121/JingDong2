package adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.jingdong.R;

import java.io.IOException;
import java.util.List;

import bean.ShopCartList;
import okhttp3.Call;
import presenter.UpdateCartPresenter;
import view.UpdateCartView;
import zidingview.AmountView;

/**
 * Created by DELL on 2017/10/18.
 */

public class ZiRvAdapter extends RecyclerView.Adapter<ZiRvAdapter.ViewHolder> implements UpdateCartView{

    private Context context;
    private List<ShopCartList.DataBean.ListBean> data;
    private int isselect;
    private int uid;
    private int pid;
    private int sellerid;
    private UpdateCartPresenter presenter;

    public ZiRvAdapter(Context context, List<ShopCartList.DataBean.ListBean> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.zirv_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        presenter = new UpdateCartPresenter(ZiRvAdapter.this);
        holder.gwc_name.setText(data.get(position).title);
        holder.gwc_price.setText("￥"+data.get(position).price+"");
        String images = data.get(position).images;
        String[] img = images.split("\\|");
        Glide.with(context).load(img[0]).into(holder.imageView);
        holder.amout_view.setGoods_storage(50,data.get(position).num);

        int selected = data.get(position).selected;
        if(selected==0)
        {
            holder.oneselect.setChecked(true);
        }
        else
        {
            holder.oneselect.setChecked(false);
        }
        holder.oneselect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    isselect=0;
                    System.out.println("----------"+isselect);
                }
                else
                {
                    isselect=1;
                    System.out.println("----------------"+isselect);
                }
                presenter.requestUpdateCart(uid, sellerid, pid,isselect,data.get(position).num);
            }
        });
        holder.amout_view.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {

            @Override
            public void onAmountChange(View view, int amount) {
              //  Toast.makeText(context, "Amount=>  " + amount, Toast.LENGTH_SHORT).show();

                SharedPreferences sp = context.getSharedPreferences("uid",Context.MODE_PRIVATE);
                uid = sp.getInt("uid", 0);
                sellerid = data.get(position).sellerid;
                pid = data.get(position).pid;
                presenter.requestUpdateCart(uid, sellerid, pid,isselect,amount);
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void UpdateSuccess(String code, String msg) {
    }

    @Override
    public void UpdateFailure(String code, String msg) {

    }
    @Override
    public void onFilure(Call call, IOException e) {

    }
    public class  ViewHolder extends RecyclerView.ViewHolder{

        private final AmountView amout_view;
        private final CheckBox oneselect;
        private final ImageView imageView;
        private final TextView gwc_name;
        private final TextView gwc_price;

        public ViewHolder(View itemView) {
            super(itemView);
            oneselect = itemView.findViewById(R.id.oneselect);
            amout_view = itemView.findViewById(R.id.amount_view);
            imageView = itemView.findViewById(R.id.imageView);
            gwc_name = itemView.findViewById(R.id.gwc_name);
            gwc_price = itemView.findViewById(R.id.gwc_price);
        }
    }
}
