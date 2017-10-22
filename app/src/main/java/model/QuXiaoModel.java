package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import adapter.QuXiao;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/22.
 */

public class QuXiaoModel {
    private onQuXiao onQuXiao;

    public void getQuxiao(int uid,int status,int orderid){

        Map<String,String> params=new HashMap<>();
        params.put("uid",uid+"");
        params.put("status",status+"");
        params.put("orderId",orderid+"");

        NetRequestData.Call(Api.QuXiao, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {
                onQuXiao.onFailure(call,e);


            }

            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    QuXiao quXiao = gson.fromJson(result, QuXiao.class);
                    String code = quXiao.code;
                    String msg = quXiao.msg;
                    if("0".equals(code))
                    {
                        onQuXiao.onQuXiaoSuccess(msg);
                    }
                    else
                    {
                        onQuXiao.onQuXiaoFailure(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setOnQuXiao(QuXiaoModel.onQuXiao onQuXiao) {
        this.onQuXiao = onQuXiao;
    }

    public interface onQuXiao{
        void onQuXiaoSuccess(String msg);
        void onQuXiaoFailure(String msg);
        void onFailure(Call call,IOException e);


    }
}
