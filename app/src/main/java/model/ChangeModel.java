package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.Change;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/16.
 */

public class ChangeModel {

    private getNickName getnickName;

    public void getNickName(int uid,String nickname){
        Map<String,String> params=new HashMap<>();
        params.put("uid",uid+"");
        params.put("nickname",nickname);

        NetRequestData.Call(Api.CHANGE, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {
                getnickName.Filure(call,e);
            }

            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    Change change = gson.fromJson(result, Change.class);
                    String msg = change.msg;
                    String code = change.code;
                    if("0".equals(code))
                    {
                        getnickName.changeSuccessNickName(code,msg);
                    }
                    else if("1".equals(code))
                    {
                        getnickName.changeFailureNickName(code,msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setGetnickName(getNickName getnickName) {
        this.getnickName = getnickName;
    }

    public interface getNickName{
        void changeSuccessNickName(String code,String msg);
        void changeFailureNickName(String code,String msg);
        void Filure(Call call,IOException e);
    }
}
