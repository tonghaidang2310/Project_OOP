package demo;

/*
 * Class này dùng để hiển thị giao diện của trang chính
 */

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
import demo.DAO.InboxDAO;
import demo.DAO.StudentClassSectionDAO;
import demo.DAO.StudentDAO;
import demo.Entity.Inbox;
import demo.Entity.Lecturer;
import demo.Entity.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
    private ComboBox<String> Course_choose_status;

    @FXML
    private Button Course_btn;

    @FXML
    private TableColumn<?, ?> Course_col_Status;

    @FXML
    private TableColumn<?, ?> Course_col_ID;

    @FXML
    private TableColumn<?, ?> Course_col_STT;

    @FXML
    private TableColumn<?, ?> Course_col_name;

    @FXML
    private TableColumn<?, ?> Course_col_schoolYear;

    @FXML
    private TableColumn<?, ?> Course_col_grade;

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
    private ComboBox<String> Inbox_Course;

    @FXML
    private ComboBox<String> Inbox_Receiver;

    @FXML
    private VBox Inbox_Inbox_table;

    @FXML
    private HBox Inbox_Inbox_Table_Tiltle;

    @FXML
    private Button Inbox_Inbox_Delete_btn;

    @FXML
    private TextField Inbox_Inbox_Search;

    @FXML
    private VBox Inbox_Sent_Table;

    @FXML
    private HBox Inbox_Sent_Table_Tiltle;

    @FXML
    private TextField Inbox_Tiltle;

    @FXML
    private AnchorPane Inbox_WriteForm;

    @FXML
    private TextArea Inbox_body;

    @FXML
    private ComboBox<String> Inbox_position;

    @FXML
    private Button Inbox_send_btn;

    @FXML
    private Button Inbox_btn;

    @FXML
    private AnchorPane Inbox_form;

    @FXML
    private Button Write_btn;

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
    private HBox Register_SC_Title;

    @FXML
    private Button Schedule_btn;

    @FXML
    private AnchorPane Schedule_form;

    @FXML
    private Button Send_btn;

    @FXML
    private TextField Setting_ChangeInfo_City;

    @FXML
    private TextField Setting_ChangeInfo_District;

    @FXML
    private TextField Setting_ChangeInfo_FName;

    @FXML
    private ComboBox<String> Setting_ChangeInfo_Gender;

    @FXML
    private TextField Setting_ChangeInfo_Email;

    @FXML
    private TextField Setting_ChangeInfo_LName;

    @FXML
    private TextField Setting_ChangeInfo_Phone;

    @FXML
    private Button Setting_ChangeInfo_Save_btn;

    @FXML
    private TextField Setting_ChangeInfo_Street;

    @FXML
    private TextField Setting_ChangeInfo_Ward;

    @FXML
    private AnchorPane Setting_InFo_Form;

    @FXML
    private Label Setting_InFo_Address;

    @FXML
    private Label Setting_InFo_Email;

    @FXML
    private Label Setting_InFo_FName;

    @FXML
    private Label Setting_InFo_Gender;

    @FXML
    private Label Setting_InFo_ID;

    @FXML
    private Label Setting_InFo_LName;

    @FXML
    private Label Setting_InFo_PN;

    @FXML
    private Button Setting_btn;

    @FXML
    private AnchorPane Setting_changeInFo_form;

    @FXML
    private AnchorPane Setting_changePass_form;

    @FXML
    private TextField Setting_ChangePass_ComfirmNewPass;

    @FXML
    private TextField Setting_ChangePass_NewPass;

    @FXML
    private TextField Setting_ChangePass_OldPass;

    @FXML
    private Button Setting_ChangePass_Save_btn;

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
    private String[] courseStatus = {"All", "Completed", "Fail", "In progress"};

    public void Course_status(){
        List<String> listStatus = new ArrayList<>();

        for(String s : courseStatus){
            listStatus.add(s);
        }

        ObservableList<String> listData = FXCollections.observableArrayList(listStatus);

        Course_choose_status.setItems(listData);
    }

    // Register
    private String[] registerStatus = {"All", "Registered", "Not registered"};

    public void Register_status(){
        List<String> listStatus = new ArrayList<>();

        for(String s : registerStatus){
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
        Register_SC_layout.getChildren().clear();
        Register_SC_layout.getChildren().add(Register_SC_Title);
        String status = Register_status.getSelectionModel().getSelectedItem();
        if(status == null){    setCourseSectionData(); }
        else if(status.equals("All")){    setCourseSectionData();}
        else if(status.equals("Not registered")){    registerNotRegisteredCourseSection();}
        else if(status.equals("Registered")){    registerRegisteredCourseSection();}
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseSectionRegister.fxml"));
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

    public void registerNotRegisteredCourseSection(){
        Course course = Register_CourseName_table.getSelectionModel().getSelectedItem();
        String courseID = course.getCourseID();
        List<ClassSection> classSectionList = new ClassSectionDAO().getClassSections(courseID);

        for(ClassSection classSection : classSectionList){
            if(!(new StudentClassSectionDAO().isEnrolled(classSection.getClassSectionID(), currentAccount.getStudentID()))){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseSectionRegister.fxml"));
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
    }

    public void registerRegisteredCourseSection(){
        Course course = Register_CourseName_table.getSelectionModel().getSelectedItem();
        String courseID = course.getCourseID();
        List<ClassSection> classSectionList = new ClassSectionDAO().getClassSections(courseID);

        for(ClassSection classSection : classSectionList){
            if(new StudentClassSectionDAO().isEnrolled(classSection.getClassSectionID(), currentAccount.getStudentID())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseSectionUnRegister.fxml"));
                try {
                    HBox hbox = loader.load();
                    CourseSectionUnRegisterController controller = loader.getController();
                    controller.setData(classSection);
                    Register_SC_layout.getChildren().add(hbox);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    // Inbox

    private String[] inboxPosition = {"All", "Student", "Lecturer"};
    private List<String> inboxReceiverID = new ArrayList<>();
    private List<String> studentNameList = new ArrayList<>();

    public void Inbox_position(){
        List<String> listPosition = new ArrayList<>();

        for(String s : inboxPosition){
            listPosition.add(s);
        }

        ObservableList<String> listData = FXCollections.observableArrayList(listPosition);

        Inbox_position.setItems(listData);
    }

    private List<String> getInboxClassSection(){
        List<String> listClassSection = new ArrayList<>();
        List<String> classSectionList = new StudentClassSectionDAO().getStudentClassSections(currentAccount.getStudentID());

        for(String classSection : classSectionList){
            listClassSection.add(new ClassSectionDAO().getClassSectionName(classSection));
        }

        return listClassSection;
    }

    public void Inbox_Course(){
        List<String> listData = getInboxClassSection();

        ObservableList<String> list = FXCollections.observableArrayList(listData);

        Inbox_Course.setItems(list);
    }

    private List<String> getInboxReceiver(String classSectionName, String position){
        List<String> listReceiver = new ArrayList<>();

        String classSectionID = new ClassSectionDAO().getClassSectionID(classSectionName);
        List<String> studentList = new StudentClassSectionDAO().getStudents(classSectionID);
        
        inboxReceiverID.clear();
        studentNameList.clear();

        for(String studentID : studentList){
            studentNameList.add(new StudentDAO().getStudentName(studentID));
            inboxReceiverID.add(studentID);
        }
        String lecturer = new ClassSectionDAO().getLecturerName(classSectionID);

        if(position.equals("All")){
            listReceiver.add(lecturer);
            listReceiver.addAll(studentNameList);
        }else if(position.equals("Student")){
            listReceiver.addAll(studentNameList);
        }else if(position.equals("Lecturer")){
            listReceiver.add(lecturer);
        }

        return listReceiver;
    }

    public void Inbox_Receiver(String classSectionName, String position){
        List<String> listData = getInboxReceiver(classSectionName, position);

        ObservableList<String> list = FXCollections.observableArrayList(listData);

        Inbox_Receiver.setItems(list);
    }

    public void setInbox(){
        Inbox_Receiver.setVisible(false);
        Inbox_position();
        Inbox_Course();
        Inbox_Course.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String classSectionName = newValue;
            String position = Inbox_position.getSelectionModel().getSelectedItem();
            if(classSectionName != null && position != null){
                Inbox_Receiver.setVisible(true);
                Inbox_Receiver(classSectionName, position);
            }
        });
        
        Inbox_position.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String classSectionName = Inbox_Course.getSelectionModel().getSelectedItem();
            String position = newValue;
            if(classSectionName != null && position != null){
                Inbox_Receiver.setVisible(true);
                Inbox_Receiver(classSectionName, position);
            }
        });
    }
    
    private String getReceiverID(String receiverName){
        int index = studentNameList.indexOf(receiverName);
        if(index == -1){
            return null;
        }else{
            return inboxReceiverID.get(index);
        }
    }

    public void sendInbox(){
        int senderAccountID = currentAccount.getAccountID();
        String receiverID = getReceiverID(Inbox_Receiver.getSelectionModel().getSelectedItem());
        int receiverAccountID = new AccountDAO().getAccountId(receiverID);
        String tiltle = Inbox_Tiltle.getText();
        String body = Inbox_body.getText();
        String classSectionId = new ClassSectionDAO().getClassSectionID(Inbox_Course.getSelectionModel().getSelectedItem());

        if(receiverID.isEmpty() || tiltle.isEmpty() || body.isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
        }else{
            new InboxDAO().addInbox(senderAccountID, receiverAccountID, tiltle, body, classSectionId);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Message sent successfully");
            alert.showAndWait();
        }
    }

    public void setInboxData(){
        List<Inbox> listInbox = new InboxDAO().getReceivedInboxs(currentAccount.getAccountID());

        for(Inbox inbox : listInbox){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inbox.fxml"));
            String senderName = new InboxDAO().getSenderName(inbox.getSenderID());
            String tiltle = inbox.getTiltle();
            try {
                HBox hbox = loader.load();
                InboxController controller = loader.getController();
                controller.setData(senderName, tiltle);
                Inbox_Inbox_table.getChildren().add(hbox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSentData(){
        List<Inbox> listInbox = new InboxDAO().getSentInboxs(currentAccount.getAccountID());

        for(Inbox inbox : listInbox){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inbox.fxml"));
            String receiverName = new InboxDAO().getReceiverName(inbox.getReceiverID());
            String tiltle = inbox.getTiltle();
            try {
                HBox hbox = loader.load();
                InboxController controller = loader.getController();
                controller.setData(receiverName, tiltle);
                Inbox_Sent_Table.getChildren().add(hbox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Setting
    private String[] genderStatus = {"Male", "Female", "Other"};

    public void ChangeInfoGender_status(){
        List<String> listGender = new ArrayList<>();

        for(String s : genderStatus){
            listGender.add(s);
        }

        ObservableList<String> listData = FXCollections.observableArrayList(listGender);
        Setting_ChangeInfo_Gender.setItems(listData);
    }

    public void setSettingChangeInfoData(){
        String userType = currentAccount.getTypeAccount();
        if(userType.equals("Student")){
            student = (Student) new AccountDAO().getInfoPerson(currentAccount.getStudentID(), "Student");
            String[] parts;
            if(student.getAddress() != null) {
                parts = student.getAddress().split(" - ");
            }else{
                parts = new String[4];
            }
            Setting_ChangeInfo_FName.setText(student.getFirstName());
            Setting_ChangeInfo_LName.setText(student.getLastName());
            Setting_ChangeInfo_Phone.setText(student.getPhoneNumber());
            Setting_ChangeInfo_Street.setText(parts[0]);
            Setting_ChangeInfo_Ward.setText(parts[1]);
            Setting_ChangeInfo_District.setText(parts[2]);
            Setting_ChangeInfo_City.setText(parts[3]);
            Setting_ChangeInfo_Gender.setValue(student.getGender());
            Setting_ChangeInfo_Email.setText(student.getEmail());
        }
    }

    public void saveSettingChangeInfoData(){
        String userType = currentAccount.getTypeAccount();

        Alert alert;
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to change the information?");
        Optional<ButtonType> option = alert.showAndWait();

        if(option.get().equals(ButtonType.OK)){
            if(userType.equals("Student")){
                String studentID = currentAccount.getStudentID();
                String fName = Setting_ChangeInfo_FName.getText();
                String lName = Setting_ChangeInfo_LName.getText();
                String phone = Setting_ChangeInfo_Phone.getText();
                String street = Setting_ChangeInfo_Street.getText();
                String ward = Setting_ChangeInfo_Ward.getText();
                String district = Setting_ChangeInfo_District.getText();
                String city = Setting_ChangeInfo_City.getText();
                String gender = Setting_ChangeInfo_Gender.getValue();
                String email = Setting_ChangeInfo_Email.getText();
                String address = street + " - " + ward + " - " + district + " - " + city;

                Student student = new Student(fName, lName, phone, email, gender, null, address);
                new StudentDAO().updateStudentInfo(studentID, student);

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Information changed successfully");
                alert.showAndWait();

                displayUserName();
            }
        }
    }

    public void setSettingChangePassData(){
        Setting_ChangePass_OldPass.clear();
        Setting_ChangePass_NewPass.clear();
        Setting_ChangePass_ComfirmNewPass.clear();
    }

    public void changePassword(){
        setSettingChangePassData();
        currentAccount = new AccountDAO().getCurrentAccount();
        String oldPass = Setting_ChangePass_OldPass.getText();
        String newPass = Setting_ChangePass_NewPass.getText();
        String comfirmPass = Setting_ChangePass_ComfirmNewPass.getText();
        String currPass = currentAccount.getPassword();

        Alert alert;

        if(oldPass.isEmpty() || newPass.isEmpty() || comfirmPass.isEmpty()){
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
        }else if(!(new AccountDAO().checkPassword(oldPass, currPass))){
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Old password is incorrect");
            alert.showAndWait();
        }else if(!(newPass.equals(comfirmPass))){
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("New password and confirm password do not match");
            alert.showAndWait();
        }else{
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to change the password?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){
                new AccountDAO().changePassword(currentAccount.getAccountID(), newPass);
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Password changed successfully");
                alert.showAndWait();
                setSettingChangePassData();
            }
        }
    }

    public void setInfoData(){
        String userType = currentAccount.getTypeAccount();
        if(userType.equals("Student")){
            student = (Student) new AccountDAO().getInfoPerson(currentAccount.getStudentID(), "Student");
            Setting_InFo_ID.setText(student.getID());
            Setting_InFo_FName.setText(student.getFirstName());
            Setting_InFo_LName.setText(student.getLastName());
            Setting_InFo_PN.setText(student.getPhoneNumber());
            Setting_InFo_Email.setText(student.getEmail());
            Setting_InFo_Address.setText(student.getAddress());
            Setting_InFo_Gender.setText(student.getGender());
        }
    }

    // Switch Form
    public void switchForm(ActionEvent e){
        setStyleButton(DashBoard_btn);
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

            switchSettingForm(e);
        }else if(e.getSource() == InboxForm_btn){
            Course_form.setVisible(false);
            Register_form.setVisible(false);
            Schedule_form.setVisible(false);
            Setting_form.setVisible(false);
            Inbox_form.setVisible(true);
            dashBoard_form.setVisible(false);
            setStyleButton(InboxForm_btn);

            switchInboxForm(e);
            setInbox();
        }
    }

    public void switchSettingForm(ActionEvent e){
        if(e.getSource() == changeInfo_btn){
            Setting_changeInFo_form.setVisible(true);
            Setting_changePass_form.setVisible(false);
            Setting_InFo_Form.setVisible(false);
            setStyleSettingButton(changeInfo_btn);
        }else if(e.getSource() == ChangePass_btn){
            Setting_changeInFo_form.setVisible(false);
            Setting_changePass_form.setVisible(true);
            Setting_InFo_Form.setVisible(false);
            setStyleSettingButton(ChangePass_btn);
        }else if(e.getSource() == Info_btn){
            Setting_changeInFo_form.setVisible(false);
            Setting_changePass_form.setVisible(false);
            Setting_InFo_Form.setVisible(true);
            setStyleSettingButton(Info_btn);
        }
    }

    public void switchInboxForm(ActionEvent e){
        if(e.getSource() == Inbox_btn){
            Inbox_InboxForm.setVisible(true);
            Inbox_SentForm.setVisible(false);
            Inbox_WriteForm.setVisible(false);
            setStyleInboxButton(Inbox_btn);
            Inbox_Inbox_table.getChildren().clear();
            Inbox_Inbox_table.getChildren().add(Inbox_Inbox_Table_Tiltle);
            setInboxData();
        }else if(e.getSource() == Send_btn){
            Inbox_InboxForm.setVisible(false);
            Inbox_SentForm.setVisible(true);
            Inbox_WriteForm.setVisible(false);
            setStyleInboxButton(Send_btn);

            Inbox_Sent_Table.getChildren().clear();
            Inbox_Sent_Table.getChildren().add(Inbox_Sent_Table_Tiltle);
            setSentData();
        }else if(e.getSource() == Write_btn){
            Inbox_InboxForm.setVisible(false);
            Inbox_SentForm.setVisible(false);
            Inbox_WriteForm.setVisible(true);
            setStyleInboxButton(Write_btn);
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
        new AccountDAO().removeCurrentAccount(currentAccount.getAccountID());
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
                new AccountDAO().removeCurrentAccount(currentAccount.getAccountID());
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
        registerShowNameCourse();
        
        Course_status();
        Register_status();
        ChangeInfoGender_status();
        setInbox();

        setSettingChangeInfoData();
        setInfoData();

        currentAccount = new AccountDAO().getCurrentAccount();
    }
}
