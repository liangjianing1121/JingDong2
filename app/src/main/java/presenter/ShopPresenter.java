package presenter;

import java.io.IOException;
import java.util.List;

import bean.Shop;
import model.ShopModel;
import okhttp3.Call;
import view.ShopView;

/**
 * Created by DELL on 2017/10/14.
 */

public class ShopPresenter implements ShopModel.onShop {


    private ShopModel shopModel;
    private ShopView shopView;

    public ShopPresenter(ShopView shopView) {
        this.shopView = shopView;
        shopModel=new ShopModel();
        shopModel.setOnshop(this);
    }

    public void requestShop(String keywords,int page,int sort){

        shopModel.getShop(keywords,page,sort);

    }
    @Override
    public void onShopSuccess(String code, String msg, int page, List<Shop.DataBean> data) {
        shopView.onShopSuccess(code,msg,page,data);
    }

    @Override
    public void onShopFailure(String code, String msg) {
        shopView.onShopFailure(code,msg);

    }

    @Override
    public void onFailure(Call call, IOException e) {
        shopView.onFailure(call,e);

    }
}
