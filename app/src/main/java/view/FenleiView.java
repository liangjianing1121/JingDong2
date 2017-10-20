package view;

import java.io.IOException;
import java.util.List;

import bean.Fenlei;
import bean.Fenlei2;
import okhttp3.Call;

/**
 * Created by DELL on 2017/10/10.
 */

public interface FenleiView {
    void getFenlei(List<Fenlei.DataBean> data);
    void onFaliure(Call call, IOException e);

}
