package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class EditCategory {
    @FXML
    TextField txtexistingCat;

    @FXML
    TextField txtmodifiedCat;

    public static String existingCategory;
    public static String modifiedCategory;
    public static boolean foundCategory;
    public static boolean modifiedCategoryExists;
    public static String DBCategoryName;
    public static String DBProductCategory;

    @FXML
    public void clickUpdate(){
        modifiedCategoryExists=false;
        foundCategory=false;
        existingCategory=txtexistingCat.getText();
        modifiedCategory=txtmodifiedCat.getText();
        if (!existingCategory.equals("") && !modifiedCategory.equals("")) {
            try {
                DBSetup.initCategory();
                DBCollection categoryCheck = DBSetup.database.getCollection("Categories");
                DBCursor findIterable=categoryCheck.find();
                for (DBObject count:findIterable) {
                    DBCategoryName= (String) count.get("CategoryName");
                    if( existingCategory.equals(DBCategoryName)) {
                        foundCategory = true;
                    }
                    if(modifiedCategory.equals(DBCategoryName)) {
                        modifiedCategoryExists=true;
                    }
                }
                if (foundCategory==true && modifiedCategoryExists==false){  //If the category to be deleted exists and the category which we're trying to change into doesn't exist
                    BasicDBObject query = new BasicDBObject();
                    query.put("CategoryName", existingCategory);
                    BasicDBObject newValue = new BasicDBObject();
                    newValue.put("CategoryName", modifiedCategory);
                    BasicDBObject updateObject = new BasicDBObject();
                    updateObject.put("$set", newValue);
                    DBSetup.database.getCollection("Categories").update(query,updateObject);
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Category updated successfully");
                    a.showAndWait();
                    txtexistingCat.setText("");
                    txtmodifiedCat.setText("");
                    DBSetup.initProductsAndStocks();
                    DBCollection productCheck = DBSetup.database.getCollection("Product Details");
                    DBCursor findIterable1 = productCheck.find();
                    for (DBObject count : findIterable1) {
                        DBProductCategory = (String) count.get("Category");
                        if (existingCategory.equals(DBProductCategory)) {
                            BasicDBObject queryForProductCategory = new BasicDBObject();
                            queryForProductCategory.put("Category", existingCategory);
                            BasicDBObject newValue1 = new BasicDBObject();
                            newValue1.put("Category", modifiedCategory);
                            BasicDBObject updateObject1 = new BasicDBObject();
                            updateObject1.put("$set", newValue1);
                            DBSetup.database.getCollection("Product Details").update(queryForProductCategory,updateObject1);
                        }

                    }
                }else if (foundCategory==false) {
                    Alert CategoryExists = new Alert(Alert.AlertType.NONE);
                    CategoryExists.setAlertType(Alert.AlertType.WARNING);
                    CategoryExists.setContentText("Category doesn't exist to update it");
                    CategoryExists.showAndWait();
                }else if (modifiedCategoryExists==true){
                    Alert ModCategoryExists = new Alert(Alert.AlertType.NONE);
                    ModCategoryExists.setAlertType(Alert.AlertType.WARNING);
                    ModCategoryExists.setContentText("The category which you're trying to change into already exists");
                    ModCategoryExists.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert emptyCategory = new Alert(Alert.AlertType.NONE);
            emptyCategory.setAlertType(Alert.AlertType.WARNING);
            emptyCategory.setContentText("One of the two fields are empty");
            emptyCategory.showAndWait();
        }
    }
}
