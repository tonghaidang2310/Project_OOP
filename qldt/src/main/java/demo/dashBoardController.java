package demo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import demo.Account.CurrentAccount;
import demo.Course.ClassSection;
import demo.Course.Course;
import demo.DAO.AccountDAO;
import demo.DAO.ClassSectionDAO;
import demo.DAO.CourseDAO;
import demo.Entity.Lecturer;
import demo.Entity.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class dashBoardController implements Initializable{
    protected CurrentAccount currentAccount;
    protected Student student;
    protected Lecturer lecturer;

    @FXML
    private Button ChangePass_btn;

    @FXML
    private Button Course_btn;

    @FXML
    private TableColumn<?, ?> Course_col_Class;

    @FXML
    private TableColumn<?, ?> Course_col_ID;

    @FXML
    private TableColumn<?, ?> Course_col_STT;

    @FXML
    private TableColumn<?, ?> Course_col_name;

    @FXML
    private TableColumn<?, ?> Course_col_schoolYear;

    @FXML
    private TableColumn<?, ?> Course_col_studentNumber;

    @FXML
    private AnchorPane Course_form;

    @FXML
    private TextField Course_search;

    @FXML
    private Button DashBoard_btn;

    @FXML
    private Button InboxForm_btn;

    @FXML
    private AnchorPane Inbox_InboxForm;

    @FXML
    private AnchorPane Inbox_SentForm;

    @FXML
    private Button Inbox_btn;

    @FXML
    private AnchorPane Inbox_form;

    @FXML
    private Button Info_btn;

    @FXML
    private Button Register_btn;

    @FXML
    private TableColumn<Course, String> Register_CourseID_col;

    @FXML
    private TableColumn<Course, String> Register_CourseName_col;

    @FXML
    private TableView<Course> Register_CourseName_table;

    @FXML
    private TableColumn<Course, Integer> Register_Credits_col;

    @FXML
    private TableView<Course> Register_Info_table;

    @FXML
    private TableColumn<Course, Double> Register_Tuition_col;

    @FXML
    private AnchorPane Register_form;

    @FXML
    private ComboBox<String> Register_status;

    @FXML
    private TextField Register_search;

    @FXML
    private VBox Register_SC_layout;

    @FXML
    private Button Schedule_btn;

    @FXML
    private AnchorPane Schedule_form;

    @FXML
    private Button Send_btn;

    @FXML
    private AnchorPane Setting_InFo_Form;

    @FXML
    private Button Setting_btn;

    @FXML
    private AnchorPane Setting_changeInFo_form;

    @FXML
    private AnchorPane Setting_changePass_form;

    @FXML
    private AnchorPane Setting_form;

    @FXML
    private Button changeInfo_btn;

    @FXML
    private Button close;

    @FXML
    private Label dashBoard_NOC;

    @FXML
    private AnchorPane dashBoard_Noti;

    @FXML
    private AnchorPane dashBoard_Tuition;

    @FXML
    private AnchorPane dashBoard_form;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Label userName;


    // Course

    // Register
    private String[] status = {"All", "Registered", "Not registered"};

    public void Register_status(){
        List<String> listStatus = new ArrayList<>();

        for(String s : status){
            listStatus.add(s);
        }

        ObservableList<String> listData = FXCollections.observableArrayList(listStatus);

        Register_status.setItems(listData);
    }

    private ObservableList<Course> listData = new CourseDAO().getCourses();

    public void registerShowNameCourse(){
        Register_CourseName_col.setCellValueFactory(new PropertyValueFactory<>("nameCourse"));

        Register_CourseName_table.setItems(listData);
    }

    public void actionClickNameCourse(MouseEvent e){
        registerShowInfoCourse();
        setCourseSectionData();
    }

    public void registerShowInfoCourse(){
        Course course = Register_CourseName_table.getSelectionModel().getSelectedItem();

        int index = Register_CourseName_table.getSelectionModel().getSelectedIndex();

        if((index - 1) < -1){
            return;
        }

        Register_CourseID_col.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        Register_Tuition_col.setCellValueFactory(new PropertyValueFactory<>("tuitionFee"));
        Register_Credits_col.setCellValueFactory(new PropertyValueFactory<>("credits"));

        ObservableList<Course> data = FXCollections.observableArrayList(course);
        
        Register_Info_table.setItems(data);
    }

    public void setCourseSectionData(){
        Course course = Register_CourseName_table.getSelectionModel().getSelectedItem();
        String courseID = course.getCourseID();
        List<ClassSection> classSectionList = new ClassSectionDAO().getClassSections(courseID);

        for(ClassSection classSection : classSectionList){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseSection.fxml"));
            try {
                HBox hbox = loader.load();
                CourseSectionController controller = loader.getController();
                controller.setData(classSection);
                Register_SC_layout.getChildren().add(hbox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerSearchCourse(){
        Register_search.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<Course> filteredData = new FilteredList<>(listData, p -> true);

            filteredData.setPredicate(course -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (course.getNameCourse().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (course.getCourseID().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else{
                    return false;
                }
            });

            SortedList<Course> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(Register_CourseName_table.comparatorProperty());
            Register_CourseName_table.setItems(sortedData);
        });
    }

    // Switch Form
    public void switchForm(ActionEvent e){
        if(e.getSource() == DashBoard_btn){
            Course_form.setVisible(false);
            Register_form.setVisible(false);
            Schedule_form.setVisible(false);
            Setting_form.setVisible(false);
            Inbox_form.setVisible(false);
            dashBoard_form.setVisible(true);
            setStyleButton(DashBoard_btn);
        }else if(e.getSource() == Course_btn){
            Course_form.setVisible(true);
            Register_form.setVisible(false);
            Schedule_form.setVisible(false);
            Setting_form.setVisible(false);
            Inbox_form.setVisible(false);
            dashBoard_form.setVisible(false);
            setStyleButton(Course_btn);
        }else if(e.getSource() == Register_btn){
            Course_form.setVisible(false);
            Register_form.setVisible(true);
            Schedule_form.setVisible(false);
            Setting_form.setVisible(false);
            Inbox_form.setVisible(false);
            dashBoard_form.setVisible(false);
            setStyleButton(Register_btn);

            registerShowNameCourse();
            registerSearchCourse();

        }else if(e.getSource() == Schedule_btn){
            Course_form.setVisible(false);
            Register_form.setVisible(false);
            Schedule_form.setVisible(true);
            Setting_form.setVisible(false);
            Inbox_form.setVisible(false);
            dashBoard_form.setVisible(false);
            setStyleButton(Schedule_btn);
        }else if(e.getSource() == Setting_btn){
            Course_form.setVisible(false);
            Register_form.setVisible(false);
            Schedule_form.setVisible(false);
            Setting_form.setVisible(true);
            Inbox_form.setVisible(false);
            dashBoard_form.setVisible(false);
            setStyleButton(Setting_btn);
        }else if(e.getSource() == InboxForm_btn){
            Course_form.setVisible(false);
            Register_form.setVisible(false);
            Schedule_form.setVisible(false);
            Setting_form.setVisible(false);
            Inbox_form.setVisible(true);
            dashBoard_form.setVisible(false);
            setStyleButton(InboxForm_btn);
        }
    }

    public void switchSettingForm(ActionEvent e){
        if(e.getSource() == changeInfo_btn){
            Setting_changeInFo_form.setVisible(true);
            Setting_changePass_form.setVisible(false);
            Setting_InFo_Form.setVisible(false);
        }else if(e.getSource() == ChangePass_btn){
            Setting_changeInFo_form.setVisible(false);
            Setting_changePass_form.setVisible(true);
            Setting_InFo_Form.setVisible(false);
        }else if(e.getSource() == Info_btn){
            Setting_changeInFo_form.setVisible(false);
            Setting_changePass_form.setVisible(false);
            Setting_InFo_Form.setVisible(true);
        }
    }

    public void switchInboxForm(ActionEvent e){
        if(e.getSource() == Inbox_btn){
            Inbox_InboxForm.setVisible(true);
            Inbox_SentForm.setVisible(false);
        }else if(e.getSource() == Send_btn){
            Inbox_InboxForm.setVisible(false);
            Inbox_SentForm.setVisible(true);
        }
    }

    public void setStyleButton(Button buttonName){
        String choseStyle = "-fx-background-color: #3796a7; background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px; border-width: 0px;";
        String unChoseStyle = "-fx-background-color: transparent; background-color: transparent; -fx-font-size: 14px; font-size: 14px; -fx-font-family: Arial; font-family: Arial; -fx-cursor:hand; cursor:hand; -fx-text-fill: #000; -fx-border-color: #ddd; border-color: #ddd; -fx-border-width: 1px; border-width: 1px;";
        Button[] buttons = {DashBoard_btn, Course_btn, Register_btn, Schedule_btn, Setting_btn, InboxForm_btn};

        for(Button b : buttons){
            if(b == buttonName){
                b.setStyle(choseStyle);
            }else{
                b.setStyle(unChoseStyle);
            }
        }
        
    }

    public void setStyleSettingButton(Button buttonName){
        String choseStyle = "-fx-background-color: #3796a7; background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px; border-width: 0px;";
        String unChoseStyle = "-fx-background-color: transparent; background-color: transparent; -fx-font-size: 14px; font-size: 14px; -fx-font-family: Arial; font-family: Arial; -fx-cursor:hand; cursor:hand; -fx-text-fill: #000; -fx-border-color: #ddd; border-color: #ddd; -fx-border-width: 1px; border-width: 1px;";
        Button[] buttons = {changeInfo_btn, ChangePass_btn, Info_btn};

        for(Button b : buttons){
            if(b == buttonName){
                b.setStyle(choseStyle);
            }else{
                b.setStyle(unChoseStyle);
            }
        }
    }

    public void setStyleInboxButton(Button buttonName){
        String choseStyle = "-fx-background-color: #3796a7; background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px; border-width: 0px;";
        String unChoseStyle = "-fx-background-color: transparent; background-color: transparent; -fx-font-size: 14px; font-size: 14px; -fx-font-family: Arial; font-family: Arial; -fx-cursor:hand; cursor:hand; -fx-text-fill: #000; -fx-border-color: #ddd; border-color: #ddd; -fx-border-width: 1px; border-width: 1px;";
        Button[] buttons = {Inbox_btn, Send_btn};

        for(Button b : buttons){
            if(b == buttonName){
                b.setStyle(choseStyle);
            }else{
                b.setStyle(unChoseStyle);
            }
        }
    }

    private double x = 0;
    private double y = 0;

    @FXML
    private void switchToLogin() throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
        Parent root = loader.load();

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
    private void dashBoard(){
        
    }

    @FXML
    private void close(){
        new AccountDAO().removeCurrentAccount(currentAccount.getAcountID());
        currentAccount = null;
        System.exit(0);
    }

    public void minimize(){
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void displayUserName(){
        this.currentAccount = new AccountDAO().getCurrentAccount();
        String userType = this.currentAccount.getTypeAccount();
        if(userType.equals("Student")){
            this.student = (Student) new AccountDAO().getInfoPerson(currentAccount.getStudentID(), "Student");
        }else{
            this.lecturer = (Lecturer) new AccountDAO().getInfoPerson(currentAccount.getLecturerID(), "Lecturer");
        }
        String user = userType.equals("Student") ? student.getFirstName() + " " + student.getLastName() : lecturer.getFirstName() + " " + lecturer.getLastName();
        userName.setText(user);
    }

    public void logout(){
        try{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();
                new AccountDAO().removeCurrentAccount(currentAccount.getAcountID());
                currentAccount = null;
                switchToLogin();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUserName();
        Register_status();
        registerShowNameCourse();
        
    }
}
