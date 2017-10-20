package presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;

import bean.Login;
import model.LoginModel;
import okhttp3.Call;
import view.LoginView;

/**
 * Created by DELL on 2017/10/12.
 */

public class LoginPresenter implements LoginModel.getLogin {
    private LoginModel loginModel;
    private LoginView loginView;
    private Context context;

    public LoginPresenter(Context context,LoginView loginView) {
        this.context=context;
        this.loginView = loginView;
        loginModel=new LoginModel();
        loginModel.setGetLogin(this);
    }

    public void requestLoginn(String mobile,String psd){

        if(TextUtils.isEmpty("mobile"))
        {
            Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty("psd"))
        {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
        }
        loginModel.getLogin(mobile,psd);
    }




    @Override
    public void onLoginSuccess(String code, String msg, Login.DataBean data) {
        loginView.onLoginSuccess(code,msg,data);
    }

    @Override
    public void onLoginFaliure(String code, String msg) {
        loginView.onLoginFaliure(code,msg);
    }

    @Override
    public void Faliure(Call call, IOException e) {
        loginView.onFaliure(call,e);

    }
}
