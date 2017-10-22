package adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
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
import java.text.DecimalFormat;
import java.util.List;

import bean.ShopCartList;
import okhttp3.Call;
import presenter.DeletePresenter;
import presenter.ShopCartListPresentere;
import presenter.UpdateCartPresenter;
import view.DeleteView;
import view.ShopCartListView;
import view.UpdateCartView;
import zidingview.AmountView;

/**
 * Created by DELL on 2017/10/18.
 */

public class ZiRvAdapter extends RecyclerView.Adapter<ZiRvAdapter.ViewHolder> implements UpdateCartView, ShopCartListView {

    private Context context;
    private List<ShopCartList.DataBean.ListBean> data;

    private UpdateCartPresenter presenter;
    private int sum=0;
    private onRecyclerViewItemClick onRecyclerViewItemClick;

    private setSum setSum;
    private int uid;

    private TextView tv_sumprice;
    private final ShopCartListPresentere presentere;



    public ZiRvAdapter(Context context, List<ShopCartList.DataBean.ListBean> data,TextView tv_sumprice) {
        this.tv_sumprice=tv_sumprice;
        this.context = context;
        this.data = data;
        SharedPreferences sp = context.getSharedPreferences("uid",Context.MODE_PRIVATE);
        uid = sp.getInt("uid", 0);
        presentere = new ShopCartListPresentere(ZiRvAdapter.this);
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
        holder.gwc_price.setText("￥"+data.get(position).bargainPrice+"");
        String images = data.get(position).images;
        String[] img = images.split("\\|");
        Glide.with(context).load(img[0]).into(holder.imageView);
        holder.amout_view.setGoods_storage(50,data.get(position).num);
        holder.oneselect.setOnCheckedChangeListener(null);

        int selected = data.get(position).selected;
        if(selected==0)
        {
            holder.oneselect.setChecked(false);
            presenter.requestUpdateCart(uid, data.get(position).sellerid, data.get(position).pid,data.get(position).selected,data.get(position).num);
        }
        else
        {
            holder.oneselect.setChecked(true);
            sum++;
            setSum.getSum(sum);
            presenter.requestUpdateCart(uid, data.get(position).sellerid, data.get(position).pid,data.get(position).selected,data.get(position).num);
        }
        getsumprice();
        /**
         * 点击最里层的item点击事件
         */
        holder.oneselect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    data.get(position).selected=1;
                    sum++;
                }
                else
                {
                    data.get(position).selected=0;
                    sum--;
                }
                System.out.println("&&&&&&&&&&&&&&&&uid"+uid);
                presenter.requestUpdateCart(uid, data.get(position).sellerid, data.get(position).pid,data.get(position).selected,data.get(position).num);
                setSum.getSum(sum);
                getsumprice();

            }


        });
        holder.amout_view.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {

            /**
             * 自定义控件的点击事件
             * @param view
             * @param amount
             */
            @Override
            public void onAmountChange(View view, int amount) {
              //  Toast.makeText(context, "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
                data.get(position).num=amount;

                presenter.requestUpdateCart(uid,data.get(position).sellerid, data.get(position).pid,data.get(position).selected,amount);

                getsumprice();

            }
        });

     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             onRecyclerViewItemClick.onitemClick(holder.itemView,position);
         }
     });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public void setSetSum(ZiRvAdapter.setSum setSum) {
        this.setSum = setSum;
    }
    /**
     * 获取购物车的接口接口回调
     * @return
     */
    @Override
    public void onShopCartListSuccess(List<ShopCartList.DataBean> dataBean) {
        if(context!=null)
        {
            float finalSum=0;

            for (ShopCartList.DataBean bean : dataBean) {
                for (ShopCartList.DataBean.ListBean listBean : bean.list) {

                    if(listBean.selected==1)
                    {
                        finalSum+=listBean.num*listBean.bargainPrice;
                    }
                }
            }

            final float finalSum1 = finalSum;
            new Handler(context.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    DecimalFormat decimalFormat = new DecimalFormat("#0.00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
                    String price_num = decimalFormat.format(finalSum1);// format 返回的是字符串
                    tv_sumprice.setText("合计: ￥"+price_num +"");
                    System.out.println(")))))))))))))))))))))))))"+price_num);
                }
            });
        }
    }

    @Override
    public void onShopCartListFailure(String msg) {

    }

    @Override
    public void onFailure(Call call, IOException e) {

    }


    public interface setSum{
        void getSum(int sum);
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

    public void setOnRecyclerViewItemClick(ZiRvAdapter.onRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

    public interface onRecyclerViewItemClick{
        void onitemClick(View view, int position);
    }

    public void getsumprice(){
        presentere.requestShopCartList(uid);
    }

}
