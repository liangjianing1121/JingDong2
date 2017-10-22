package com.example.dell.jingdong;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.OrderAdapter;
import adapter.OrderListAdapter;
import bean.OrderList;
import okhttp3.Call;
import presenter.OrderListPresenter;
import view.OrderListView;

public class OrderActivity extends AppCompatActivity implements OrderListView {

    private OrderListPresenter presenter;
    private RecyclerView zhifi1_rv;
    private ImageView fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        intData();
    }
    private void intData() {
        presenter = new OrderListPresenter(this);
        SharedPreferences sp =getSharedPreferences("uid", Context.MODE_PRIVATE);
        int uid = sp.getInt("uid",0);
        presenter.requestOrderList(uid);
    }

    private void initView() {
        zhifi1_rv = (RecyclerView) findViewById(R.id.zhifi1_rv);
        fanhui = (ImageView) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onOrderListsuccess(final List<OrderList.DataBean> data) {
        if(this!=null)
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(data.size()+"(5555555555555555555555)");
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(OrderActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                    zhifi1_rv.setLayoutManager(linearLayoutManager);
                    OrderListAdapter adapter=new OrderListAdapter(OrderActivity.this,data);
                    zhifi1_rv.setAdapter(adapter);
                }
            });
        }
    }
    @Override
    public void onOrderListFailure(final String msg) {
        if(this!=null)
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(OrderActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }


}
