package com.example.dell.jingdong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import presenter.ZhucePresenter;
import view.ZhuceView;

public class ZhuceActivity extends AppCompatActivity implements View.OnClickListener, ZhuceView {

    private ImageView iv_fanhui;
    private Button bt_zhuce;
    private EditText et_mobile;
    private EditText et_psd;
    private ZhucePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        initView();
        initData();
    }

    private void initData() {

        presenter = new ZhucePresenter(this,this);

    }

    private void initView() {
        iv_fanhui = (ImageView) findViewById(R.id.iv_fanhui);
        bt_zhuce = (Button) findViewById(R.id.bt_zhuce);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_psd = (EditText) findViewById(R.id.et_psd);

        iv_fanhui.setOnClickListener(this);
        bt_zhuce.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
             case R.id.iv_fanhui:
                 finish();
                 break;
            case R.id.bt_zhuce:
                presenter.requestZhuce(et_mobile.getText().toString(),et_psd.getText().toString());

                break;
        }
    }

    @Override
    public void onZhuceSuccess(final String code, final String msg) {
        if(this!=null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(code.equals("0"))
                    {
                        Toast.makeText(ZhuceActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                    else if(code.equals("1"))
                    {
                        Toast.makeText(ZhuceActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onZhuceFaliure(String code, String msg) {

    }

    @Override
    public void onFaliure(Call call, IOException e) {

    }
}
