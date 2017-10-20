package com.example.dell.jingdong;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import presenter.ChangePresenter;
import view.ChangeView;

public class ChangeActivity extends AppCompatActivity implements View.OnClickListener, ChangeView {

    private EditText et_nickname;
    private TextView tv_true;
    private ImageView fanhui;
    private ChangePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        initView();
        initData();
    }

    private void initData() {
        presenter = new ChangePresenter(this);

    }

    private void initView() {
        et_nickname = (EditText) findViewById(R.id.et_nickname);
        tv_true = (TextView) findViewById(R.id.tv_true);
        fanhui = (ImageView) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        tv_true.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tv_true:
                SharedPreferences sp = getSharedPreferences("uid", MODE_PRIVATE);
                int uid = sp.getInt("uid", 0);
                presenter.RequestChange(uid,et_nickname.getText().toString());
                finish();
                break;
           case R.id.fanhui:
                finish();
                break;
        }
    }

    @Override
    public void changeSuccessNickName(String code, final String msg) {
        if(this!=null)
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ChangeActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public void changeFailureNickName(String code, final String msg) {
        if(this!=null)
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ChangeActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void Filure(Call call, IOException e) {

    }
}
