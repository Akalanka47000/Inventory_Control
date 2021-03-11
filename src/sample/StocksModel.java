//Some parts of the following lines of code have been referenced from the following website, https://medium.com/@keeptoo/adding-data-to-javafx-tableview-stepwise-df582acbae4f
package sample;

import javafx.beans.property.SimpleStringProperty;

public class StocksModel {

    private SimpleStringProperty product;
    private SimpleStringProperty stock;


    public StocksModel(String product, String stock) {
        this.product = new SimpleStringProperty(product);
        this.stock = new SimpleStringProperty(stock);
    }

    public String getProduct() {
        return product.get();
    }

    public void setProduct(String product) {
        this.product = new SimpleStringProperty(product);
    }

    public String getStock() {
        return stock.get();
    }

    public void setStock(String stock) {
        this.stock = new SimpleStringProperty(stock);
    }

}