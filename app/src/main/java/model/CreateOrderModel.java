package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

import bean.CreateOrder;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/22.
 */

public class CreateOrderModel {
    private onCreateOder onCreateOder;

    public void getCreateOrder(int uid,String price){

        Map<String,String> params=new HashMap<>();
        params.put("uid",uid+"");
        params.put("price",price+"");
        NetRequestData.Call(Api.CreateOrder, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {

               onCreateOder.Failure(call,e);
            }

            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    CreateOrder createOrder = gson.fromJson(result, CreateOrder.class);
                    String code = createOrder.code;
                    String msg = createOrder.msg;

                    if ("0".equals(code)) {
                        onCreateOder.onCreateSuccess(msg);
                    } else
                    {
                        onCreateOder.onCreatFailure(msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setOnCreateOder(CreateOrderModel.onCreateOder onCreateOder) {
        this.onCreateOder = onCreateOder;
    }
    public interface onCreateOder{
        void onCreateSuccess(String msg);
        void onCreatFailure(String msg);
        void Failure(Call call,IOException e);
    }


}
