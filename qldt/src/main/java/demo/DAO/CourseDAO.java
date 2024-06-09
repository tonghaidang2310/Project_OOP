package demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import demo.Course.Course;
import demo.Data.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseDAO {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public ObservableList<Course> getCourses() {
        ObservableList<Course> courseList = FXCollections.observableArrayList();
        try {
            connect = DataBase.connecDb();
            prepare = connect.prepareStatement("SELECT * FROM course");
            result = prepare.executeQuery();
            while (result.next()) {
                Course course = new Course(result.getString("courseID"), result.getString("nameCourse"),
                        result.getDouble("tuitionFee"), result.getInt("credits"));
                courseList.add(course);
            }
            return courseList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> Object getCourseInfo(String courseName){
        ObservableList<Course> courseList = FXCollections.observableArrayList();
        try {
            connect = DataBase.connecDb();
            prepare = connect.prepareStatement("SELECT * FROM course WHERE nameCourse = ?");
            prepare.setString(1, courseName);
            result = prepare.executeQuery();
            while (result.next()) {
                Course course = new Course(result.getString("courseID"), result.getString("nameCourse"),
                        result.getDouble("tuitionFee"), result.getInt("credits"));
                courseList.add(course);
            }
            return courseList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
