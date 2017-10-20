package view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DELL on 2017/10/18.
 */

public interface ShopCartView {
    void onShopSuccess(String code,String msg);
    void onShopFailure(String code,String msg);
    void onFailure(Call call, IOException e);

}
