package bean;

/**
 * Created by DELL on 2017/10/12.
 */

public class User {


    /**
     * msg : 获取用户信息成功
     * code : 0
     * data : {"age":null,"createtime":"2017-10-10T00:00:00","gender":0,"icon":"http://120.27.23.105/images/71.jpg","mobile":"18612991023","money":0,"nickname":"kson","password":"111111","uid":71,"username":"18612991023"}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * age : null
         * createtime : 2017-10-10T00:00:00
         * gender : 0
         * icon : http://120.27.23.105/images/71.jpg
         * mobile : 18612991023
         * money : 0
         * nickname : kson
         * password : 111111
         * uid : 71
         * username : 18612991023
         */

        public Object age;
        public String createtime;
        public int gender;
        public String icon;
        public String mobile;
        public int money;
        public String nickname;
        public String password;
        public int uid;
        public String username;
    }
}
