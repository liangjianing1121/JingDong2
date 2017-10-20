package com.example.dell.jingdong;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import adapter.ShopListAdapter;
import adapter.ShopListAdapter2;
import bean.Shop;
import okhttp3.Call;
import presenter.ShopListPresenter;
import presenter.ShopPresenter;
import view.ShopView;

public class Shop2Activity extends AppCompatActivity implements ShopView, View.OnClickListener {

    private RecyclerView rv;
    private ImageView iv_change;
    private ImageView iv_fanhui;
    private TextView tv_xiaoliang;
    private TextView tv_price;
    private TextView tv_zonghe;
    private boolean geshi;
    private ShopListAdapter2 adapter;
    private String query;
    private ShopPresenter presenter;
    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop2);
        initView();
        initData();
    }
    private void initData() {
        Intent intent = getIntent();
        query = intent.getStringExtra("query");

        geshi=true;
        presenter = new ShopPresenter(this);
        presenter.requestShop(query,1,0);
    }
    private void initView() {

        rv = (RecyclerView) findViewById(R.id.rv);
        iv_change = (ImageView) findViewById(R.id.iv_change);
        iv_fanhui = (ImageView) findViewById(R.id.iv_fanhui);
        tv_xiaoliang = (TextView) findViewById(R.id.tv_xiaoliang);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_zonghe = (TextView) findViewById(R.id.tv_zonghe);
        rl = (RelativeLayout) findViewById(R.id.rl);
        rl.setOnClickListener(this);
        tv_price.setOnClickListener(this);
        iv_fanhui.setOnClickListener(this);
        tv_xiaoliang.setOnClickListener(this);
        tv_zonghe.setOnClickListener(this);
        iv_change.setOnClickListener(this);
    }

    @Override
    public void onShopSuccess(String code, String msg, int page, final List<Shop.DataBean> data) {
        if(this!=null)
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(geshi){
                        LinearLayoutManager lm=new LinearLayoutManager(Shop2Activity.this);
                        rv.setLayoutManager(lm);
                    }else{
                        GridLayoutManager gm=new GridLayoutManager(Shop2Activity.this,2);
                        rv.setLayoutManager(gm);
                    }

                    if(adapter==null){
                        adapter = new ShopListAdapter2(Shop2Activity.this,data,geshi);
                    }else{
                        adapter.setgeshi(geshi);
                        adapter.notifyDataSetChanged();
                    }
                    rv.setAdapter(adapter);
                }
            });
        }
    }

    @Override
    public void onShopFailure(String code, String msg) {

    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_zonghe:
                tv_xiaoliang.setTextColor(Color.rgb(153,153,153));
                tv_price.setTextColor(Color.rgb(153,153,153));
                tv_zonghe.setTextColor(Color.RED);
                adapter=null;
                presenter.requestShop(query,1,0);

                break;
            case R.id.tv_xiaoliang:
                adapter=null;
                tv_xiaoliang.setTextColor(Color.RED);
                tv_price.setTextColor(Color.rgb(153,153,153));
                tv_zonghe.setTextColor(Color.rgb(153,153,153));

                presenter.requestShop(query,1,1);

                break;
            case R.id.tv_price:
                tv_price.setTextColor(Color.RED);
                tv_xiaoliang.setTextColor(Color.rgb(153,153,153));
                tv_zonghe.setTextColor(Color.rgb(153,153,153));
                adapter=null;
                System.out.println("***************开始"+adapter);
                presenter.requestShop(query,1,2);
                break;
            case R.id.iv_change:
                if(geshi)
                {
                    iv_change.setImageResource(R.drawable.kind_liner);
                    geshi=false;
                    presenter.requestShop(query,1,0);
                   /* LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ShopActivity.this);
                    rv.setLayoutManager(linearLayoutManager);
                    adapter = new ShopListAdapter(ShopActivity.this,data);
                    rv.setAdapter(adapter);
*/
                }
                else
                {
                    iv_change.setImageResource(R.drawable.kind_grid);
                    geshi=true;
                    presenter.requestShop(query,1,0);
                   /* GridLayoutManager gridLayoutManager=new GridLayoutManager(ShopActivity.this,2);
                    rv.setLayoutManager(gridLayoutManager);
                    adapter = new ShopListAdapter(ShopActivity.this,data);
                    rv.setAdapter(adapter);*/
                }
                break;
            case R.id.rl:
                Intent intent=new Intent(Shop2Activity.this,SousuoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
