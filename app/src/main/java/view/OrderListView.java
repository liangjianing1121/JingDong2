package view;

import java.io.IOException;
import java.util.List;

import bean.OrderList;
import okhttp3.Call;

/**
 * Created by DELL on 2017/10/22.
 */

public interface OrderListView {
    void onOrderListsuccess(List<OrderList.DataBean> data);
    void onOrderListFailure(String msg);
    void onFailure(Call call, IOException e);
}
