package sample;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignUp {

    @FXML
    Button btnBack;

    @FXML
    Button btnLogin;

    @FXML
    TextField txtUsername;

    @FXML
    PasswordField txtPassword;

    public static String Username;
    public static String Password;
    public static boolean loginDetailsFound;
    public static Stage HomePage =new Stage();


    @FXML
    public void login() throws Exception{
            loginDetailsFound=false;
            DBSetup.init();
            DBCollection loginCheck = DBSetup.database.getCollection("LoginDetails");
            DBCursor findIterable=loginCheck.find();
            for (DBObject counter:findIterable) {
                Username= (String) counter.get("Username");
                Password= (String) counter.get("Password");
                System.out.println(Username);
                System.out.println(Password);
                if (((txtUsername.getText()).equals(Username)) && ((txtPassword.getText()).equals(Password))) {
                    loginDetailsFound = true;
                    Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                    HomePage.setTitle("Home");
                    HomePage.setScene(new Scene(root, 956, 638));
                    HomePage.show();
                    Controller.loginStage.close();
                    txtPassword.setText("");
                    txtUsername.setText("");
                    break;

                }
            }

            if (loginDetailsFound==false){
                Alert wrongLoginDetails = new Alert(Alert.AlertType.NONE);
                wrongLoginDetails.setAlertType(Alert.AlertType.WARNING);
                wrongLoginDetails.setContentText("Wrong Username or Password. Please re-enter");
                wrongLoginDetails.showAndWait();

            }
    }

    @FXML
    public void goBacktoMain() throws Exception{
        Controller.loginStage.close();
        Main.Primarystage.show();
    }
}
