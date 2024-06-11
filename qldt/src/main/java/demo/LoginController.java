package demo;

/*
 * Class này dùng để xử lý các sự kiện liên quan đến việc đăng nhập
 */

import java.io.IOException;

import demo.Account.LecturerAccount;
import demo.Account.StudentAccount;
import demo.DAO.AccountDAO;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {

    private double x = 0;
    private double y = 0;

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
    
    public void login(){
        String user = username.getText();
        String pass = password.getText();

        try{
            boolean result = new AccountDAO().loginAccount(user, pass);

            Alert alert;

            if(user.isEmpty() || pass.isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields");
                alert.showAndWait();
            }else{
                if(result){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Successful");
                    alert.showAndWait();
                    AccountDAO accountDAO = new AccountDAO();
                    String userType = accountDAO.checkTypeAccount(user);
                    if(userType.equals("Student"))
                        accountDAO.addCurrentAccount( (StudentAccount) new AccountDAO().getAccount(user, pass));
                    else if(userType.equals("Lecturer"))
                        accountDAO.addCurrentAccount( (LecturerAccount) new AccountDAO().getAccount(user, pass));
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
        signUpBtn.getScene().getWindow().hide();
        Parent root = App.loadFXML("SignUp");

        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

            stage.setOpacity(0.8);
        });

        root.setOnMouseReleased((MouseEvent event) -> {
            stage.setOpacity(1.0);
        });

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToDashBoard() throws IOException {
        loginBtn.getScene().getWindow().hide();
        Parent root = App.loadFXML("Dashboard");
        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

            stage.setOpacity(0.8);
        });

        root.setOnMouseReleased((MouseEvent event) -> {
            stage.setOpacity(1.0);
        });

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void close() {
        System.exit(0);
    }
}
