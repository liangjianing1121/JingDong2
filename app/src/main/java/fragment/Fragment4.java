package fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.jingdong.R;

import java.io.IOException;
import java.util.List;

import adapter.ShopCartListAdapter;
import bean.ShopCartList;
import okhttp3.Call;
import presenter.ShopCartListPresentere;
import view.ShopCartListView;

/**
 * Created by DELL on 2017/10/9.
 */

public class Fragment4 extends Fragment implements ShopCartListView {

    private View view;
    private RecyclerView rc_gouwuche;
    private ShopCartListPresentere presentere;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment4, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }
    private void initData() {
        presentere = new ShopCartListPresentere(this);
        SharedPreferences sp = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        presentere.requestShopCartList(uid);

    }
    private void initView() {
        rc_gouwuche = view.findViewById(R.id.rc_gouwuche);
    }

    @Override
    public void onShopCartListSuccess(final List<ShopCartList.DataBean> dataBean) {
        if(getActivity()!=null)
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("****************databean"+dataBean.size());
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                    rc_gouwuche.setLayoutManager(linearLayoutManager);

                    ShopCartListAdapter adapter=new ShopCartListAdapter(getActivity(),dataBean);
                    rc_gouwuche.setAdapter(adapter);
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

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sp = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        presentere.requestShopCartList(uid);
    }
}
