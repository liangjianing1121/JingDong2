package view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DELL on 2017/10/12.
 */

public interface ZhuceView {
    void onZhuceSuccess(String code,String msg);
    void onZhuceFaliure(String code,String msg);
    void  onFaliure(Call call, IOException e);
}
