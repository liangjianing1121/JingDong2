package presenter;

import java.io.IOException;
import java.util.List;

import bean.ShopList;
import model.ShopCarModel;
import model.ShopListModel;
import okhttp3.Call;
import view.ShopCartView;

/**
 * Created by DELL on 2017/10/18.
 */

public class ShopCartPresenter implements ShopCarModel.onShopCart {


    private ShopCartView shopCartView;
    private ShopCarModel shopCarModel;

    public ShopCartPresenter(ShopCartView shopCartView) {
        this.shopCartView = shopCartView;
        shopCarModel=new ShopCarModel();
        shopCarModel.setOnshopcart(this);
    }

    public void requestShopcart(int uid,int pid){
        shopCarModel.getShopCar(uid,pid);
    }


    @Override
    public void onShopSuccess(String code, String msg) {
        shopCartView.onShopSuccess(code,msg);
    }

    @Override
    public void onShopFailure(String code, String msg) {
        shopCartView.onShopFailure(code,msg);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        shopCartView.onFailure(call,e);

    }
}
