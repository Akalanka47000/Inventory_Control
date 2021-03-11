package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.awt.*;

public class DeleteCategory {
    @FXML
    TextField txtDelete;

    public static String deleteValue;
    public static boolean foundCategory;
    public static boolean categoryInProductTable;
    public static String DBCategoryName;
    public static String DBProductCategory;

    public void clickDelete(){
        foundCategory = false;  //Variable to indicate whether the item to delete exists in the DB while searching it
        categoryInProductTable=false;  // Variable to indicate whether the category that we're trying to delete has been used in the product table
        deleteValue=txtDelete.getText();
        if (!deleteValue.equals("")) {
            try {
                DBSetup.initCategory();
                DBCollection categoryCheck = DBSetup.database.getCollection("Categories");
                DBCursor findIterable=categoryCheck.find();
                for (DBObject count:findIterable) {
                    DBCategoryName= (String) count.get("CategoryName");
                    if( deleteValue.equals(DBCategoryName)) {
                        foundCategory = true;
                    }
                }
                if (foundCategory) {
                    BasicDBObject basicDBObjectForCat = new BasicDBObject();
                    basicDBObjectForCat.put("CategoryName", deleteValue);
                    categoryCheck.findAndRemove(basicDBObjectForCat);
                    txtDelete.setText("");
                    DBSetup.initProductsAndStocks();
                    DBCollection productCheck = DBSetup.database.getCollection("Product Details");
                    DBCursor findIterable2 = productCheck.find();
                    for (DBObject counter : findIterable2) {
                        DBProductCategory= (String) counter.get("Category");
                        if (deleteValue.equals(DBProductCategory)) {
                            BasicDBObject basicDBObjectForProduct = new BasicDBObject();
                            basicDBObjectForProduct.put("Category", deleteValue);
                            productCheck.findAndRemove(basicDBObjectForProduct);
                            categoryInProductTable=true;
                        }
                    }
                    if (categoryInProductTable==true) { // Code to be executed if the deleted category has been used in the product table
                        Alert a1 = new Alert(Alert.AlertType.NONE);
                        a1.setAlertType(Alert.AlertType.WARNING);
                        a1.setContentText("The category that you have deleted had been used in the product details sector, as a result all corresponding records which consisted of that category have been deleted as well");
                        a1.showAndWait();
                    }else{
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Category deleted successfully");
                        a.showAndWait();
                    }

                } else {
                    Alert CategoryExists = new Alert(Alert.AlertType.NONE);
                    CategoryExists.setAlertType(Alert.AlertType.WARNING);
                    CategoryExists.setContentText("Category doesn't exist to delete");
                    CategoryExists.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert emptyCategory = new Alert(Alert.AlertType.NONE);
            emptyCategory.setAlertType(Alert.AlertType.WARNING);
            emptyCategory.setContentText("Nothing has been entered to delete");
            emptyCategory.showAndWait();
        }
    }
}
