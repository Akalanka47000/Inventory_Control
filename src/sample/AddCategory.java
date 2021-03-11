package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class AddCategory {
    @FXML
    TextField txtCategory;

    public static String catName;
    public static boolean foundCategory;
    public static String DBCategoryName;

    @FXML
    public void addCategory(){
        foundCategory = false;   //Variable to indicate the finding of the relevant category during the database search process
        catName=txtCategory.getText();
        if (!catName.equals("")) {  //Checking whether textfield is not left empty
            if (catName.equals(catName.toLowerCase())) { //To prevent the entering of uppercase letters
                try {
                    DBSetup.initCategory();
                    DBCollection categoryCheck = DBSetup.database.getCollection("Categories");
                    DBCursor findIterable = categoryCheck.find();
                    for (DBObject count : findIterable) {
                        DBCategoryName = (String) count.get("CategoryName");
                        if (catName.equals(DBCategoryName)) {
                            foundCategory = true;
                        }

                    }
                    if (foundCategory) {
                        Alert CategoryExists = new Alert(Alert.AlertType.NONE);
                        CategoryExists.setAlertType(Alert.AlertType.WARNING);
                        CategoryExists.setContentText("Category already exists");
                        CategoryExists.showAndWait();

                    } else {
                        BasicDBObject basicDBObjectForCat = new BasicDBObject();
                        basicDBObjectForCat.put("CategoryName", catName);
                        DBSetup.initCategory();
                        DBCollection collection = DBSetup.database.getCollection("Categories");
                        collection.insert(basicDBObjectForCat);
                        Alert a = new Alert(Alert.AlertType.NONE);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Category entered successfully");
                        a.showAndWait();
                        txtCategory.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                Alert lowercaseCategory = new Alert(Alert.AlertType.NONE);
                lowercaseCategory.setAlertType(Alert.AlertType.WARNING);
                lowercaseCategory.setContentText("Data entered must be lowercase");
                lowercaseCategory.showAndWait();
            }
        } else {
            Alert emptyCategory = new Alert(Alert.AlertType.NONE);
            emptyCategory.setAlertType(Alert.AlertType.WARNING);
            emptyCategory.setContentText("Category field is empty");
            emptyCategory.showAndWait();
        }
    }


}
