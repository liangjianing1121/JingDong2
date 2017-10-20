package view;

import java.io.IOException;
import java.util.List;

import bean.Shop;
import okhttp3.Call;

/**
 * Created by DELL on 2017/10/14.
 */

public interface ShopView {
    void onShopSuccess(String code, String msg, int page, List<Shop.DataBean> data);
    void onShopFailure(String code, String msg);
    void onFailure(Call call, IOException e);
}
