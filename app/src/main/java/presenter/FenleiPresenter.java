package presenter;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import bean.Fenlei;
import model.FenleiModel;
import okhttp3.Call;
import view.FenleiView;

/**
 * Created by DELL on 2017/10/10.
 */

public class FenleiPresenter implements FenleiModel.onFenlei {


    private FenleiModel fenleiModel;
    private FenleiView fenleiView;
    private Context context;

    public FenleiPresenter(Context context, FenleiView fenleiView) {
        this.context=context;
        this.fenleiView = fenleiView;
        fenleiModel=new FenleiModel();
        fenleiModel.setFenlei(this);
    }

    public void requestfenei(){
        fenleiModel.RequestFenlei();

    }


    @Override
    public void getFenlei(List<Fenlei.DataBean> data) {
        if(data!=null){
            fenleiView.getFenlei(data);
        }

    }
    @Override
    public void onFailure(Call call, IOException e) {
        fenleiView.onFaliure(call,e);
    }
}
