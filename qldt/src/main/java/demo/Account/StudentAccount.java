package demo.Account;

import demo.DAO.AccountDAO;

public class StudentAccount extends Account{
    private String Studentid;

    public StudentAccount(String id, String username, String password) {
        super(id, username, password, "Student");
        this.Studentid = new AccountDAO().generateUniqueStudentID();
    }

    public String getStudentid() {
        return Studentid;
    }
}
