package demo;

import java.net.URL;
import java.util.ResourceBundle;

import demo.Course.ClassSection;
import demo.DAO.LecturerDAO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CourseSectionController implements Initializable{

    @FXML
    private Label CourseName;

    @FXML
    private Label Enrolled;

    @FXML
    private Label LecturerName;

    @FXML
    private Button register_btn;

    public void setData(ClassSection classSection){
        CourseName.setText(classSection.getClassSectionName());
        Enrolled.setText(String.valueOf(classSection.getEnrolledStudents()));
        LecturerName.setText(new LecturerDAO().getNameLecturer(classSection.getLecturerID()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
