package fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.jingdong.R;
import com.example.dell.jingdong.SousuoActivity;
import com.example.kson.tablayout.widget.HorizontalScollTabhost;
import com.example.kson.tablayout.widget.bean.CategoryBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.ListViewAdapter;
import adapter.MyAdapter;
import bean.Fenlei;
import okhttp3.Call;
import presenter.FenleiPresenter;
import view.FenleiView;
import view.MainView;

/**
 * Created by DELL on 2017/10/9.
 */

public class Fragment2 extends Fragment implements FenleiView, View.OnClickListener {

    private View view;
    private ListView lv;
    //private List<Fenlei.DataBean> data;
    public static int position;
    private EditText et;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.fragment2, null);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        FenleiPresenter presenter=new FenleiPresenter(getActivity(),this);
        presenter.requestfenei();
    }
    private void initView() {
       //data=new ArrayList<>();
        lv = view.findViewById(R.id.lv);
        lv.setVerticalScrollBarEnabled(false);
        et = view.findViewById(R.id.et);
        et.setFocusable(false);
        et.setOnClickListener(this);


    }

    @Override
    public void getFenlei(final List<Fenlei.DataBean> data) {
        if(getActivity()!=null)
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                  final ListViewAdapter listViewAdapter=new ListViewAdapter(getActivity(),data);
                    FenleiRightFragment fenleiRightFragment = new FenleiRightFragment();
                    fenleiRightFragment.getcid(data.get(0).cid);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fenlei_fragment, fenleiRightFragment).commit();
                    lv.setAdapter(listViewAdapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            view.setSelected(true);
                            //position=i;
                            //listViewAdapter.notifyDataSetChanged();
                            FenleiRightFragment fenleiRightFragment = new FenleiRightFragment();
                            fenleiRightFragment.getcid(data.get(i).cid);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fenlei_fragment, fenleiRightFragment).commit();
                            //listViewAdapter.setselect(i);
                            //Toast.makeText(getActivity(), i+""+data.get(i).cid, Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });
        }
    }

    @Override
    public void onFaliure(Call call, IOException e) {

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
