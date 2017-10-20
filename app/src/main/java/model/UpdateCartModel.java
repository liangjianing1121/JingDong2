package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.UpdateCart;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/18.
 */

public class UpdateCartModel {
    private onUpdateCart onupdateCart;


    public void getUpdataCart(int uid,int sellerid,int pid,int selected,int num){
        Map<String,String> params=new HashMap<>();
        params.put("uid",uid+"");
        params.put("sellerid",sellerid+"");
        params.put("pid",pid+"");
        params.put("selected",selected+"");
        params.put("num",num+"");

        NetRequestData.Call(Api.UPDATACART, params, new CallBck() {

            public void onFailure(Call call, IOException e) {
                onupdateCart.onFilure(call,e);
            }
            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    UpdateCart updateCart = gson.fromJson(result, UpdateCart.class);
                    String code = updateCart.code;
                    String msg = updateCart.msg;
                    if("0".equals(code))
                    {
                        onupdateCart.UpdateSuccess(code,msg);
                    }
                    else if("1".equals(code))
                    {
                        onupdateCart.UpdateFailure(code,msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setOnupdateCart(onUpdateCart onupdateCart) {
        this.onupdateCart = onupdateCart;
    }

    public interface onUpdateCart{
        void UpdateSuccess(String code,String msg);
        void UpdateFailure(String code,String msg);
        void onFilure(Call call,IOException e);
    }


}
