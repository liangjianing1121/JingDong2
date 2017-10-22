package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.jingdong.R;
import com.stx.xhb.xbanner.XBanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.XiangQing;
import okhttp3.Call;
import presenter.XiangQingPresenterr;
import view.XiangQingView;


/**
 * Created by DELL on 2017/10/17.
 */

public class XiangQingFragment1 extends Fragment implements XiangQingView,XBanner.XBannerAdapter{

    private View view;
    private XBanner banner;
    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_subhead;
    private List<String> imglist;
    private TextView tv_productNums;
    private TextView tv_score;
    private TextView tv_sellerid;
    private TextView tv_description;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.xiangqing_fragment1, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();

    }

    private void initData() {
        Intent intent = getActivity().getIntent();
        int pid = intent.getIntExtra("pid", 0);
        System.out.println("%%%%%%%%%%%%%"+pid);
        XiangQingPresenterr presenterr=new XiangQingPresenterr(this);
        presenterr.requestXiangQing(pid);

    }

    private void initView() {
        banner = view.findViewById(R.id.banner);
        tv_title = view.findViewById(R.id.tv_title2);
        tv_price = view.findViewById(R.id.tv_price2);
        tv_subhead = view.findViewById(R.id.tv_subhead);
        tv_productNums = view.findViewById(R.id.tv_productNums);
        tv_score = view.findViewById(R.id.tv_score);
        tv_sellerid = view.findViewById(R.id.tv_sellerid);
        tv_description = view.findViewById(R.id.tv_description);

    }


    @Override
    public void onXiangQingSuccess(final XiangQing.DataBean data, final XiangQing.SellerBean seller) {
        if(getActivity()!=null)
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   // System.out.println("%%%%%%%%%%%%%%"+data.title);

                    if(data!=null){


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
                                Glide.with(getActivity()).load(imglist.get(position)).into((ImageView) view);
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
    public void loadBanner(XBanner banner, View view, int position) {
        Glide.with(getActivity()).load(imglist.get(position)).into((ImageView) view);
    }
}
