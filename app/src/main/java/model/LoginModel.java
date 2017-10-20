package model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.Login;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/12.
 */

public class LoginModel {
    private getLogin getLogin;


    public void getLogin(String mobile,String psd){
        Map<String,String> params=new HashMap<>();
        params.put("mobile",mobile);
        params.put("password",psd);

        NetRequestData.Call(Api.LOGIN, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {
                getLogin.Faliure(call,e);
            }

            @Override
            public void onSuccess(Call call, Response response) {

                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    Login login = gson.fromJson(result, Login.class);
                    String code = login.code;
                    String msg = login.msg;
                    Login.DataBean data = login.data;
                    if(code.equals("0"))
                    {
                        getLogin.onLoginSuccess(code,msg,data);
                    }
                    else if(code.equals("1"))
                    {
                        getLogin.onLoginFaliure(code,msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setGetLogin(LoginModel.getLogin getLogin) {
        this.getLogin = getLogin;
    }

    public interface getLogin{
        void onLoginSuccess(String code,String msg,Login.DataBean data);
        void onLoginFaliure(String code,String msg);
        void Faliure(Call call,IOException e);
    }

}
