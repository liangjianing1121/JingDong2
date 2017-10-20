package com.example.dell.jingdong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import fragment.Fragment1;
import fragment.Fragment2;
import fragment.Fragment3;
import fragment.Fragment4;
import fragment.Fragment5;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ImageView iv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment1()).commit();
        iv1.setImageResource(R.drawable.shouyehong);
    }

    private void initView() {
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        iv5 = (ImageView) findViewById(R.id.iv5);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.iv1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment1()).commit();
                iv1.setImageResource(R.drawable.shouyehong);
                iv2.setImageResource(R.drawable.fenleibai);
                iv3.setImageResource(R.drawable.faxian);
                iv4.setImageResource(R.drawable.gouwuchebai);
                iv5.setImageResource(R.drawable.wodebai);
                break;
            case R.id.iv2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment2()).commit();
                iv1.setImageResource(R.drawable.shouyebai);
                iv2.setImageResource(R.drawable.fenleihong);
                iv3.setImageResource(R.drawable.faxian);
                iv4.setImageResource(R.drawable.gouwuchebai);
                iv5.setImageResource(R.drawable.wodebai);
                break;
            case R.id.iv3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment3()).commit();
                iv1.setImageResource(R.drawable.shouyebai);
                iv2.setImageResource(R.drawable.fenleibai);
                iv3.setImageResource(R.drawable.shuxin);
                iv4.setImageResource(R.drawable.gouwuchebai);
                iv5.setImageResource(R.drawable.wodebai);
                break;
            case R.id.iv4:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment4()).commit();
                iv1.setImageResource(R.drawable.shouyebai);
                iv2.setImageResource(R.drawable.fenleibai);
                iv3.setImageResource(R.drawable.faxian);
                iv4.setImageResource(R.drawable.gouwuchehong);
                iv5.setImageResource(R.drawable.wodebai);
                break;
            case R.id.iv5:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Fragment5()).commit();
                iv1.setImageResource(R.drawable.shouyebai);
                iv2.setImageResource(R.drawable.fenleibai);
                iv3.setImageResource(R.drawable.faxian);
                iv4.setImageResource(R.drawable.gouwuchebai);
                iv5.setImageResource(R.drawable.wodehong);
                break;
        }

    }
}
