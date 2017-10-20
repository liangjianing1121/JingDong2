package com.example.dell.jingdong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.XiangQing;
import okhttp3.Call;
import presenter.XiangQingPresenterr;
import view.XiangQingView;

public class SYXiangQingActivity extends AppCompatActivity implements XiangQingView {


    private XBanner banner;
    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_subhead;
    private List<String> imglist;
    private TextView tv_productNums;
    private TextView tv_score;
    private TextView tv_sellerid;
    private TextView tv_description;
    private XiangQingPresenterr presenterr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syxiang_qing);
        initView();
        initData();


    }
    private void initData() {
        Intent intent =getIntent();
        int pid = intent.getIntExtra("pid", 0);
        System.out.println("%%%%%%%%%%%%%"+pid);
        presenterr = new XiangQingPresenterr(this);
        presenterr.requestXiangQing(pid);

    }

    private void initView() {
        banner = (XBanner) findViewById(R.id.banner);
        tv_title = (TextView) findViewById(R.id.tv_title2);
        tv_price = (TextView) findViewById(R.id.tv_price2);
        tv_subhead = (TextView) findViewById(R.id.tv_subhead);
        tv_productNums = (TextView) findViewById(R.id.tv_productNums);
        tv_score = (TextView) findViewById(R.id.tv_score);
        tv_sellerid = (TextView) findViewById(R.id.tv_sellerid);
        tv_description = (TextView) findViewById(R.id.tv_description);
    }

    @Override
    public void onXiangQingSuccess(final XiangQing.DataBean data, final XiangQing.SellerBean seller) {
        if(this!=null)
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    imglist = new ArrayList<>();
                    String images = data.images;
                    String[] img = images.split("\\|");
                    for (int i = 0; i <img.length; i++) {
                        imglist.add(img[i]);
                    }

                    banner.setData(imglist,null);
                    banner.setPoinstPosition(XBanner.CENTER);

                    banner.setmAdapter(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, View view, int position) {
                            Glide.with(SYXiangQingActivity.this).load(imglist.get(position)).into((ImageView) view);
                        }
                    });
                    tv_title.setText(data.title);
                    tv_subhead.setText(data.subhead);
                    tv_price.setText("￥"+data.price+"");
                    tv_productNums.setText(seller.productNums+"");
                    tv_score.setText(seller.score+"");
                    tv_sellerid.setText(seller.sellerid+"");
                    tv_description.setText(seller.description);

                }
            });
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }
    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
