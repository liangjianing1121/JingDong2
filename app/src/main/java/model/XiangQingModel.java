package model;

import android.graphics.drawable.shapes.PathShape;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.XiangQing;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/17.
 */

public class XiangQingModel {
    private onXiangQing onxiangQing;

    public void getXiangQing(int pid){

        Map<String,String> params=new HashMap<>();
        params.put("pid",pid+"");
        NetRequestData.Call(Api.XIANGQING, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {
                onxiangQing.onFailure(call,e);
            }
            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    XiangQing xiangQing = gson.fromJson(result, XiangQing.class);
                    XiangQing.DataBean data = xiangQing.data;
                    XiangQing.SellerBean seller = xiangQing.seller;
                    onxiangQing.onXiangQingSuccess(data,seller);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setOnxiangQing(onXiangQing onxiangQing) {
        this.onxiangQing = onxiangQing;
    }

    public  interface  onXiangQing{
        void onXiangQingSuccess(XiangQing.DataBean data,XiangQing.SellerBean seller);
        void onFailure(Call call,IOException e);
    }

}
