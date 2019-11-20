package se.nackademin;

import java.io.Serializable;

public class Category implements Serializable {
    private String categoryType;

    public Category(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}
