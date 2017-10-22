package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Fenlei;
import bean.XBanner;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/9.
 */

public class MainModel {

    private onSuccess onsuccess;
    private List<XBanner.DataBean> data;
    //private onFenlei onfenlei;

    public  void  Request(){
        Map<String,String> params=new HashMap<>();
        NetRequestData.Call(Api.XBANNER, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {
                onsuccess.onFailure(call,e);
            }

            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    parseData(result);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void parseData(String result) {
        Gson gson=new Gson();
        XBanner xBanner = gson.fromJson(result, XBanner.class);
        if(xBanner!=null)
        {
            if("0".equals(xBanner.code))
            {

                data = xBanner.data;
                XBanner.MiaoshaBean miaosha = xBanner.miaosha;
                XBanner.TuijianBean tuijian = xBanner.tuijian;
                onsuccess.getData(data);
                onsuccess.getmiaosha(miaosha);
                onsuccess.gettuijian(tuijian);
            }

        }

    }

    public void setOnsuccess(onSuccess onsuccess) {
        this.onsuccess = onsuccess;
    }

    public interface onSuccess{
        void getData(List<XBanner.DataBean> data);
        void gettuijian(XBanner.TuijianBean tuijian);
        void getmiaosha(XBanner.MiaoshaBean miaosha);
        void onFailure(Call call, IOException e);
    }

}
