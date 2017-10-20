package presenter;

import java.io.IOException;

import model.UpdateCartModel;
import okhttp3.Call;
import view.UpdateCartView;

/**
 * Created by DELL on 2017/10/18.
 */

public class UpdateCartPresenter implements UpdateCartModel.onUpdateCart {


    private UpdateCartModel updateCartModel;
    private UpdateCartView updateCartView;

    public UpdateCartPresenter(UpdateCartView updateCartView) {
        this.updateCartView = updateCartView;
        updateCartModel=new UpdateCartModel();
        updateCartModel.setOnupdateCart(this);
    }

    public void requestUpdateCart(int uid,int sellerid,int pid,int selected,int num){
        updateCartModel.getUpdataCart(uid,sellerid,pid,selected,num);

    }

    @Override
    public void UpdateSuccess(String code, String msg) {
        updateCartView.UpdateSuccess(code,msg);

    }

    @Override
    public void UpdateFailure(String code, String msg) {
        updateCartView.UpdateFailure(code,msg);
    }

    @Override
    public void onFilure(Call call, IOException e) {

        updateCartView.onFilure(call,e);

    }
}
