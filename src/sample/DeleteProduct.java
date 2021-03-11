package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class DeleteProduct {
    @FXML
    TextField txtDelete;

    public static boolean foundProduct;
    public static String DBProductID;
    public static String deleteValue;

    public void clickDelete(){
        foundProduct = false;
        deleteValue=txtDelete.getText();
        if (!deleteValue.equals("")) {
            if (deleteValue.length()<=10) {
                try {
                    DBSetup.initProductsAndStocks();
                    DBCollection productCheck = DBSetup.database.getCollection("Product Details");
                    DBCursor findIterable = productCheck.find();
                    for (DBObject count : findIterable) {
                        DBProductID = (String) count.get("ProductID");
                        if (deleteValue.equals(DBProductID)) {
                            foundProduct = true;
                        }
                    }
                    if (foundProduct) {
                        BasicDBObject basicDBObjectForProduct = new BasicDBObject();
                        basicDBObjectForProduct.put("ProductID", deleteValue);
                        productCheck.findAndRemove(basicDBObjectForProduct);
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Product deleted successfully");
                        a.showAndWait();
                        txtDelete.setText("");

                    } else {
                        Alert ProductExists = new Alert(Alert.AlertType.NONE);
                        ProductExists.setAlertType(Alert.AlertType.WARNING);
                        ProductExists.setContentText("Product doesn't exist to delete");
                        ProductExists.showAndWait();
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
            Alert emptyField = new Alert(Alert.AlertType.NONE);
            emptyField.setAlertType(Alert.AlertType.WARNING);
            emptyField.setContentText("Nothing has been entered to delete");
            emptyField.showAndWait();
        }
    }
}
