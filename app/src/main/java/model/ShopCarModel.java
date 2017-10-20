package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.AddShopCart;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/18.
 */

public class ShopCarModel {

    private onShopCart onshopcart;


    public void getShopCar(int uid,int pid){
        Map<String,String> params=new HashMap<>();
        params.put("uid",uid+"");
        params.put("pid",pid+"");

        NetRequestData.Call(Api.ADDCART, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {
                onshopcart.onFailure(call,e);
            }
            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    AddShopCart addShopCart = gson.fromJson(result, AddShopCart.class);
                    String code = addShopCart.code;
                    String msg = addShopCart.msg;
                    if("0".equals(code))
                    {
                        onshopcart.onShopSuccess(code,msg);
                    }
                    else if("1".equals(code))
                    {
                        onshopcart.onShopFailure(code,msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        }

    public void setOnshopcart(onShopCart onshopcart) {
        this.onshopcart = onshopcart;
    }

    public interface onShopCart{
            void onShopSuccess(String code,String msg);
            void onShopFailure(String code,String msg);
            void onFailure(Call call,IOException e);
        }

    }

