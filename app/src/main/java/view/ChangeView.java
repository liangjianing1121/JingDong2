package view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DELL on 2017/10/16.
 */

public interface ChangeView {
    void changeSuccessNickName(String code, String msg);

    void changeFailureNickName(String code, String msg);

    void Filure(Call call, IOException e);

}
