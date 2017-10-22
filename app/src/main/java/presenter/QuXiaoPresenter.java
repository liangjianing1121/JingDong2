package presenter;

import java.io.IOException;

import model.QuXiaoModel;
import okhttp3.Call;
import view.QuXiaoView;

/**
 * Created by DELL on 2017/10/22.
 */

public class QuXiaoPresenter implements QuXiaoModel.onQuXiao {

    private QuXiaoModel quXiaoModel;
    private QuXiaoView quXiaoView;

    public QuXiaoPresenter(QuXiaoView quXiaoView) {
        this.quXiaoView = quXiaoView;
        quXiaoModel=new QuXiaoModel();
        quXiaoModel.setOnQuXiao(this);
    }

    public void requestQuXiao(int uid,int status,int orderid){
        quXiaoModel.getQuxiao(uid,status,orderid);

    }

    @Override
    public void onQuXiaoSuccess(String msg) {

    }

    @Override
    public void onQuXiaoFailure(String msg) {

    }

    @Override
    public void onFailure(Call call, IOException e) {

    }
}
