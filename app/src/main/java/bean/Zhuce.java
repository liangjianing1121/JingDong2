package bean;

/**
 * Created by DELL on 2017/10/12.
 */

public class Zhuce {


    /**
     * msg : 注册成功
     * code : 0
     * data : {"age":null,"createtime":null,"gender":0,"icon":null,"mobile":"13835305861","money":0,"nickname":null,"password":"111111","uid":0,"username":"13835305861"}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * age : null
         * createtime : null
         * gender : 0
         * icon : null
         * mobile : 13835305861
         * money : 0
         * nickname : null
         * password : 111111
         * uid : 0
         * username : 13835305861
         */

        public Object age;
        public Object createtime;
        public int gender;
        public Object icon;
        public String mobile;
        public int money;
        public Object nickname;
        public String password;
        public int uid;
        public String username;
    }
}
