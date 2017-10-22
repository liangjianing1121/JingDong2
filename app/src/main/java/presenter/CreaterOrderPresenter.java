package presenter;

import java.io.IOException;

import model.CreateOrderModel;
import okhttp3.Call;
import view.CreateOrderView;

/**
 * Created by DELL on 2017/10/22.
 */

public class CreaterOrderPresenter implements CreateOrderModel.onCreateOder {

    private CreateOrderModel createOrderModel;
    private CreateOrderView createOrderView;


    public CreaterOrderPresenter(CreateOrderView createOrderView) {
        this.createOrderView = createOrderView;
        createOrderModel =new CreateOrderModel();
        createOrderModel.setOnCreateOder(this);
    }


    public void requestCreateOrder(int uid, String price){
        createOrderModel.getCreateOrder(uid,price);
    }
    @Override
    public void onCreateSuccess(String msg) {
        createOrderView.onCreateSuccess(msg);
    }

    @Override
    public void onCreatFailure(String msg) {
        createOrderView.onCreatFailure(msg);
    }

    @Override
    public void Failure(Call call, IOException e) {
        createOrderView.Failure(call,e);
    }
}
