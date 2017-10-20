package com.example.dell.jingdong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.IOException;

import bean.User;
import okhttp3.Call;
import presenter.UserPresenter;
import view.UserView;

public class SuccessActivity extends AppCompatActivity implements View.OnClickListener, UserView {

    private ImageView iv_fanhui;
    private RelativeLayout rl;
    private ImageView iv_head;
    private TextView tv_user;


    @Override
    protected void onResume() {
        super.onResume();
        UserPresenter presenter=new UserPresenter(this,this);
        SharedPreferences sp = getSharedPreferences("uid", MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        presenter.requestUser(uid);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        initView();
        initData();
    }

    private void initData() {
        UserPresenter presenter=new UserPresenter(this,this);
        SharedPreferences sp = getSharedPreferences("uid", MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        presenter.requestUser(uid);

    }

    private void initView() {

        iv_fanhui = (ImageView) findViewById(R.id.iv_fanhui);
        rl = (RelativeLayout) findViewById(R.id.rl);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        tv_user = (TextView) findViewById(R.id.tv_user);

        rl.setOnClickListener(this);
        iv_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {

            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.rl:
                Intent intent=new Intent(this,UserActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getUserSuccess(final User.DataBean data) {
        if(this!=null)
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String icon = data.icon;
                    Glide.with(SuccessActivity.this).load(icon).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv_head);


                    tv_user.setText(data.nickname);

                }
            });


        }

    }

    @Override
    public void getUserFailure(String msg) {

    }

    @Override
    public void onFailure(Call call, IOException e) {

    }
}
