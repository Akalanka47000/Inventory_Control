package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;

public class Controller {
    @FXML
    Button btnNewAcc;

    @FXML
    Button btnLogin;

    public static Stage createAccStage=new Stage();
    public static Stage loginStage=new Stage();

    @FXML
    public void accCreate() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("createAcc.fxml"));
        createAccStage.setTitle("Create Account");
        createAccStage.setScene(new Scene(root, 956, 638));
        createAccStage.show();
        Main.Primarystage.close();
    }

    @FXML
    public void signUp() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        loginStage.setTitle("Log into your account");
        loginStage.setScene(new Scene(root, 956, 638));
        loginStage.show();
        Main.Primarystage.close();
    }


}
