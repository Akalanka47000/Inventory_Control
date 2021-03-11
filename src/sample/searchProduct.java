package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class searchProduct {
    @FXML
    ComboBox cmbSearchCriteria;

    @FXML
    TextField txtSearch;

    public static String CriteriaSelected;
    public static String searchText;
    public static String DBProductID;
    public static String DBProductName;
    public static String DBProductCategory;
    public static boolean productFound;

    @FXML
    public void initialize(){
        ObservableList dropDownList = FXCollections.observableArrayList();
        dropDownList.add("ID");
        dropDownList.add("Name");
        cmbSearchCriteria.setItems(dropDownList);
    }
    @FXML
    public void searchByID(){
        searchText=txtSearch.getText();
        if (!searchText.equals("")) {
            if (searchText.length()<=10) {
                try {
                    DBSetup.initProductsAndStocks();
                    DBCollection Products = DBSetup.database.getCollection("Product Details");
                    DBCursor findIterable = Products.find();
                    for (DBObject count : findIterable) {
                        DBProductID = (String) count.get("ProductID");
                        DBProductName = (String) count.get("Product Name");
                        DBProductCategory = (String) count.get("Category");
                        if (searchText.equals(DBProductID)) {
                            productFound = true;
                            break;
                        }
                    }
                    if (productFound) {
                        Alert productExists = new Alert(Alert.AlertType.NONE);
                        productExists.setAlertType(Alert.AlertType.INFORMATION);
                        productExists.setContentText("The product exists," + "\n" + "Product name: " + DBProductName + "\n" + "Category: " + DBProductCategory); //Displaying of product information
                        productExists.showAndWait();

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

    @FXML
    public void searchByName(){
        searchText=txtSearch.getText();
        if (!searchText.equals("")) {
                try {
                    DBSetup.initProductsAndStocks();
                    DBCollection Products = DBSetup.database.getCollection("Product Details");
                    DBCursor findIterable = Products.find();
                    for (DBObject count : findIterable) {
                        DBProductID = (String) count.get("ProductID");
                        DBProductName = (String) count.get("Product Name");
                        DBProductCategory = (String) count.get("Category");
                        if (searchText.toUpperCase().equals(DBProductName.toUpperCase())) {
                            productFound = true;
                            break;
                        }
                    }
                    if (productFound) {
                        Alert productExists = new Alert(Alert.AlertType.NONE);
                        productExists.setAlertType(Alert.AlertType.INFORMATION);
                        productExists.setContentText("The product exists," + "\n" + "Product ID: " + DBProductID + "\n" + "Category: " + DBProductCategory); //Displaying of product info
                        productExists.showAndWait();

                    } else {
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.WARNING);
                        a.setContentText("The product you are searching for is not available");
                        a.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } else {
            Alert emptyCategory = new Alert(Alert.AlertType.NONE);
            emptyCategory.setAlertType(Alert.AlertType.WARNING);
            emptyCategory.setContentText("Search details have not been entered");
            emptyCategory.showAndWait();
        }
    }

    public void clickSearch(){
        productFound=false;
        CriteriaSelected= (String) cmbSearchCriteria.getValue();
        if (CriteriaSelected=="ID"){
            searchByID();
        }else if (CriteriaSelected=="Name"){
            searchByName();
        }else{
            Alert criteriaNotSelected = new Alert(Alert.AlertType.NONE);
            criteriaNotSelected.setAlertType(Alert.AlertType.WARNING);
            criteriaNotSelected.setContentText("Select an option to search by");
            criteriaNotSelected.showAndWait();
        }

    }
}
