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


public class ViewStockDetails<findIterable> implements Initializable {

    @FXML
    private TableView<StocksModel> tblView;

    @FXML
    public TableColumn<StocksModel, String> tblColumnProduct;

    @FXML
    public TableColumn<StocksModel, String> tblColumnStocks;

    public static String stocks;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblColumnProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
        tblColumnStocks.setCellValueFactory(new PropertyValueFactory<>("stock"));
        display2();

    }
    public void display2(){
        ObservableList<StocksModel> mainList = FXCollections.observableArrayList();
        DBSetup.initProductsAndStocks();
        DBCollection productCheck = DBSetup.database.getCollection("Product Details");
        DBCursor findIterable = productCheck.find();
        for (DBObject count : findIterable) {
            stocks= (String) count.get("Stocks Available");
            System.out.println(stocks);
            if(stocks==null){
                stocks="Not entered";
            }
            StocksModel tabledata = new StocksModel((String) count.get("Product Name"), stocks);
            mainList.add(tabledata);
        }
        tblView.setItems(mainList);
    }
}