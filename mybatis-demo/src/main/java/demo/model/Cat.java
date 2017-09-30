package demo.model;

/**
 * Created by Administrator on 2017-09-13.
 */
public class Cat {
    private int id;//主键.

    private String catName;//姓名. cat_name

    private int catAge;//年龄. cat_age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getCatAge() {
        return catAge;
    }

    public void setCatAge(int catAge) {
        this.catAge = catAge;
    }
}
