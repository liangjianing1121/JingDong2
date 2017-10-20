package presenter;

import java.io.IOException;

import model.ChangeModel;
import okhttp3.Call;
import view.ChangeView;

/**
 * Created by DELL on 2017/10/16.
 */

public class ChangePresenter implements ChangeModel.getNickName {

    private ChangeModel changeModel;
    private ChangeView changeView;

    public ChangePresenter(ChangeView changeView) {
        this.changeView = changeView;
        changeModel=new ChangeModel();
        changeModel.setGetnickName(this);
    }
    public void RequestChange(int uid,String nickname){
        changeModel.getNickName(uid,nickname);

    }

    @Override
    public void changeSuccessNickName(String code, String msg) {
        changeView.changeSuccessNickName(code,msg);
    }

    @Override
    public void changeFailureNickName(String code, String msg) {
        changeView.changeFailureNickName(code,msg);

    }

    @Override
    public void Filure(Call call, IOException e) {
        changeView.Filure(call,e);

    }
}
