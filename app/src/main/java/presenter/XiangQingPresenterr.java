package presenter;

import java.io.IOException;

import bean.XiangQing;
import model.XiangQingModel;
import okhttp3.Call;
import view.XiangQingView;

/**
 * Created by DELL on 2017/10/17.
 */

public class XiangQingPresenterr implements XiangQingModel.onXiangQing {

    private XiangQingModel xiangQingModel;
    private XiangQingView xiangQingView;

    public XiangQingPresenterr(XiangQingView xiangQingView) {
        this.xiangQingView = xiangQingView;
        xiangQingModel=new XiangQingModel();
        xiangQingModel.setOnxiangQing(this);
    }


    public void requestXiangQing(int pid){
        xiangQingModel.getXiangQing(pid);

    }
    @Override
    public void onXiangQingSuccess(XiangQing.DataBean data, XiangQing.SellerBean seller) {

        xiangQingView.onXiangQingSuccess(data,seller);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        xiangQingView.onFailure(call,e);

    }
}
