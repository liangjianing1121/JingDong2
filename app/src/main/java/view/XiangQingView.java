package view;

import java.io.IOException;

import bean.XiangQing;
import okhttp3.Call;

/**
 * Created by DELL on 2017/10/17.
 */

public interface XiangQingView {
    void onXiangQingSuccess(XiangQing.DataBean data, XiangQing.SellerBean seller);
    void onFailure(Call call, IOException e);
}
