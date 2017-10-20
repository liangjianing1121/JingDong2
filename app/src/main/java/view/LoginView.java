package view;


import java.io.IOException;

import bean.Login;
import okhttp3.Call;

/**
 * Created by DELL on 2017/10/12.
 */

public interface LoginView {

    void onLoginSuccess(String code,String msg, Login.DataBean data);
    void onLoginFaliure(String code,String msg);
    void onNameError(String msg);
    void onPsdError(String msg);
    void onFaliure(Call call, IOException e);
}
