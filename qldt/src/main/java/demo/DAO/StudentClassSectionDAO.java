package demo.DAO;

/*
 * Class này dùng để thực hiện các thao tác với bảng StudentClassSection trong cơ sở dữ liệu
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import demo.Data.DataBase;

public class StudentClassSectionDAO {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    // Hàm thêm một sinh viên vào một lớp học phần
    public void addStudentClassSection(String classSectionID, String studentID){
        try{
            connect = DataBase.connecDb();
            String sql = "INSERT INTO StudentClassSection (classSectionID, studentID) VALUES (?, ?)";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, classSectionID);
            prepare.setString(2, studentID);

            prepare.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // Hàm xóa một sinh viên khỏi một lớp học phần
    public void removeStudentClassSection(String classSectionID, String studentID){
        try{
            connect = DataBase.connecDb();
            String sql = "DELETE FROM StudentClassSection WHERE classSectionID = ? AND studentID = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, classSectionID);
            prepare.setString(2, studentID);

            prepare.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Hàm kiểm tra xem một sinh viên có đăng ký một lớp học phần không
    public boolean isEnrolled(String classSectionID, String studentID){
        try{
            connect = DataBase.connecDb();
            String sql = "SELECT * FROM StudentClassSection WHERE classSectionID = ? AND studentID = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, classSectionID);
            prepare.setString(2, studentID);

            result = prepare.executeQuery();
            return result.next();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // Hàm lấy danh sách các lớp học phần mà một sinh viên đã đăng ký
    public List<String> getStudentClassSections(String studentID){
        List<String> classSections = new ArrayList<>();
        try{
            connect = DataBase.connecDb();
            String sql = "SELECT * FROM StudentClassSection WHERE studentID = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, studentID);

            result = prepare.executeQuery();
            while(result.next()){
                classSections.add(result.getString("classSectionID"));
            }
            return classSections;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // Hàm lấy danh sách các sinh viên trong một lớp học phần
    public List<String> getStudents(String classSectionID){
        List<String> students = new ArrayList<>();
        try{
            connect = DataBase.connecDb();
            String sql = "SELECT * FROM StudentClassSection WHERE classSectionID = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, classSectionID);

            result = prepare.executeQuery();
            while(result.next()){
                students.add(result.getString("studentID"));
            }
            return students;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // Hàm lấy số lượng sinh viên đã đăng ký một lớp học phần
    public int getEnrolledStudents(String classSectionID){
        try{
            connect = DataBase.connecDb();
            String sql = "SELECT COUNT(*) FROM StudentClassSection WHERE classSectionID = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, classSectionID);

            result = prepare.executeQuery();
            result.next();
            return result.getInt(1);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public boolean checkRegistrationConditions(String classSectionID, String studentID){
        String[] parts = classSectionID.split("\\.");

        if(parts.length == 2){
            try{
                connect = DataBase.connecDb();
                String sql = "SELECT * FROM StudentClassSection WHERE studentID = ? AND classSectionID LIKE ? AND classSectionID NOT LIKE ?";
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, studentID);
                prepare.setString(2, parts[0] + ".N__");
                prepare.setString(3, parts[0] + ".N__." + ".%");

                result = prepare.executeQuery();
                return !result.next();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }else if(parts.length == 3){
            try{
                connect = DataBase.connecDb();
                String sql = "SELECT * FROM StudentClassSection WHERE studentID = ? AND classSectionID LIKE ?";
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, studentID);
                prepare.setString(2, parts[0] + "." + parts[1] + ".%");

                result = prepare.executeQuery();
                return !result.next();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public String getStudentClassSectionID(String classSectionID, String studentID){
        String[] parts = classSectionID.split("\\.");

        if(parts.length == 2){
            try{
                connect = DataBase.connecDb();
                String sql = "SELECT * FROM StudentClassSection WHERE studentID = ? AND classSectionID LIKE ? AND classSectionID NOT LIKE ?";
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, studentID);
                prepare.setString(2, parts[0] + ".N__");
                prepare.setString(3, parts[0] + ".N__." + ".%");

                result = prepare.executeQuery();
                if(result.next()){
                    return result.getString("classSectionID");
                }
                return null;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }else if(parts.length == 3){
            try{
                connect = DataBase.connecDb();
                String sql = "SELECT * FROM StudentClassSection WHERE studentID = ? AND classSectionID LIKE ?";
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, studentID);
                prepare.setString(2, parts[0] + "." + parts[1] + ".%");

                result = prepare.executeQuery();
                if(result.next()){
                    return result.getString("classSectionID");
                }
                return null;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public int getNumberOfClassSectionByCourseID(String courseID){
        try{
            connect = DataBase.connecDb();
            String sql = "SELECT COUNT(*) FROM StudentClassSection WHERE classSectionID LIKE ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, courseID + ".%");

            result = prepare.executeQuery();
            result.next();
            return result.getInt(1);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
