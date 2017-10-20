package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ZiFenLei;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/11.
 */

public class ZiFenLeiModel {
    private OnZiFenLei onZiFenLei;


    public void getRequestZiFenLei(int cid){
        Map<String,String> params=new HashMap<>();
        params.put("cid",cid+"");
        NetRequestData.Call(Api.ZiFenlei, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {
                onZiFenLei.onFaliure(call,e);

            }

            @Override
            public void onSuccess(Call call, Response response) {

                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    ZiFenLei ziFenLei = gson.fromJson(result, ZiFenLei.class);
                    List<ZiFenLei.DataBean> data = ziFenLei.data;

                    onZiFenLei.getZiFenLei(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void setOnZiFenLei(OnZiFenLei onZiFenLei) {
        this.onZiFenLei = onZiFenLei;
    }

    public interface OnZiFenLei{
        void getZiFenLei(List<ZiFenLei.DataBean> data);
        void onFaliure(Call call,IOException e);
    }
}
