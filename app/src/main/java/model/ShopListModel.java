package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ShopList;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/16.
 */

public class ShopListModel {

    private getShopList getshopList;

    public void getShopList(int pscid,int page,int sort){


        Map<String,String> params=new HashMap<>();
        params.put("pscid",pscid+"");
        params.put("sort",sort+"");
        NetRequestData.Call(Api.SHOPLIST, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {

                getshopList.onFailure(call,e);
            }

            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    ShopList shopList = gson.fromJson(result, ShopList.class);
                    List<ShopList.DataBean> data = shopList.data;
                    String msg = shopList.msg;
                    String code = shopList.code;
                    if("0".equals(code))
                    {
                        getshopList.onShopListSuccess(data);
                    }
                    else if("1".equals(code))
                    {
                        getshopList.onShopListFailure(msg);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }


    public void setGetshopList(getShopList getshopList) {
        this.getshopList = getshopList;
    }

    public interface getShopList{
        void onShopListSuccess(List<ShopList.DataBean> data);
        void onShopListFailure(String msg);
        void onFailure(Call call,IOException e);
    }
}
