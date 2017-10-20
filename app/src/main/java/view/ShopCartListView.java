package view;

import java.io.IOException;
import java.util.List;

import bean.ShopCartList;
import okhttp3.Call;

/**
 * Created by DELL on 2017/10/18.
 */

public interface ShopCartListView {
    void onShopCartListSuccess(List<ShopCartList.DataBean> dataBean);
    void onShopCartListFailure(String msg);
    void onFailure(Call call, IOException e);
}
