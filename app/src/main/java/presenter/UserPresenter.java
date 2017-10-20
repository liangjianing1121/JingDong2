package presenter;

import android.content.Context;

import java.io.IOException;

import bean.Login;
import bean.User;
import model.UserModel;
import okhttp3.Call;
import view.UserView;

/**
 * Created by DELL on 2017/10/12.
 */

public class UserPresenter implements UserModel.onUser {

    private UserModel userModel;
    private UserView userView;
    private Context context;

    public UserPresenter(Context context,UserView userView) {
        this.context=context;
        this.userView = userView;
        userModel=new UserModel();
        userModel.setOnuser(this);
    }

    public void requestUser(int uid){
        userModel.getUserData(uid);
    }


    @Override
    public void getUserSuccess(User.DataBean data) {
        userView.getUserSuccess(data);
    }

    @Override
    public void getUserFaliure(String msg) {

        userView.getUserFailure(msg);
    }

    @Override
    public void Failure(Call call, IOException e) {

        userView.onFailure(call,e);
    }
}
