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

public class UpdateProduct {
    @FXML
    TextField txtProductName;

    @FXML
    TextField txtID;

    @FXML
    ComboBox cmbCategory;

    public static String DBCategoryName;
    public static String DBID;
    public static String ID;
    public static String ProductName;
    public static String CategorySelected;
    public static boolean foundID;

    @FXML
    public void initialize(){
        DBSetup.initCategory();
        DBCollection categoryCheck = DBSetup.database.getCollection("Categories");
        DBCursor findIterable=categoryCheck.find();
        ObservableList dropDownList = FXCollections.observableArrayList();
        for (DBObject count:findIterable) {
            DBCategoryName= (String) count.get("CategoryName");
            dropDownList.add(DBCategoryName);
        }
        cmbCategory.setItems(dropDownList);
    }

    @FXML
    public void clickUpdate(){
        foundID=false;
        ID=txtID.getText();
        ProductName=txtProductName.getText();
        CategorySelected= (String) cmbCategory.getValue();
        System.out.println(CategorySelected);
        if (!ID.equals("") && !ProductName.equals("") && !(CategorySelected ==null)) {
            if (ID.length()<=10) {
                try {
                    DBSetup.initProductsAndStocks();
                    DBCollection productCheck = DBSetup.database.getCollection("Product Details");
                    DBCursor findIterable = productCheck.find();
                    for (DBObject count : findIterable) {
                        DBID = (String) count.get("ProductID");
                        if (ID.equals(DBID)) {
                            foundID = true;
                        }

                    }
                    if (foundID) {
                        Alert ProductExists = new Alert(Alert.AlertType.NONE);
                        ProductExists.setAlertType(Alert.AlertType.WARNING);
                        ProductExists.setContentText("The product ID that you're trying to change into already exists");
                        ProductExists.showAndWait();

                    } else {
                        BasicDBObject query = new BasicDBObject();
                        query.put("ProductID", EditProduct.ProductIDtoModify);
                        BasicDBObject newValue = new BasicDBObject();
                        newValue.put("ProductID", ID);
                        newValue.put("Product Name", ProductName);
                        newValue.put("Category", CategorySelected);
                        BasicDBObject updateObject = new BasicDBObject();
                        updateObject.put("$set", newValue);
                        DBSetup.database.getCollection("Product Details").update(query,updateObject);
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Product details updated successfully");
                        a.showAndWait();
                        txtID.setText("");
                        txtProductName.setText("");
                        cmbCategory.setValue("");
                        EditProduct.editProductStage.close();
                        HomePageController.EditProduct.show();
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
            Alert emptyDetails = new Alert(Alert.AlertType.NONE);
            emptyDetails.setAlertType(Alert.AlertType.WARNING);
            emptyDetails.setContentText("All details have not been entered");
            emptyDetails.showAndWait();
        }


    }
}
