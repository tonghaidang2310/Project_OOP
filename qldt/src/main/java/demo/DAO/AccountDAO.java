package demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import demo.Data.DataBase;
import demo.Entity.Student;

public class AccountDAO {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public String generateUniqueStudentID() {
        String newStudentID = "";
        try {
            // Kết nối tới cơ sở dữ liệu
            connect = DataBase.connecDb();
            
            // Lấy ID sinh viên lớn nhất hiện có
            String sql = "SELECT MAX(StudentID) FROM Student";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                String maxID = result.getString(1);
                if (maxID != null) {
                    int maxNum = Integer.parseInt(maxID.split("-")[1]);
                    newStudentID = String.format("SV-%03d", maxNum + 1);
                } else {
                    newStudentID = "SV-001";
                }
            } else {
                newStudentID = "SV-001";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newStudentID;
    }

    public String generateUniqueLecturerID() {
        String newLecturerID = "";
        try {
            // Kết nối tới cơ sở dữ liệu
            connect = DataBase.connecDb();
            
            // Lấy ID giảng viên lớn nhất hiện có
            String sql = "SELECT MAX(LecturerID) FROM Lecturer";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                String maxID = result.getString(1);
                if (maxID != null) {
                    int maxNum = Integer.parseInt(maxID.split("-")[1]);
                    newLecturerID = String.format("GV-%03d", maxNum + 1);
                } else {
                    newLecturerID = "GV-001";
                }
            } else {
                newLecturerID = "GV-001";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newLecturerID;
    }

    public void signUpAccount(String username, String password, String typeUser, Student s){
        String id = typeUser.equals("Student") ? generateUniqueStudentID() : generateUniqueLecturerID();
        new StudentDAO().addStudentId(id, s);
        try{
            connect = DataBase.connecDb();
            String sql = "INSERT INTO account (username, password, UserType, " + typeUser + "ID" +  ") VALUES (?, ?, ?, ?)";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username);
            prepare.setString(2, password);
            prepare.setString(3, typeUser);
            prepare.setString(4, id);

            prepare.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet loginAccount(String username, String password){
        try{
            connect = DataBase.connecDb();
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username);
            prepare.setString(2, password);

            result = prepare.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
