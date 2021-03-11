//Some parts of the following lines of code have been referenced from the following website, https://medium.com/@keeptoo/adding-data-to-javafx-tableview-stepwise-df582acbae4f
package sample;

import javafx.beans.property.SimpleStringProperty;

public class CategoryModel {

    private SimpleStringProperty category;

    public CategoryModel(String category) {
        this.category = new SimpleStringProperty(category);
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category = new SimpleStringProperty(category);
    }
}