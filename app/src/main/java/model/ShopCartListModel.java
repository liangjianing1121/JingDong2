package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ShopCartList;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/18.
 */

public class ShopCartListModel {
    private onShopcartList onshopcartlist;

    public void getShopsortList(int uid){
        Map<String,String> params=new HashMap<>();
        params.put("uid",uid+"");
        NetRequestData.Call(Api.GETCARTS, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {
                onshopcartlist.onFailure(call,e);
            }

            @Override
            public void onSuccess(Call call, Response response) {
                try {
                        String result = response.body().string();
                    if(result.equals("null")){
                        return;
                    }else{
                        Gson gson=new Gson();
                        ShopCartList shopCartList = gson.fromJson(result, ShopCartList.class);
                        String code = shopCartList.code;
                        String msg = shopCartList.msg;
                        List<ShopCartList.DataBean> data = shopCartList.data;
                        if("0".equals(code)){
                            onshopcartlist.onShopCartListSuccess(data);
                        }
                        else if("1".equals(code))
                        {
                            onshopcartlist.onShopCartListFailure(msg);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void setOnshopcartlist(onShopcartList onshopcartlist) {
        this.onshopcartlist = onshopcartlist;
    }

    public interface onShopcartList{
        void onShopCartListSuccess(List<ShopCartList.DataBean> dataBean);
        void onShopCartListFailure(String msg);
        void onFailure(Call call,IOException e);
    }


}
