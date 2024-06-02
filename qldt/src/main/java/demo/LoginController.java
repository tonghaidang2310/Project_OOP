package demo;

import java.io.IOException;
import java.sql.ResultSet;

import demo.DAO.AccountDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController {

    @FXML
    private Button cLose;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signUpBtn;

    private ResultSet result;
    
    public void login(){
        String user = username.getText();
        String pass = password.getText();

        try{
            result = new AccountDAO().loginAccount(user, pass);

            Alert alert;

            if(user.isEmpty() || pass.isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields");
                alert.showAndWait();
            }else{
                if(result.next()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Successful");
                    alert.showAndWait();
                    switchToDashBoard();
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
        }catch(Exception e){    e.printStackTrace();}    
    }

    

    @FXML
    private void switchToSignUp() throws IOException {
        App.setRoot("SignUp");
    }

    @FXML
    private void switchToDashBoard() throws IOException {
        App.setRoot("DashBoard");
    }
    
    @FXML
    private void close() {
        System.exit(0);
    }
}
