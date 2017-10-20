package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Fenlei;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/10.
 */

public class FenleiModel {

    private onFenlei onfenlei;

     public  void RequestFenlei(){
        Map<String,String> params=new HashMap<>();
        NetRequestData.Call(Api.FENLEI, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {
                onfenlei.onFailure(call,e);
            }

            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    Fenlei fenlei = gson.fromJson(result, Fenlei.class);
                    List<Fenlei.DataBean> data = fenlei.data;
                    onfenlei.getFenlei(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setFenlei(onFenlei onfenlei) {
        this.onfenlei = onfenlei;
    }
    public interface onFenlei{
        void getFenlei(List<Fenlei.DataBean> data);
        void onFailure(Call call, IOException e);
    }




}
