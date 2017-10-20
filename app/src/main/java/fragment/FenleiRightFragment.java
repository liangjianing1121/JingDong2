package fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.jingdong.R;

import java.io.IOException;
import java.util.List;

import adapter.ZiFenLeiAdapter;
import bean.ZiFenLei;
import okhttp3.Call;
import presenter.ZiFenLeiPresenter;
import view.ZiFenLeiView;

/**
 * Created by DELL on 2017/10/11.
 */

public class FenleiRightFragment extends Fragment implements ZiFenLeiView{

    private int cid;
    private View view;
    private ImageView iv_scroll;
    private RecyclerView rv_fenlei;
    private ZiFenLeiPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.zifenlei, null);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

        presenter = new ZiFenLeiPresenter(getActivity(),this);
        presenter.requestZiFenLei(cid);

    }

    private void initView() {
        iv_scroll = view.findViewById(R.id.iv_scroll);
        rv_fenlei = view.findViewById(R.id.rv_fenlei);
        iv_scroll.setImageResource(R.drawable.fenleiviewpager);
    }


    public void getcid(int position){
        cid=position;
    }


    @Override
    public void getZiFenLeiData(final List<ZiFenLei.DataBean> data) {

        if(getActivity()!=null)
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                    rv_fenlei.setLayoutManager(linearLayoutManager);
                    ZiFenLeiAdapter ziFenLeiAdapter=new ZiFenLeiAdapter(getActivity(),data);
                    rv_fenlei.setAdapter(ziFenLeiAdapter);
                }
            });
        }
    }

    @Override
    public void onFaliure(Call call, IOException e) {

    }
}
