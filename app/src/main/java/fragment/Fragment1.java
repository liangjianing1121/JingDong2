package fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.jingdong.Main2Activity;
import com.example.dell.jingdong.R;
import com.example.dell.jingdong.SousuoActivity;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.MiaoShaAdapter;
import adapter.MyAdapter;
import adapter.VpAdapter;
import bean.Fenlei;
import bean.Fenlei2;
import okhttp3.Call;
import presenter.MainPresenter;
import view.FenleiView;
import view.MainView;

/**
 * Created by DELL on 2017/10/9.
 */

public class Fragment1  extends Fragment implements MainView, View.OnClickListener {

    private View view;
    private SuperSwipeRefreshLayout superlayout;
    private RecyclerView recyclerView;
    private MyAdapter adapter;

    private int mDistanceY;
    private Toolbar toolbar;
    private XBanner banner;
    private List<String> vplist;
    private List<String> titles;

    private ViewPager vp;
    private List<Fragment> fragments;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private RecyclerView miaoshaRecycler;
    private List<bean.XBanner.MiaoshaBean.ListBeanX> miaoshalist;
    private LinearLayoutManager mLayoutManager2;
    private View miaosha;
    private List<bean.XBanner.TuijianBean.ListBean> tuijianlist;
    private View viewpager;
    private View fenlei;
    private ImageView iv_saoyisao;
    private TextView tv_soayisao;
    private ImageView iv_xiaoxi;
    private TextView tv_xiaoxi;
    private TextView tvMinute;
    private TextView tvHour;
    private TextView tvSecond;
    private EditText et;
    private View tu;

    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;

    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                computeTime();
                if (mHour<10){
                    tvHour.setText("0"+mHour+"");
                }else {
                    tvHour.setText("0"+mHour+"");
                }
                if (mMin<10){
                    tvMinute.setText("0"+mMin+"");
                }else {
                    tvMinute.setText(mMin+"");
                }
                if (mSecond<10){
                    tvSecond.setText("0"+mSecond+"");
                }else {
                    tvSecond.setText(mSecond+"");
                }
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.fragment1, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        miaoshalist=new ArrayList<>();
        tuijianlist = new ArrayList<>();
        initView();
        jianting();
        //initData();
        startRun();
    }



    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }
    private void jianting() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //滑动的距离
                mDistanceY += dy;
                //toolbar的高度
                int toolbarHeight = toolbar.getBottom();

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    toolbar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    //将标题栏的颜色设置为完全不透明状态
                    toolbar.setBackgroundResource(R.color.widowColor);
                    iv_saoyisao.setImageResource(R.drawable.saiyisaohei);
                    tv_soayisao.setTextColor(Color.BLACK);
                    iv_xiaoxi.setImageResource(R.drawable.xinxihei);
                    tv_xiaoxi.setTextColor(Color.BLACK);
                }
            }
        });

        superlayout.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        superlayout.setRefreshing(false);
                    }
                }, 500);
            }
            @Override
            public void onPullDistance(int distance) {
                if (distance > 0) {
                    toolbar.setVisibility(View.GONE);
                } else {
                    toolbar.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onPullEnable(boolean enable) {
            }
        });
    }

    private void initData() {
        GridLayoutManager mLayoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(mLayoutManager);

        System.out.println("================"+tuijianlist.size());
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);

        fragments = new ArrayList<>();
        fragments.add(new FenleiFragment());
        fragments.add(new FenleiFragment2());
        VpAdapter vpAdapter=new VpAdapter(getFragmentManager(),getActivity(),fragments);
        vp.setAdapter(vpAdapter);

        //添加第一个头部
        mHeaderAndFooterWrapper.addHeaderView(viewpager);
        //添加第二个头部
        mHeaderAndFooterWrapper.addHeaderView(tu);
        //添加第三个头部
        mHeaderAndFooterWrapper.addHeaderView(fenlei);

        //添加第四个头布局
        mHeaderAndFooterWrapper.addHeaderView(miaosha);
        mHeaderAndFooterWrapper.notifyDataSetChanged();
        recyclerView.setAdapter(mHeaderAndFooterWrapper);
    }

    private void initView() {
        superlayout = view.findViewById(R.id.superlayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        toolbar = view.findViewById(R.id.toolbar);
        iv_saoyisao = view.findViewById(R.id.iv_saoyisao);
        tv_soayisao = view.findViewById(R.id.tv_saoyisao);
        iv_xiaoxi = view.findViewById(R.id.iv_xiaoxi);
        tv_xiaoxi = view.findViewById(R.id.tv_xiaoxi);
        et = view.findViewById(R.id.et);




        et.setFocusable(false);
        et.setOnClickListener(this);

        ( (AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        MainPresenter presenter=new MainPresenter(getContext(),this);
        presenter.getdata();

        //第一个头部布局
        viewpager = LayoutInflater.from(getActivity()).inflate(R.layout.top_viewpager, null);
        banner = viewpager.findViewById(R.id.banner);

        //第三个头部布局
        tu = LayoutInflater.from(getActivity()).inflate(R.layout.tu, null);

        //第二个头部布局
        fenlei = LayoutInflater.from(getActivity()).inflate(R.layout.fenlei, null);
        vp = fenlei.findViewById(R.id.vp);
        //第四个头部布局
        miaosha = LayoutInflater.from(getActivity()).inflate(R.layout.miaosha, null);
        tvMinute = miaosha.findViewById(R.id.tv_minute);
        tvHour = miaosha.findViewById(R.id.tv_hour);
        tvSecond = miaosha.findViewById(R.id.tv_second);



    }

    @Override
    public void setXbanner(final List<bean.XBanner.DataBean> data) {

        if(getActivity()!=null)
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    vplist = new ArrayList<>();

                    for (int i = 0; i < data.size(); i++) {
                        bean.XBanner.DataBean dataBean = data.get(i);
                        String icon = dataBean.icon;
                        vplist.add(icon);
                    }
                    // 设置XBanner的页面切换特效
                    banner.setPageTransformer(Transformer.Default);
                    // 设置XBanner页面切换的时间，即动画时长
                    banner.setPageChangeDuration(1000);


                    banner.setData(vplist,null);

                    banner.setmAdapter(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, View view, int position) {
                            Glide.with(getActivity()).load(vplist.get(position)).into((ImageView) view);
                        }
                    });
                    banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                        @Override
                        public void onItemClick(XBanner banner, int position) {
                            Toast.makeText(getActivity(), "点击了第" + (position + 1) + "张图片", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

    }

   @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }
    /**
     * 推荐的接口回调   实现推荐的逻辑
     * @param tuijian
     */
    @Override
    public void settuijian(final bean.XBanner.TuijianBean tuijian) {
        if(getActivity()!=null)
        {
            getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                tuijianlist = tuijian.list;
                System.out.println("========================"+tuijianlist.size());
                adapter=new MyAdapter(getActivity(),R.layout.item_list,tuijianlist);
                initData();
            }
        });
        }
    }
    /**
     * 秒杀的接口回调 实现推荐的逻辑

     */
    @Override
    public void setmiaosha(final bean.XBanner.MiaoshaBean miaosha1) {
        if(getActivity()!=null)
        {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(miaosha1.list.size()+"==============================================");

                    miaoshalist = miaosha1.list;

                    miaoshaRecycler = miaosha.findViewById(R.id.miaoshaRecycler);

                    mLayoutManager2 = new LinearLayoutManager(getActivity());
                    mLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                    miaoshaRecycler.setLayoutManager(mLayoutManager2);

                    //GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),1);
                    //gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                    //miaoshaRecycler.setLayoutManager(gridLayoutManager);

                    System.out.println(miaoshalist.size()+"==================================");
                    MiaoShaAdapter miaoShaAdapter=new MiaoShaAdapter(getActivity(),miaoshalist);
                    miaoshaRecycler.setAdapter(miaoShaAdapter);
                }
            });

        }

    }
    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.et:
                Intent intent=new Intent(getActivity(), SousuoActivity.class);
                startActivity(intent);
                break;
        }



    }
}
