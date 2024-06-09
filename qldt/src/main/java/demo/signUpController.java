package demo;

import demo.DAO.AccountDAO;
import demo.Entity.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class signUpController {
    
    @FXML
    private Button cLose;

     @FXML
    private ComboBox<String> chooseGender;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField repassword;

    @FXML
    private Button returnBtn;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField username;

    ObservableList<String> Genderlist = FXCollections.observableArrayList("Male", "Female", "Other");

    @FXML
    private void initialize(){
        chooseGender.setItems(Genderlist);
    }

    private double x = 0;
    private double y = 0;

    public void signUp(){
        String user = username.getText();
        String pass = password.getText();
        String repass = repassword.getText();
        String gender = chooseGender.getValue();
        String fName = firstName.getText();
        String lName = lastName.getText();

        Alert alert;

        if(user.isEmpty() || pass.isEmpty() || repass.isEmpty() || fName.isEmpty() || lName.isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }else if(!pass.equals(repass)){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Password does not match");
            alert.showAndWait();
        }else if(gender == null){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please choose gender");
            alert.showAndWait();
        }else{
            Student student = new Student(fName, lName, gender);
            AccountDAO accountDAO = new AccountDAO();
            accountDAO.signUpAccount(user, pass, "Student", student);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Sign up successfully");
            alert.showAndWait();
            switchToLogin();
        }

    }

    @FXML
    private void close() {
        System.exit(0);
    }

    @FXML
    private void back(){
        returnBtn.getScene().getWindow().hide();
        Parent root = App.loadFXML("SignIn");
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
    private void switchToLogin() {
        signUpBtn.getScene().getWindow().hide();
        Parent root = App.loadFXML("SignIn");
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
}