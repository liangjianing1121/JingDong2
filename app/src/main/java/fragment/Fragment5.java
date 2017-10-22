package fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dell.jingdong.LoginActivity2;
import com.example.dell.jingdong.R;
import com.example.dell.jingdong.SuccessActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;

import bean.User;
import okhttp3.Call;
import presenter.UserPresenter;
import view.UserView;

/**
 * Created by DELL on 2017/10/9.
 */

public class Fragment5 extends Fragment implements View.OnClickListener, UserView {

    private View view;
    private TextView tv_user;
    private int spuid;
    private ImageView img_denglu;
    private UserPresenter presenter;
    private LinearLayout order;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.wode, null);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.requestUser(spuid);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();

    }

    private void initData() {
        SharedPreferences sp = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
        spuid = sp.getInt("uid", 0);
        System.out.println(spuid+"============spuid");
        presenter = new UserPresenter(getActivity(),this);
        presenter.requestUser(spuid);

    }

    private void initView() {
        tv_user = view.findViewById(R.id.tv_user);
        img_denglu = view.findViewById(R.id.img_denglu);
        order = view.findViewById(R.id.order);
        this.tv_user.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.tv_user:

                if(spuid==0)
                {
                    Intent intent=new Intent(getActivity(), LoginActivity2.class);
                    startActivity(intent);
                }
                else
                {
                    // TODO: 2017/10/12 登录成功后的页面
                    Intent intent=new Intent(getActivity(), SuccessActivity.class);
                    startActivity(intent);

                }

                break;
        }
    }

    @Override
    public void getUserSuccess(final User.DataBean data) {
        if(getActivity()!=null)
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(data.nickname!=null)
                    {
                        tv_user.setText(data.nickname);
                    }
                    else
                    {
                        tv_user.setText(data.username);
                    }

                    if(data.icon!=null)
                    {

                        SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.img_denglu);
                        draweeView.setImageURI(data.icon);
                        //Glide.with(getActivity()).load(data.icon).diskCacheStrategy(DiskCacheStrategy.NONE).into(img_denglu);
                    }
                    else
                    {
                        img_denglu.setImageResource(R.drawable.denglu);
                    }

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
