package com.example.dell.jingdong;

import android.app.Presentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import bean.Login;
import okhttp3.Call;
import presenter.LoginPresenter;
import presenter.UserPresenter;
import view.LoginView;
import view.UserView;

public class LoginActivity2 extends AppCompatActivity implements View.OnClickListener, LoginView{

    private Button login;
    private LoginPresenter presenter;
    private EditText et_mobile;
    private EditText et_psd;
    private UserPresenter presenter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initView();
        initData();
    }

    private void initData() {
        presenter = new LoginPresenter(LoginActivity2.this,this);




    }
    private void initView() {
        TextView tv_zhuce = (TextView) findViewById(R.id.tv_zhuce);
        login = (Button) findViewById(R.id.login);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_psd = (EditText) findViewById(R.id.et_psd);


        login.setOnClickListener(this);
        tv_zhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tv_zhuce:
                Intent intent=new Intent(this,ZhuceActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                presenter.requestLoginn(et_mobile.getText().toString(),et_psd.getText().toString());

                break;
        }
    }

    @Override
    public void onLoginSuccess(String code, final String msg, final Login.DataBean data) {
        if(this!=null)
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity2.this, msg, Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getSharedPreferences("uid", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putInt("uid",data.uid).commit();
                    finish();
               }
            });
        }
    }

    @Override
    public void onLoginFaliure(String code, final String msg) {
        if(this!=null)
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity2.this, msg, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    @Override
    public void onNameError(String msg) {

    }

    @Override
    public void onPsdError(String msg) {

    }

    @Override
    public void onFaliure(Call call, IOException e) {

    }



}
