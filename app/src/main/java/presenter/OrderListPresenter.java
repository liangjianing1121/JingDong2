package presenter;

import java.io.IOException;
import java.util.List;

import bean.OrderList;
import model.OrderListModel;
import okhttp3.Call;
import view.OrderListView;

/**
 * Created by DELL on 2017/10/22.
 */

public class OrderListPresenter implements OrderListModel.onOrderList {

    private OrderListModel orderListModel;
    private OrderListView orderListView;

    public OrderListPresenter(OrderListView orderListView) {
        this.orderListView = orderListView;
        orderListModel=new OrderListModel();
        orderListModel.setOnOrderList(this);
    }
    public void requestOrderList(int uid){
        orderListModel.getOrderList(uid);
    }
    @Override
    public void onOrderListsuccess(List<OrderList.DataBean> data) {
        orderListView.onOrderListsuccess(data);
    }

    @Override
    public void onOrderListFailure(String msg) {
        orderListView.onOrderListFailure(msg);

    }

    @Override
    public void onFailure(Call call, IOException e) {
        orderListView.onFailure(call,e);

    }
}
