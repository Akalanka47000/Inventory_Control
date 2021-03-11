package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;

public class HomePageController {
    public static Stage AddCategory =new Stage();
    public static Stage ViewCategory =new Stage();
    public static Stage EditCategory =new Stage();
    public static Stage DeleteCategory =new Stage();
    public static Stage AddProduct =new Stage();
    public static Stage SearchProduct =new Stage();
    public static Stage ViewProduct =new Stage();
    public static Stage EditProduct =new Stage();
    public static Stage DeleteProduct =new Stage();
    public static Stage AboutStage=new Stage();
    public static Stage AddToStock=new Stage();
    public static Stage ViewStockAll=new Stage();
    public static Stage ViewStockSingle=new Stage();


    @FXML
    public void clickAdd() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("AddCategory.fxml"));
        AddCategory.setTitle("Add Category");
        AddCategory.setScene(new Scene(root, 675, 457));
        AddCategory.show();
    }
    public void clickView() throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CategoryView.fxml"));
            ViewCategory.setTitle("Category List");
            ViewCategory.setScene(new Scene(root, 600, 400));
            ViewCategory.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void clickEdit() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("EditCategory.fxml"));
        EditCategory.setTitle("Edit Category");
        EditCategory.setScene(new Scene(root, 802, 472));
        EditCategory.show();
    }
    public void clickDelete() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("DeleteCategory.fxml"));
        DeleteCategory.setTitle("Delete Category");
        DeleteCategory.setScene(new Scene(root, 675, 457));
        DeleteCategory.show();
    }
    public void clickAddProduct() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        AddProduct.setTitle("Add Product");
        AddProduct.setScene(new Scene(root, 744, 483));
        AddProduct.show();
    }
    public void clickSearchProduct() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("searchProduct.fxml"));
        SearchProduct.setTitle("Search Product");
        SearchProduct.setScene(new Scene(root, 740, 480));
        SearchProduct.show();
    }
    public void clickViewProduct() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ViewProducts.fxml"));
        ViewProduct.setTitle("Products");
        ViewProduct.setScene(new Scene(root, 600, 400));
        ViewProduct.show();
    }
    public void clickEditProduct() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("EditProduct.fxml"));
        EditProduct.setTitle("Edit Product");
        EditProduct.setScene(new Scene(root, 705, 438));
        EditProduct.show();
    }
    public void clickDeleteProduct() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("DeleteProduct.fxml"));
        DeleteProduct.setTitle("Delete Product");
        DeleteProduct.setScene(new Scene(root, 635, 410));
        DeleteProduct.show();
    }
    public void clickAddToStock() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UpdateStocks.fxml"));
        AddToStock.setTitle("Add to Stocks");
        AddToStock.setScene(new Scene(root, 768, 512));
        AddToStock.show();
    }
    public void clickViewStocksAll() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ViewStockDetails.fxml"));
        ViewStockAll.setTitle("Stock Details");
        ViewStockAll.setScene(new Scene(root, 600, 400));
        ViewStockAll.show();
    }
    public void clickViewStocksSingle() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ViewStocks[Single].fxml"));
        ViewStockSingle.setTitle("View Stocks");
        ViewStockSingle.setScene(new Scene(root, 754, 511));
        ViewStockSingle.show();
    }

    public void about() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("About.fxml"));
        AboutStage.setTitle("App info");
        AboutStage.setScene(new Scene(root, 664, 442));
        AboutStage.show();
    }


    public void goBacktoMain() throws Exception{
        SignUp.HomePage.close();
        Main.Primarystage.show();
    }

}
