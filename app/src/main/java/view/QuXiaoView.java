package view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DELL on 2017/10/22.
 */

public interface QuXiaoView {
    void onQuXiaoSuccess(String msg);
    void onQuXiaoFailure(String msg);
    void onFailure(Call call, IOException e);

}
