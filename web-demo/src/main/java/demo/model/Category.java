package demo.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Administrator on 2017-05-10.
 */
public class Category implements Serializable {
    private int id;
    private String categoryName;
    private Category parentCategory;
    private Set subCategories;
    private String info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    /**
     * @param parentCategory the parentCategory to set
     */
    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set subCategories) {
        this.subCategories = subCategories;
    }

    public String toString() {
        return categoryName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Try to avoid database ids in your hashCode and equals() implementation.
     * Base hashCode() and equals() implementation on buisness key and on immutable attributes of the entity object
     * @return
     */
    @Override
    public int hashCode() {
        return getCategoryName().length() + 20;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Category) {
            Category object = (Category) obj;
            if (this == object) {
                return true;
            }
            if (object.getCategoryName().equals(object.getCategoryName())) {
                return true;
            } else {
                return false;
            }

        } else
            return false;
    }


}
