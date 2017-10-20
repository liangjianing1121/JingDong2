package view;

import android.support.v4.widget.ListViewAutoScrollHelper;

import java.io.IOException;
import java.util.List;

import bean.Fenlei;
import bean.XBanner;
import okhttp3.Call;

/**
 * Created by DELL on 2017/10/9.
 */

public interface MainView {
    void setXbanner(List<XBanner.DataBean> data);
    void settuijian(XBanner.TuijianBean tuijian);
    void setmiaosha(XBanner.MiaoshaBean miaosha);
    void onFailure(Call call, IOException e);



}
