package presenter;

import android.content.Context;

import java.io.IOException;

import model.ZhuceModel;
import okhttp3.Call;
import view.ZhuceView;

/**
 * Created by DELL on 2017/10/12.
 */

public class ZhucePresenter implements ZhuceModel.OnZhuce {


    private ZhuceModel zhuceModel;
    private ZhuceView zhuceView;
    private Context context;

    public ZhucePresenter(Context context,ZhuceView zhuceView) {
        this.context=context;
        this.zhuceView = zhuceView;
        zhuceModel=new ZhuceModel();
        zhuceModel.setOnZhuce(this);
    }


    public void requestZhuce(String mobile,String psd){
        zhuceModel.getZhuce(mobile,psd);
    }

    @Override
    public void getZhuceSuccess(String code, String msg) {

        zhuceView.onZhuceSuccess(code,msg);
    }

    @Override
    public void getZhuceFaliure(String code, String msg) {

        zhuceView.onZhuceFaliure(code,msg);
    }

    @Override
    public void onFaliuer(Call call, IOException e) {

        zhuceView.onFaliure(call,e);

    }
}
