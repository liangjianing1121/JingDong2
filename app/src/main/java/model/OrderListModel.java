package model;

import android.speech.tts.Voice;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.OrderList;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/22.
 */

public class OrderListModel {
    private onOrderList onOrderList;

    public void getOrderList(int uid){

        Map<String,String> params=new HashMap<>();
        params.put("uid",uid+"");
        NetRequestData.Call(Api.GETORDERR, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {
                onOrderList.onFailure(call,e);

            }

            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    OrderList orderList = gson.fromJson(result, OrderList.class);
                    String code = orderList.code;
                    String msg = orderList.msg;
                    List<OrderList.DataBean> data = orderList.data;
                    if ("0".equals(code))
                    {
                        onOrderList.onOrderListsuccess(data);
                    }
                    else
                    {
                        onOrderList.onOrderListFailure(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void setOnOrderList(OrderListModel.onOrderList onOrderList) {
        this.onOrderList = onOrderList;
    }

    public interface onOrderList{
        void onOrderListsuccess(List<OrderList.DataBean> data);
        void onOrderListFailure(String msg);
        void onFailure(Call call,IOException e);
    }
}
