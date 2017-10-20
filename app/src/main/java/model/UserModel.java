package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.Login;
import bean.User;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/12.
 */

public class UserModel {
    private onUser onuser;

    public void getUserData(int uid){
        Map<String,String> params=new HashMap<>();
        params.put("uid",uid+"");
        NetRequestData.Call(Api.GETUSER, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {
                onuser.Failure(call,e);
            }
            @Override
            public void onSuccess(Call call, Response response) {
                try {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    User user = gson.fromJson(result, User.class);
                    String code = user.code;
                    String msg = user.msg;
                    User.DataBean data = user.data;

                    System.out.println(user.code+"=============================="+user.toString());
                    if("0".equals(code)){
                        onuser.getUserSuccess(data);
                    }
                    else if("1".equals(code))
                    {
                        onuser.getUserFaliure(msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setOnuser(onUser onuser) {
        this.onuser = onuser;
    }

    public interface onUser{
        void getUserSuccess( User.DataBean data);
        void getUserFaliure(String msg);
        void Failure(Call call,IOException e);
    }

}
