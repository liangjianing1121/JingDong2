package presenter;

import java.io.IOException;
import java.util.List;

import bean.ShopCartList;
import model.ShopCartListModel;
import okhttp3.Call;
import view.ShopCartListView;

/**
 * Created by DELL on 2017/10/18.
 */

public class ShopCartListPresentere implements ShopCartListModel.onShopcartList {

    private ShopCartListModel shopCartListModel;
    private ShopCartListView shopCartListView;

    public ShopCartListPresentere(ShopCartListView shopCartListView) {
        this.shopCartListView = shopCartListView;
        shopCartListModel=new ShopCartListModel();
        shopCartListModel.setOnshopcartlist(this);
    }

    public void requestShopCartList(int uid){
        shopCartListModel.getShopsortList(uid);
    }

    @Override
    public void onShopCartListSuccess(List<ShopCartList.DataBean> dataBean) {
        shopCartListView.onShopCartListSuccess(dataBean);
    }

    @Override
    public void onShopCartListFailure(String msg) {
        shopCartListView.onShopCartListFailure(msg);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        shopCartListView.onFailure(call,e);
    }
}
