package com.example.dell.jingdong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.XiangQingAdapter;
import fragment.XiangQingFragment1;
import fragment.XiangQingFragment2;
import fragment.XiangQingFragment3;
import okhttp3.Call;
import presenter.ShopCartPresenter;
import view.ShopCartView;

public class XiangqingActivity extends AppCompatActivity implements View.OnClickListener, ShopCartView {

    private List<Fragment> fragmentList;
    private TabLayout tablayout;
    private ViewPager xq_vp;
    private ImageView xq_back;
    private Button joinshapcar;
    private ShopCartPresenter presenter;
    private LinearLayout shapcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        initView();
        initData();
    }
    private void initData() {
        presenter = new ShopCartPresenter(this);
    }

    private void initView() {
        joinshapcar = (Button) findViewById(R.id.joinshapcar);

        tablayout = (TabLayout) findViewById(R.id.tablayout);
        xq_vp = (ViewPager) findViewById(R.id.xq_vp);
        tablayout.setupWithViewPager(xq_vp);

        xq_back = (ImageView) findViewById(R.id.xq_back);
        xq_back.setOnClickListener(this);
        joinshapcar.setOnClickListener(this);

        shapcar = (LinearLayout) findViewById(R.id.shapcar);

        shapcar.setOnClickListener(this);

        fragmentList = new ArrayList<>();
        XiangQingFragment1 fragment1=new XiangQingFragment1();
        XiangQingFragment2 fragment2=new XiangQingFragment2();
        XiangQingFragment3 fragment3=new XiangQingFragment3();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        System.out.println(fragmentList.size()+"*********************************");
        XiangQingAdapter adapter=new XiangQingAdapter(getSupportFragmentManager(),XiangqingActivity.this,fragmentList);
        xq_vp.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.xq_back:
                finish();
                break;
            case R.id.joinshapcar:
                SharedPreferences sp = getSharedPreferences("uid", MODE_PRIVATE);
                int uid = sp.getInt("uid", 0);
                Intent intent = getIntent();
                int pid = intent.getIntExtra("pid", 0);
                presenter.requestShopcart(uid,pid);
                break;
            case R.id.shapcar:

                Intent intent1=new Intent(XiangqingActivity.this,Main2Activity.class);
                intent1.putExtra("a",1);
                startActivity(intent1);
                break;
        }
    }
    @Override
    public void onShopSuccess(String code, final String msg) {
        if(this!=null)
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(XiangqingActivity.this, msg, Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    @Override
    public void onShopFailure(String code, final String msg) {
        if(this!=null)
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(XiangqingActivity.this, msg, Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }
}
