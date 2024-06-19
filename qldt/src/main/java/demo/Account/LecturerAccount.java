package demo.Account;

/*
 * Class này dùng để lưu trữ thông tin tài khoản của giảng viên
 */

import demo.DAO.AccountDAO;

public class LecturerAccount extends Account{
    private String Lecturerid;

    public LecturerAccount(int id, String username, String password) {
        super(id, username, password, "Lecturer");
        this.Lecturerid = new AccountDAO().generateUniqueLecturerID();
    }

    public LecturerAccount(int id, String username, String password, String lecturerid) {
        super(id, username, password, "Lecturer");
        this.Lecturerid = lecturerid;
    }

    public String getLecturerid() {
        return Lecturerid;
    }
}
