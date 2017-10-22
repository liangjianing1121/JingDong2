package bean;

import java.util.List;

/**
 * Created by DELL on 2017/10/22.
 */

public class OrderList {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2017-10-22T13:31:07","orderid":803,"price":399,"status":0,"uid":195},{"createtime":"2017-10-22T13:31:36","orderid":804,"price":399,"status":0,"uid":195},{"createtime":"2017-10-22T14:06:03","orderid":828,"price":198,"status":0,"uid":195},{"createtime":"2017-10-22T14:06:09","orderid":829,"price":447.96,"status":0,"uid":195},{"createtime":"2017-10-22T14:06:13","orderid":830,"price":645.96,"status":0,"uid":195}]
     * page : 1
     */

    public String msg;
    public String code;
    public String page;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * createtime : 2017-10-22T13:31:07
         * orderid : 803
         * price : 399.0
         * status : 0
         * uid : 195
         */

        public String createtime;
        public int orderid;
        public double price;
        public int status;
        public int uid;
    }
}
