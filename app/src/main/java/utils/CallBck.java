package utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by DELL on 2017/10/9.
 */

public interface CallBck {
    void onFailure(Call call , IOException e);
    void onSuccess(Call call, Response response);
}
