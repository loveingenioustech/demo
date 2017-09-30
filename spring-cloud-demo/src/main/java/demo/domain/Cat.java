package demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017-09-11.
 */
@Entity
public class Cat {

    /**
     * 使用@Id指定主键.
     * 指定主键的生成策略，mysql默认的是自增长。
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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
