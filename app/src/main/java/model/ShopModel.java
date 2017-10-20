package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Shop;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/14.
 */

public class ShopModel {

    private onShop onshop;


    public void getShop(String keywords,int page,int sort){
        Map<String,String> params=new HashMap<>();
        params.put("keywords",keywords);
        params.put("page",page+"");
        params.put("sort",sort+"");
        NetRequestData.Call(Api.SHOP, params, new CallBck() {
            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    Shop shop = gson.fromJson(result, Shop.class);
                    String code = shop.code;
                    String msg = shop.msg;
                    int page1 = shop.page;
                    List<Shop.DataBean> data = shop.data;
                    if("0".equals(code))
                    {
                        onshop.onShopSuccess(code,msg,page1,data);
                    }

                    else if("1".equals(code))
                    {
                        onshop.onShopFailure(code,msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call call, IOException e) {

                onshop.onFailure(call,e);
            }
        });

    }

    public void setOnshop(onShop onshop) {
        this.onshop = onshop;
    }

    public interface onShop{
        void onShopSuccess(String code, String msg, int page, List<Shop.DataBean> data);
        void onShopFailure(String code, String msg);
        void onFailure(Call call, IOException e);

    }


}
