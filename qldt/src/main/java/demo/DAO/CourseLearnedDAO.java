package demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import demo.Data.DataBase;

public class CourseLearnedDAO {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void addCourseLearned(String studentID, String courseID) {
        try {
            connect = DataBase.connecDb();
            prepare = connect.prepareStatement("INSERT INTO courselearned(studentID, courseID) VALUES(?, ?)");
            prepare.setString(1, studentID);
            prepare.setString(2, courseID);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkCourseLearned(String studentID, String courseID) {
        try {
            connect = DataBase.connecDb();
            prepare = connect.prepareStatement("SELECT * FROM courselearned WHERE studentID = ? AND courseID = ?");
            prepare.setString(1, studentID);
            prepare.setString(2, courseID);
            result = prepare.executeQuery();
            return result.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
