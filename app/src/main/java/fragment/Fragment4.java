package fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.health.UidHealthStats;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dell.jingdong.R;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import adapter.ShopCartListAdapter;
import bean.ShopCartList;
import okhttp3.Call;
import presenter.CreaterOrderPresenter;
import presenter.ShopCartListPresentere;
import view.CreateOrderView;
import view.ShopCartListView;

/**
 * Created by DELL on 2017/10/9.
 */

public class Fragment4 extends Fragment implements ShopCartListView, View.OnClickListener, CreateOrderView {

    private View view;
    private RecyclerView rc_gouwuche;
    private ShopCartListPresentere presentere;
    private CheckBox allselect;
    private ShopCartListAdapter adapter;
    private TextView tv_sumprice;
    private Button pay;
    private int uid;
    private CreaterOrderPresenter presenter;

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
        presenter = new CreaterOrderPresenter(this);
    }
    private void initData() {
        presentere = new ShopCartListPresentere(this);
        SharedPreferences sp = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
        final int uid = sp.getInt("uid", 0);
        presentere.requestShopCartList(uid);

    }
    private void initView() {
        rc_gouwuche = view.findViewById(R.id.rc_gouwuche);
        allselect = view.findViewById(R.id.allselect);
        tv_sumprice = view.findViewById(R.id.tv_sumprice);
        pay = view.findViewById(R.id.pay);
        pay.setOnClickListener(this);
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

                    adapter = new ShopCartListAdapter(getActivity(),dataBean,tv_sumprice);
                    rc_gouwuche.setAdapter(adapter);
                    adapter.setOngetSum(new ShopCartListAdapter.ongetSum() {
                        @Override
                        public void getsum(int sum) {
                            if(sum==dataBean.size())
                            {
                                allselect.setChecked(true);
                            }
                            else
                            {
                                allselect.setChecked(false);
                            }
                        }
                    });
                }
            });
        }
        allselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (ShopCartList.DataBean bean : dataBean) {
                    for (ShopCartList.DataBean.ListBean listBean : bean.list) {
                        if(allselect.isChecked())
                        {
                            listBean.selected=1;
                        }
                        else
                        {
                            listBean.selected=0;
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });


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
        uid = sp.getInt("uid", 0);
        presentere.requestShopCartList(uid);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.pay:
                String s = tv_sumprice.getText().toString();
                String[] s2 = s.split("￥");
                System.out.println(tv_sumprice.getText().toString()+"价格++++++++++++");

                presenter.requestCreateOrder(uid,s2[1]);
                break;
        }

    }

    @Override
    public void onCreateSuccess(final String msg) {
        if(getActivity()!=null)
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();


                }
            });
        }
    }

    @Override
    public void onCreatFailure(final String msg) {
        if(getActivity()!=null)
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void Failure(Call call, IOException e) {

    }
}
