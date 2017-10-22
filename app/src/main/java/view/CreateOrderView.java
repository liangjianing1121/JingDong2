package view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DELL on 2017/10/22.
 */

public interface CreateOrderView {
    void onCreateSuccess(String msg);
    void onCreatFailure(String msg);
    void Failure(Call call, IOException e);
}
