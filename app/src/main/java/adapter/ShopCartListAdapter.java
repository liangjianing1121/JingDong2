package adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.jingdong.R;

import java.util.List;

import bean.ShopCartList;

/**
 * Created by DELL on 2017/10/18.
 */

public class ShopCartListAdapter extends RecyclerView.Adapter<ShopCartListAdapter.ViewHoler>{
    private Context context;
    private View view;
    private List<ShopCartList.DataBean> data;

    public ShopCartListAdapter(Context context, List<ShopCartList.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.rv_shopcartlist, null);
        ViewHoler holer=new ViewHoler(view);
        return holer;
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        holder.tv_sellername.setText(data.get(position).sellerName);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);

        holder.zi_rv.setLayoutManager(linearLayoutManager);
        ZiRvAdapter adapter=new ZiRvAdapter(context,data.get(position).list);
        holder.zi_rv.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{

        private final TextView tv_sellername;
        private final RecyclerView zi_rv;

        public ViewHoler(View itemView) {
            super(itemView);
            tv_sellername = itemView.findViewById(R.id.tv_sellername);
            zi_rv = itemView.findViewById(R.id.zi_rv);
        }
    }

}
