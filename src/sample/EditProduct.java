package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditProduct {
    @FXML
    TextField txtIDtoModify;

    public static String ProductIDtoModify;
    public static String DBID;
    public static boolean foundID;
    public static Stage editProductStage=new Stage();


    @FXML
    public void clickGO() throws Exception{
        foundID=false;
        ProductIDtoModify=txtIDtoModify.getText();
        if (!ProductIDtoModify.equals("")) {
            if (ProductIDtoModify.length()<=10) {
                try {
                    DBSetup.initProductsAndStocks();
                    DBCollection productCheck = DBSetup.database.getCollection("Product Details");
                    DBCursor findIterable = productCheck.find();
                    for (DBObject count : findIterable) {
                        DBID = (String) count.get("ProductID");
                        if (ProductIDtoModify.equals(DBID)) {
                            foundID = true;
                        }

                    }
                    if (foundID) {
                        Parent root = FXMLLoader.load(getClass().getResource("/UpdateProduct.fxml"));
                        editProductStage.setTitle("Enter modification details");
                        editProductStage.setScene(new Scene(root, 705, 439));
                        editProductStage.show();
                        HomePageController.EditProduct.close();
                        txtIDtoModify.setText("");

                    } else {
                        Alert IDdoesntExist = new Alert(Alert.AlertType.NONE);
                        IDdoesntExist.setAlertType(Alert.AlertType.WARNING);
                        IDdoesntExist.setContentText("Product ID does not exist");
                        IDdoesntExist.showAndWait();
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
            Alert emptyID = new Alert(Alert.AlertType.NONE);
            emptyID.setAlertType(Alert.AlertType.WARNING);
            emptyID.setContentText("Product ID has not been entered");
            emptyID.showAndWait();
        }


    }
}

