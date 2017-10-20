package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.dell.jingdong.R;

import java.io.IOException;

import bean.XiangQing;
import okhttp3.Call;
import presenter.XiangQingPresenterr;
import view.XiangQingView;

/**
 * Created by DELL on 2017/10/17.
 */

public class XiangQingFragment2 extends Fragment implements XiangQingView {


    private View view;
    private WebView wv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.xiangqing_fragment2, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        initData();

    }

    private void initData() {

        XiangQingPresenterr presenterr=new XiangQingPresenterr(this);
        Intent intent = getActivity().getIntent();
        int pid = intent.getIntExtra("pid", 0);
        presenterr.requestXiangQing(pid);

    }

    private void initView() {
        wv = view.findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }

    @Override
    public void onXiangQingSuccess(final XiangQing.DataBean data, XiangQing.SellerBean seller) {

        if(getActivity()!=null)
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    wv.loadUrl(data.detailUrl);

                }
            });


        }

    }

    @Override
    public void onFailure(Call call, IOException e) {

    }
}
