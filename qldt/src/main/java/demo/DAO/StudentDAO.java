package demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

import demo.Data.DataBase;
import demo.Entity.Student;

public class StudentDAO {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    // Hàm thêm một sinh viên vào bảng Student
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

    // Hàm thêm một sinh viên vào bảng Student
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

    public void updateStudentInfo(String ID, Student student){
        try{
            connect = DataBase.connecDb();
            String sql = "UPDATE Student SET firstName = ?, lastName = ?, address = ?, PhoneNumber = ?, Email = ?, gender = ? WHERE StudentID = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, student.getFirstName());
            prepare.setString(2, student.getLastName());
            prepare.setString(3, student.getAddress());
            prepare.setString(4, student.getPhoneNumber());
            prepare.setString(5, student.getEmail());
            prepare.setString(6, student.getGender());
            prepare.setString(7, ID);

            prepare.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Student> getStudentsByClassSectionId(String classSectionId) {
        List<String> studentID = new ClassSectionDAO().getStudentIdByClassSectionId(classSectionId);
        List<Student> students = new ArrayList<>();
        try{
            connect = DataBase.connecDb();
            for (String id : studentID) {
                prepare = connect.prepareStatement("SELECT * FROM Student WHERE StudentID = ?");
                prepare.setString(1, id);
                result = prepare.executeQuery();
                while (result.next()) {
                    Student student = new Student(result.getString("firstName"), result.getString("lastName"), result.getString("PhoneNumber"),
                            result.getString("Email"), result.getString("Gender"), result.getString("id"), result.getString("Address"));
                    students.add(student);
                }
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public String getStudentName(String studentId) {
        try {
            connect = DataBase.connecDb();
            prepare = connect.prepareStatement("SELECT * FROM Student WHERE StudentID = ?");
            prepare.setString(1, studentId);
            result = prepare.executeQuery();
            if (result.next()) {
                return result.getString("firstName") + " " + result.getString("lastName");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
