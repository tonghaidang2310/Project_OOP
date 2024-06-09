package demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
// import java.sql.ResultSet;

import demo.Data.DataBase;
import demo.Entity.Student;

public class StudentDAO {
    private Connection connect;
    private PreparedStatement prepare;
    // private ResultSet result;

    public void addStudentId(String StudentID, String firstName, String lastName, String gender){
        try{
            connect = DataBase.connecDb();
            String sql = "INSERT INTO Student (StudentID, firstName, lastName, gender) VALUES (?, ?, ?, ?)";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, StudentID);
            prepare.setString(2, firstName);
            prepare.setString(3, lastName);
            prepare.setString(4, gender);

            prepare.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addStudentId(String ID, Student student){
        try{
            connect = DataBase.connecDb();
            String sql = "INSERT INTO Student (StudentID, firstName, lastName, address, PhoneNumber, Email, Gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, ID);
            prepare.setString(2, student.getFirstName());
            prepare.setString(3, student.getLastName());
            prepare.setString(4, student.getAddress());
            prepare.setString(5, student.getPhoneNumber());
            prepare.setString(6, student.getEmail());
            prepare.setString(7, student.getGender());

            prepare.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
