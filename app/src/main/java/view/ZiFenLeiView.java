package view;

import java.io.IOException;
import java.util.List;

import bean.ZiFenLei;
import okhttp3.Call;

/**
 * Created by DELL on 2017/10/11.
 */

public interface ZiFenLeiView {

    void getZiFenLeiData(List<ZiFenLei.DataBean> data);
    void onFaliure(Call call, IOException e);
}
