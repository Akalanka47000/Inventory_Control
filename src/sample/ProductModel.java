//Some parts of the following lines of code have been referenced from the following website, https://medium.com/@keeptoo/adding-data-to-javafx-tableview-stepwise-df582acbae4f
package sample;

import javafx.beans.property.SimpleStringProperty;

public class ProductModel {

    private SimpleStringProperty product;
    private SimpleStringProperty productID;
    private SimpleStringProperty productCategory;


    public ProductModel(String product,String productID,String productCategory) {
        this.product = new SimpleStringProperty(product);
        this.productID = new SimpleStringProperty(productID);
        this.productCategory = new SimpleStringProperty(productCategory);
    }

    public String getProduct() {
        return product.get();
    }

    public void setProduct(String product) {
        this.product = new SimpleStringProperty(product);
    }
    public String getProductID() {
        return productID.get();
    }

    public void setProductID(String productID) {

        this.productID = new SimpleStringProperty(productID);
    }
    public String getProductCategory() {

        return productCategory.get();
    }

    public void setProductCategory(String productCategory) {

        this.productCategory = new SimpleStringProperty(productCategory);
    }

}