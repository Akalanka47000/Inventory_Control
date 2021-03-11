package sample;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewStocksSingle {
    @FXML
    TextField txtSearch;

    @FXML
    Label lblStock;

    public static String searchText;
    public static String DBProductID;
    public static String DBStocks;
    public static boolean productFound;


    @FXML
    public void clickSearch(){
    productFound=false;
        searchText=txtSearch.getText();
        if (!searchText.equals("")) {
            if (searchText.length()<=10) {
                try {
                    DBSetup.initProductsAndStocks();
                    DBCollection Products = DBSetup.database.getCollection("Product Details");
                    DBCursor findIterable = Products.find();
                    for (DBObject count : findIterable) {
                        DBProductID = (String) count.get("ProductID");
                        DBStocks = (String) count.get("Stocks Available");
                        if (DBStocks==null){ //If stock details haven't been entered yet for a product
                            DBStocks="Not Entered";
                        }
                        if (searchText.equals(DBProductID)) {
                            productFound = true;
                            break;
                        }
                    }
                    if (productFound) {
                        lblStock.setText(DBStocks);  //Displaying stock number in a label

                    } else {
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.WARNING);
                        a.setContentText("The product you are searching for is not available");
                        a.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                Alert IDLengthAlert = new Alert(Alert.AlertType.NONE);
                IDLengthAlert.setAlertType(Alert.AlertType.WARNING);
                IDLengthAlert.setContentText("Product ID must be less than 10 characters");
                IDLengthAlert.showAndWait();
            }
        } else {
            Alert emptyCategory = new Alert(Alert.AlertType.NONE);
            emptyCategory.setAlertType(Alert.AlertType.WARNING);
            emptyCategory.setContentText("Search details have not been entered");
            emptyCategory.showAndWait();
        }
    }







}
