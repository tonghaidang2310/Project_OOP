package demo.DAO;

/*
 * Class này dùng để lưu trữ thông tin tiến trình học tập của sinh viên
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import demo.Course.ClassSection;
import demo.Data.DataBase;

public class StudentCourseProgressDAO {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    // Hàm tạo một ID mới cho tiến trình học tập của sinh viên
    public int generateUniqueStudentCourseProgressID(){
        try{
            connect = DataBase.connecDb();
            prepare = connect.prepareStatement("SELECT COUNT(*) FROM StudentCourseProgress");
            result = prepare.executeQuery();
            if(result.next()){
                return result.getInt(1) + 1;
            }
            return 0;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    // Hàm thêm một tiến trình học tập mới cho sinh viên
    public void addStudentCourseProgress(String studentID, String courseID, String classSectionID){
        try{
            connect = DataBase.connecDb();
            String sql = "INSERT INTO StudentCourseProgress (ProgressID, studentID, courseID, classSectionID VALUES (?, ?, ?, ?)";
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, generateUniqueStudentCourseProgressID());
            prepare.setString(2, studentID);
            prepare.setString(3, courseID);
            prepare.setString(4, classSectionID);

            prepare.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Hàm cập nhật trạng thái học tập của sinh viên
    public void updateStatus(String studentID, String courseID, String classSectionID, double grade){
        String status = (grade > 4.0) ? "Completed" : "Failed";
        try{
            connect = DataBase.connecDb();
            String sql = "UPDATE StudentCourseProgress SET status = ? WHERE studentID = ? AND courseID = ? AND classSectionID = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, status);
            prepare.setString(2, studentID);
            prepare.setString(3, courseID);
            prepare.setString(4, classSectionID);

            prepare.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Hàm lấy danh sách các lớp học phần mà sinh viên đã đăng ký
    public List<ClassSection> getStudentClassSections(String studentID, String status){
        List<ClassSection> classSections = new ArrayList<>();
        try{
            connect = DataBase.connecDb();
            String sql = "SELECT * FROM StudentCourseProgress WHERE studentID = ? AND status = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, studentID);
            prepare.setString(2, status);

            result = prepare.executeQuery();
            while(result.next()){
                String classSectionID = result.getString("classSectionID");
                ClassSectionDAO classSectionDAO = new ClassSectionDAO();
                ClassSection classSection = classSectionDAO.getClassSection(classSectionID);
                classSections.add(classSection);
            }
            return classSections;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
