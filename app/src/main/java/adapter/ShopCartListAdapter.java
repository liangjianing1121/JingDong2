package adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dell.jingdong.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import bean.ShopCartList;
import okhttp3.Call;
import presenter.DeletePresenter;
import view.DeleteView;

/**
 * Created by DELL on 2017/10/18.
 */

public class ShopCartListAdapter extends RecyclerView.Adapter<ShopCartListAdapter.ViewHoler> implements DeleteView {
    private Context context;
    private View view;
    private List<ShopCartList.DataBean> data;
    private ongetSum ongetSum;
    private List<Boolean> list;
    private int allSelectCount;
    private TextView tv_sumprice;

    private onSum2 onSum2;
    public ShopCartListAdapter(Context context, List<ShopCartList.DataBean> data,TextView tv_sumprice) {
        this.tv_sumprice=tv_sumprice;
        this.context = context;
        this.data = data;
        list=new ArrayList<>();
        for (ShopCartList.DataBean dataBean : data) {
            list.add(false);

        }
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.rv_shopcartlist, null);
        ViewHoler holer=new ViewHoler(view);
        return holer;
    }

    @Override
    public void onBindViewHolder(final ViewHoler holder, final int position) {
        int a=0;
            for (ShopCartList.DataBean.ListBean listBean : data.get(position).list) {
                if(listBean.selected==1)
                {
                    a++;
                }
        }
        if(a==data.get(position).list.size())
        {
            holder.allselect.setChecked(true);
            list.set(position,true);
            getSelect();
        }
        else
        {
            holder.allselect.setChecked(false);
            list.set(position,false);
            getSelect();
        }

        holder.tv_sellername.setText(data.get(position).sellerName);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);

        holder.zi_rv.setLayoutManager(linearLayoutManager);
        ZiRvAdapter adapter=new ZiRvAdapter(context,data.get(position).list,tv_sumprice);
        holder.zi_rv.setAdapter(adapter);

        adapter.setSetSum(new ZiRvAdapter.setSum() {
            @Override
            public void getSum(int sum) {
                System.out.println("^^^^^^^^^^^^^^^^sum"+sum+"^^^^^^^^^^^^"+data.get(position).list.size());
                if(sum==data.get(position).list.size())
                {
                    holder.allselect.setChecked(true);

                    list.set(position,true);
                    getSelect();
                }
                else
                {
                    holder.allselect.setChecked(false);

                    list.set(position,false);
                    getSelect();
                }
            }
        });

        holder.allselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (ShopCartList.DataBean.ListBean listBean : data.get(position).list) {
                    if(holder.allselect.isChecked())
                    {
                        listBean.selected=1;
                    }
                    else{
                        listBean.selected=0;
                    }
                    notifyDataSetChanged();
            }
            }
        });

        //点击删除的接口回调
        adapter.setOnRecyclerViewItemClick(new ZiRvAdapter.onRecyclerViewItemClick() {
            @Override
            public void onitemClick(View view, final int curposition) {

                AlertDialog.Builder a=new AlertDialog.Builder(context);
                a.setMessage("确定删除吗");
                a.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DeletePresenter presenter=new DeletePresenter(ShopCartListAdapter.this);
                        SharedPreferences sp = context.getSharedPreferences("uid",Context.MODE_PRIVATE);
                        int uid = sp.getInt("uid", 0);
                        presenter.requestDelete(uid,data.get(position).list.get(curposition).pid);
                        data.get(position).list.remove(curposition);
                        if(data.get(position).list.size()==0)
                        {
                            data.remove(curposition);
                        }
                        notifyDataSetChanged();
                    }
                });
                a.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                a.create().show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onDeleteSuccess(String msg) {

    }

    @Override
    public void onDeleteFailure(String msg) {

    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    public class ViewHoler extends RecyclerView.ViewHolder{

        private final TextView tv_sellername;
        private final RecyclerView zi_rv;
        private final CheckBox allselect;

        public ViewHoler(View itemView) {
            super(itemView);
            tv_sellername = itemView.findViewById(R.id.tv_sellername);
            zi_rv = itemView.findViewById(R.id.zi_rv);
            allselect = itemView.findViewById(R.id.allselect);
        }
    }
    public void getSelect(){
        allSelectCount=0;
        for (Boolean aBoolean : list) {
                if(aBoolean){
                    allSelectCount++;
                }
        }
        ongetSum.getsum(allSelectCount);
    }
    public void setOngetSum(ShopCartListAdapter.ongetSum ongetSum) {
        this.ongetSum = ongetSum;
    }

    public interface ongetSum{
        void getsum(int sum);
    }

    public void setOnSum2(ShopCartListAdapter.onSum2 onSum2) {
        this.onSum2 = onSum2;
    }

    /**
     * 大的adapter定义接口
     */
    public interface onSum2{
        void onprice();
    }
}
