package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.sun.org.apache.xpath.internal.operations.And;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.bson.Document;



public class CreateAcc {

    @FXML
    Button btnBack;

    @FXML
    Button btnCreate;

    @FXML
    TextField txtUsername;

    @FXML
    TextField txtEmail;

    @FXML
    PasswordField txtPassword;

    @FXML
    PasswordField txtConfirmPassword;

    public String username;
    public String password;
    public String email;
    public String confirmPassword;

    public boolean found=false;
    public boolean spaceFound;
    public boolean foundUsername;


    @FXML
    public void proceedWithCreation() throws Exception {
        foundUsername=false;
        username = txtUsername.getText();
        email = txtEmail.getText();
        password = txtPassword.getText();
        confirmPassword = txtConfirmPassword.getText();
        if (!password.equals(confirmPassword)) {
            Alert wrongPassword = new Alert(Alert.AlertType.NONE);
            wrongPassword.setAlertType(Alert.AlertType.WARNING);
            wrongPassword.setContentText("Passwords do not match. Please Re-enter");
            wrongPassword.showAndWait();
        } else {
                if (!username.equals("") && !password.equals("") && !email.equals("")) {
                    try {
                        DBSetup.init();
                        DBCollection usernameCheck = DBSetup.database.getCollection("LoginDetails");
                        DBCursor findIterable=usernameCheck.find();
                        for (DBObject count:findIterable) {
                            if( username.equals(count.get("Username"))) {
                                foundUsername = true;
                            }

                        }
                        if (foundUsername) {
                                Alert usernameExists = new Alert(Alert.AlertType.NONE);
                                usernameExists.setAlertType(Alert.AlertType.WARNING);
                                usernameExists.setContentText("Username already exists. Please enter another username");
                                usernameExists.showAndWait();

                        } else {
                            BasicDBObject basicDBObject1 = new BasicDBObject();
                            basicDBObject1.put("Username", username);
                            basicDBObject1.put("Password", password);
                            DBSetup.init();
                            DBCollection collection = DBSetup.database.getCollection("LoginDetails");
                            collection.insert(basicDBObject1);
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Account created successfully. You may now login");
                            a.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.OK) {
                                    Controller.createAccStage.close();
                                    Main.Primarystage.show();
                                }
                            });

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert emptyDetails = new Alert(Alert.AlertType.NONE);
                    emptyDetails.setAlertType(Alert.AlertType.WARNING);
                    emptyDetails.setContentText("All details have not been entered. Please fill them");
                    emptyDetails.showAndWait();
                }
            }

    }
    @FXML
    public void goBacktoMain() throws Exception{
        Controller.createAccStage.close();
        Main.Primarystage.show();
    }
}
