package demo.Account;

/*
 * Class này dùng để lưu trữ thông tin tài khoản của sinh viên
 */

import demo.DAO.AccountDAO;

public class StudentAccount extends Account{
    private String Studentid;

    public StudentAccount(String id, String username, String password) {
        super(id, username, password, "Student");
        this.Studentid = new AccountDAO().generateUniqueStudentID();
    }

    public StudentAccount(String id, String username, String password, String studentid) {
        super(id, username, password, "Student");
        this.Studentid = studentid;
    }

    public String getStudentid() {
        return Studentid;
    }
}
