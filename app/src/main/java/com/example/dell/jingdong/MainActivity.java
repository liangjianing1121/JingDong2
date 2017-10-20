package com.example.dell.jingdong;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.HeaderViewListAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = (int) msg.what;
            if(i==0)
            {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);

                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            private int i=3;
            @Override
            public void run() {
                i--;
                Message msg=Message.obtain();
                msg.what=i;
                handler.sendEmptyMessage(i);


            }
        };
        timer.schedule(task,1000,1000);
    }
}
