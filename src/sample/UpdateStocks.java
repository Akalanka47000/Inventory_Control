package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class UpdateStocks {
    @FXML
    TextField txtID;

    @FXML
    TextField txtQuantity;

    public static String DBID;
    public static String ID;
    public static String Quantity;
    public static boolean foundID;

    @FXML
    public void clickUpdate(){
        foundID=false;
        ID=txtID.getText();
        Quantity=txtQuantity.getText();
        if (!ID.equals("") && !Quantity.equals("")) {
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
                        BasicDBObject query = new BasicDBObject();
                        query.put("ProductID", ID);
                        BasicDBObject newValue = new BasicDBObject();
                        newValue.put("Stocks Available", Quantity);
                        BasicDBObject updateObject = new BasicDBObject();
                        updateObject.put("$set", newValue);
                        DBSetup.database.getCollection("Product Details").update(query,updateObject);
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Stock details updated successfully");
                        a.showAndWait();
                        txtID.setText("");
                        txtQuantity.setText("");
                    } else {
                        Alert ProductdoesntExist = new Alert(Alert.AlertType.NONE);
                        ProductdoesntExist.setAlertType(Alert.AlertType.WARNING);
                        ProductdoesntExist.setContentText("Product ID doesn't exist");
                        ProductdoesntExist.showAndWait();
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
