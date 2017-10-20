package bean;

/**
 * Created by DELL on 2017/10/11.
 */

public class Fenlei3 {

    private String name;
    private int cid;

    public Fenlei3() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Fenlei3(String name, int cid) {

        this.name = name;
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Fenlei3{" +
                "name='" + name + '\'' +
                ", cid=" + cid +
                '}';
    }
}
