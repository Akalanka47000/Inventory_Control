//Some parts of the following lines of code have been referenced from the following website, https://medium.com/@keeptoo/adding-data-to-javafx-tableview-stepwise-df582acbae4f
package sample;


import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class CategoryViewController<findIterable> implements Initializable {

    @FXML
    private TableView<CategoryModel> tblView;

    @FXML
    public TableColumn<CategoryModel, String> tblColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        display();

    }
    public void display(){
            ObservableList<CategoryModel> mainList = FXCollections.observableArrayList();
            DBSetup.initCategory();
            DBCollection categoryCheck = DBSetup.database.getCollection("Categories");
            DBCursor findIterable = categoryCheck.find();
             for (DBObject count : findIterable) {
                 CategoryModel category = new CategoryModel((String) count.get("CategoryName"));
                 category.setCategory((String) count.get("CategoryName"));
                 //new CategoryModel((String) count.get("CategoryName"));
            mainList.add(category);

        }
        tblView.setItems(mainList);
    }
}












