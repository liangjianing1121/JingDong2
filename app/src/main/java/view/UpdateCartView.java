package view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DELL on 2017/10/18.
 */

public interface UpdateCartView {


    void UpdateSuccess(String code,String msg);
    void UpdateFailure(String code,String msg);
    void onFilure(Call call, IOException e);


}
