package presenter;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import bean.Fenlei;
import bean.XBanner;
import model.MainModel;
import okhttp3.Call;
import view.MainView;

/**
 * Created by DELL on 2017/10/9.
 */

public class MainPresenter implements MainModel.onSuccess{
    private MainView mainView;
    private MainModel mainModel;
    private Context context;

    public MainPresenter(Context context, MainView mainView) {
        this.context=context;
        this.mainView = mainView;
        mainModel=new MainModel();
        mainModel.setOnsuccess(this);
    }

    public void getdata(){
        mainModel.Request();
    }

    @Override
    public void getData(List<XBanner.DataBean> data) {
        mainView.setXbanner(data);

    }

    @Override
    public void gettuijian(XBanner.TuijianBean tuijian) {

        mainView.settuijian(tuijian);
    }

    @Override
    public void getmiaosha(XBanner.MiaoshaBean miaosha) {
        mainView.setmiaosha(miaosha);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        mainView.onFailure(call,e);
    }

}
