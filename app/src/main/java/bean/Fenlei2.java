package bean;

/**
 * Created by DELL on 2017/10/10.
 */

public class Fenlei2 {
    private String icon;
    private String name;

    public Fenlei2() {
    }

    @Override
    public String toString() {
        return "Fenlei2{" +
                "icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fenlei2(String icon, String name) {

        this.icon = icon;
        this.name = name;
    }
}
