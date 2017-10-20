package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.Zhuce;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/12.
 */

public class ZhuceModel {

    private OnZhuce onZhuce;



    public void getZhuce(String mobile,String psd){
        Map<String,String> params=new HashMap<>();
        params.put("mobile",mobile);
        params.put("password",psd);
        NetRequestData.Call(Api.ZHUCE, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {
                onZhuce.onFaliuer(call,e);
            }
            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    Zhuce zhuce = gson.fromJson(result, Zhuce.class);
                    String code = zhuce.code;
                    String msg = zhuce.msg;
                   //Zhuce.DataBean data = zhuce.data;

                    if(code.equals("0"))
                    {
                        onZhuce.getZhuceSuccess(code,msg);
                    }
                    else if(code.equals("1"))
                    {
                        onZhuce.getZhuceFaliure(code,msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setOnZhuce(OnZhuce onZhuce) {
        this.onZhuce = onZhuce;
    }

    public interface OnZhuce{
        void getZhuceSuccess(String code,String msg);
        void getZhuceFaliure(String code,String msg);
        void onFaliuer(Call call,IOException e);
    }


}
