package view;

import java.io.IOException;
import java.util.List;

import bean.ShopList;
import okhttp3.Call;

/**
 * Created by DELL on 2017/10/16.
 */

public interface ShopListView {
    void onShopListSuccess(List<ShopList.DataBean> data);
    void onShopListFailure(String msg);
    void onFailure(Call call, IOException e);
}
