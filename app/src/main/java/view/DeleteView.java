package view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DELL on 2017/10/21.
 */

public interface DeleteView {
    void onDeleteSuccess(String msg);
    void onDeleteFailure(String msg);
    void onFailure(Call call, IOException e);

}
