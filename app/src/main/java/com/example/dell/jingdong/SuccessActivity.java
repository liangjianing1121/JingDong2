package com.example.dell.jingdong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;

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
    private Button exit;


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
        exit = (Button) findViewById(R.id.exit);

        rl.setOnClickListener(this);
        iv_fanhui.setOnClickListener(this);
        exit.setOnClickListener(this);
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
            case  R.id.exit:
                SharedPreferences sp = getSharedPreferences("uid", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.clear().commit();
                Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();
                finish();
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
                    SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.iv_head);
                    draweeView.setImageURI(icon);
                    //Glide.with(SuccessActivity.this).load(icon).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv_head);

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
