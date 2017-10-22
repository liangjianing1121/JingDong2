package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.jingdong.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.FenleiAdapter;
import bean.Fenlei;
import bean.Fenlei2;
import okhttp3.Call;
import presenter.FenleiPresenter;
import view.FenleiView;

/**
 * Created by DELL on 2017/10/10.
 */

public class FenleiFragment extends Fragment implements FenleiView {
    private View view;
    private RecyclerView recyclerView;
    private GridLayoutManager mLayoutManager;
    private List<Fenlei2> fenleilist2;
    private List<Fenlei2> fenleilist;
    private String icon;
    private String name;
    private FenleiPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fenleifragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.requestfenei();
    }

    private void initData() {

        presenter = new FenleiPresenter(getActivity(),this);
        presenter.requestfenei();
    }

    private void initView() {
        recyclerView = view.findViewById(R.id.recyclerView1);
    }
    /**
     * 分类的接口回调  做分类的逻辑
     * @param data
     * */
    @Override
    public void getFenlei(final List<Fenlei.DataBean> data) {
        System.out.println("================"+data);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                 fenleilist = new ArrayList<>();
                fenleilist2=new ArrayList<>();

                for (int i = 0; i < data.size(); i++) {
                    Fenlei.DataBean dataBean = data.get(i);
                    icon = dataBean.icon;
                    name = dataBean.name;
                    if(i<10){
                        Fenlei2 fenlei2=new Fenlei2();
                        fenlei2.setIcon(icon);
                        fenlei2.setName(name);
                        fenleilist.add(fenlei2);
                    }
                    else
                    {
                        Fenlei2 fenlei2=new Fenlei2();
                        fenlei2.setIcon(icon);
                        fenlei2.setName(name);
                        fenleilist2.add(fenlei2);
                    }
                }
                mLayoutManager = new GridLayoutManager(getActivity(),5);
                recyclerView.setLayoutManager(mLayoutManager);
                FenleiAdapter fenleiAdapter=new FenleiAdapter(getActivity(),fenleilist);
                recyclerView.setAdapter(fenleiAdapter);
            }
        });
    }
    @Override
    public void onFaliure(Call call, IOException e) {
    }
}
