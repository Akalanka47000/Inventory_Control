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


public class ViewProductsController<findIterable> implements Initializable {

    @FXML
    private TableView<ProductModel> tblView;

    @FXML
    public TableColumn<ProductModel, String> tblColumn;

    @FXML
    public TableColumn<ProductModel, String> tblColumnID;

    @FXML
    public TableColumn<ProductModel, String> tblColumnCategory;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        tblColumnID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        tblColumnCategory.setCellValueFactory(new PropertyValueFactory<>("productCategory"));
        display();

    }
    public void display(){
        ObservableList<ProductModel> mainList = FXCollections.observableArrayList();
        DBSetup.initProductsAndStocks();
        DBCollection productCheck = DBSetup.database.getCollection("Product Details");
        DBCursor findIterable = productCheck.find();
        for (DBObject count : findIterable) {
            ProductModel product = new ProductModel((String) count.get("Product Name"), (String) count.get("ProductID"), (String) count.get("Category"));
            mainList.add(product);

        }
        tblView.setItems(mainList);
    }
}