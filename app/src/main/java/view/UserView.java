package view;

import java.io.IOException;

import bean.Login;
import bean.User;
import okhttp3.Call;

/**
 * Created by DELL on 2017/10/12.
 */

public interface UserView {
    void getUserSuccess(User.DataBean data);
    void getUserFailure(String msg);
    void onFailure(Call call, IOException e);
}
