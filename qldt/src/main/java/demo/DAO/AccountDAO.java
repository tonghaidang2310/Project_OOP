package demo.DAO;

/*
 * Class này dùng để thao tác với cơ sở dữ liệu liên quan đến tài khoản
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

import demo.Account.CurrentAccount;
import demo.Account.LecturerAccount;
import demo.Account.StudentAccount;
import demo.Data.DataBase;
import demo.Entity.Student;
import demo.Entity.Lecturer;

public class AccountDAO {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    // Hàm tạo ID sinh viên mới
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

    // Hàm tạo ID giảng viên mới
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

    // Hàm đăng ký tài khoản
    public void signUpAccount(String username, String password, String typeUser, Student s){
        String id = typeUser.equals("Student") ? generateUniqueStudentID() : generateUniqueLecturerID();
        new StudentDAO().addStudentId(id, s);
        try{
            connect = DataBase.connecDb();
            String sql = "INSERT INTO account (username, password, UserType, " + typeUser + "ID" +  ") VALUES (?, ?, ?, ?)";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username);
            prepare.setString(2, hashPassword(password));
            prepare.setString(3, typeUser);
            prepare.setString(4, id);

            prepare.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Hàm đăng nhập tài khoản
    public boolean loginAccount(String username, String password){
        boolean rs = false;
        try{
            connect = DataBase.connecDb();
            String sql = "SELECT * FROM account WHERE username = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username);

            result = prepare.executeQuery();

            if(result.next()){
                String hashedPassFromDb = result.getString("password");
                rs = checkPassword(password, hashedPassFromDb);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    // Hàm mã hóa mật khẩu
    private String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Hàm kiểm tra mật khẩu
    private boolean checkPassword(String plainPassword, String hashedPasssword){
        return BCrypt.checkpw(plainPassword, hashedPasssword);
    }

    // Hàm lấy thông tin tài khoản
    public <T> Object getAccount(String username, String password){
        try{
            connect = DataBase.connecDb();
            String sql = "SELECT * FROM account WHERE username = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username);

            result = prepare.executeQuery();

            String userType = "";
            String userTypeId = "";
            String pass = "";
            String user = "";
            String accountID = "";

            if(result.next()){
                userType = result.getString("UserType");
                userTypeId = result.getString(userType + "ID");
                pass = result.getString("password");
                user = result.getString("username");
                accountID = result.getString("AccountID");
            }
            return (userType.equals("Student")) ? new StudentAccount(accountID, user, pass, userTypeId) : new LecturerAccount(accountID, username, password, userTypeId);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // Hàm kiểm tra loại tài khoản
    public String checkTypeAccount(String username){
        String type = "";
        try{
            connect = DataBase.connecDb();
            String sql = "SELECT UserType FROM account WHERE username = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username);

            result = prepare.executeQuery();

            if(result.next()){
                type = result.getString("UserType");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return type;
    }

    // Hàm thêm tài khoản sinh viên hiện tại đang đăng nhập
    public void addCurrentAccount(StudentAccount studentAccount){
        try{
            connect = DataBase.connecDb();
            String sql = "INSERT INTO session (AccountID, Username, Password, UserType, StudentID) VALUES (?, ?, ?, ?, ?)";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, studentAccount.getId());
            prepare.setString(2, studentAccount.getUsername());
            prepare.setString(3, studentAccount.getPassword());
            prepare.setString(4, studentAccount.getUserType());
            prepare.setString(5, studentAccount.getStudentid());

            prepare.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Hàm thêm tài khoản giảng viên hiện tại đang đăng nhập
    public void addCurrentAccount(LecturerAccount lecturerAccount){
        try{
            connect = DataBase.connecDb();
            String sql = "INSERT INTO session (AccountID, Username, Password, UserType, LecturerID) VALUES (?, ?, ?, ?, ?)";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, lecturerAccount.getId());
            prepare.setString(2, lecturerAccount.getUsername());
            prepare.setString(3, lecturerAccount.getPassword());
            prepare.setString(4, lecturerAccount.getUserType());
            prepare.setString(5, lecturerAccount.getLecturerid());

            prepare.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Hàm thêm tài khoản hiện tại đang đăng nhập
    public void addCurrentAccount(CurrentAccount currentAccount){
        String user = currentAccount.getUserName();
        String pass = currentAccount.getPassword();
        if(getAccount(user, pass) instanceof StudentAccount){
            addCurrentAccount((StudentAccount) getAccount(user, pass));
        }else{
            addCurrentAccount((LecturerAccount) getAccount(user, pass));
        }
    }

    // Hàm xóa tài khoản hiện tại đang đăng nhập
    public void removeCurrentAccount(String accountID){
        try{
            connect = DataBase.connecDb();
            String sql = "DELETE FROM session WHERE AccountID = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, accountID);

            prepare.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Hàm lấy tài khoản hiện tại đang đăng nhập
    public CurrentAccount getCurrentAccount(){
        CurrentAccount currentAccount = null;
        try{
            connect = DataBase.connecDb();
            String sql = "SELECT * FROM session";
            prepare = connect.prepareStatement(sql);

            result = prepare.executeQuery();

            if(result.next()){
                String userType = result.getString("UserType");
                String accountID = result.getString("AccountID");
                String username = result.getString("Username");
                String password = result.getString("Password");
                String userTypeId = result.getString(userType + "ID");

                if(userType.equals("Student")){
                    currentAccount = new CurrentAccount(new StudentAccount(accountID, username, password, userTypeId));
                }else{
                    currentAccount = new CurrentAccount(new LecturerAccount(accountID, username, password, userTypeId));
                }
            }
            return currentAccount;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // Hàm lấy thông tin người dùng
    public <T> Object getInfoPerson(String ID, String userType){
        try{
            connect = DataBase.connecDb();
            String sql = "SELECT * FROM " + userType + " WHERE " + userType + "ID = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, ID);

            result = prepare.executeQuery();

            String id = "";
            String fName = "";
            String lName = "";
            String address = "";
            String phone = "";
            String email = "";
            String gender = "";

            if(result.next()){
                id = result.getString(userType + "ID");
                fName = result.getString("FirstName");
                lName = result.getString("LastName");
                if(userType.equals("Student"))
                    address = result.getString("Address");
                phone = result.getString("PhoneNumber");
                email = result.getString("Email");
                gender = result.getString("gender");
            }
            if(userType.equals("Student")){
                return new Student(fName, lName, phone, email, gender, id, address);
            }else if(userType.equals("Lecturer")){
                return new Lecturer(fName, lName, phone, email, gender, id);
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
