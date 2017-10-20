package presenter;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import bean.ZiFenLei;
import model.ZiFenLeiModel;
import okhttp3.Call;
import view.ZiFenLeiView;

/**
 * Created by DELL on 2017/10/11.
 */

public class ZiFenLeiPresenter implements ZiFenLeiModel.OnZiFenLei {


    private ZiFenLeiModel ziFenLeiModel;
    private ZiFenLeiView ziFenLeiView;
    private Context context;

    public ZiFenLeiPresenter(Context context,ZiFenLeiView ziFenLeiView) {
        this.context=context;
        this.ziFenLeiView = ziFenLeiView;
        ziFenLeiModel=new ZiFenLeiModel();
        ziFenLeiModel.setOnZiFenLei(this);
    }

    public void requestZiFenLei(int cid){
            ziFenLeiModel.getRequestZiFenLei(cid);
    }

    @Override
    public void getZiFenLei(List<ZiFenLei.DataBean> data) {
        ziFenLeiView.getZiFenLeiData(data);
    }

    @Override
    public void onFaliure(Call call, IOException e) {
        ziFenLeiView.onFaliure(call,e);

    }
}
