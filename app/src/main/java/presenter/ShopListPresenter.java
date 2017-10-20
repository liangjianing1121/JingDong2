package presenter;

import java.io.IOException;
import java.util.List;

import bean.ShopList;
import model.ShopListModel;
import okhttp3.Call;
import view.ShopListView;

/**
 * Created by DELL on 2017/10/16.
 */

public class ShopListPresenter implements ShopListModel.getShopList {

    private ShopListModel shopListModel;
    private ShopListView shopListView;

    public ShopListPresenter(ShopListView shopListView) {
        this.shopListView = shopListView;
        shopListModel=new ShopListModel();
        shopListModel.setGetshopList(this);
    }

    public void requestShopList(int pscid,int page,int sort){
        shopListModel.getShopList(pscid,page,sort);


    }

    @Override
    public void onShopListSuccess(List<ShopList.DataBean> data) {
        shopListView.onShopListSuccess(data);

    }

    @Override
    public void onShopListFailure(String msg) {
        shopListView.onShopListFailure(msg);

    }

    @Override
    public void onFailure(Call call, IOException e) {
        shopListView.onFailure(call,e);

    }
}
