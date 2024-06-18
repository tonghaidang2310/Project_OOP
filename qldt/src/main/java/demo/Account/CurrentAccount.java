package demo.Account;

/*
 * Class này dùng để lưu trữ thông tin tài khoản hiện tại đang đăng nhập
 */

public class CurrentAccount {
    private String AccountID;
    private String LecturerID;
    private String StudentID;
    private String AccountType;
    private String Username;
    private String Password;

    public CurrentAccount(StudentAccount studentAccount) {
        this.StudentID = studentAccount.getStudentid();
        this.AccountID = studentAccount.getId();
        this.AccountType = studentAccount.getUserType();
        this.Username = studentAccount.getUsername();
        this.Password = studentAccount.getPassword();
    }

    public CurrentAccount(LecturerAccount lecturerAccount){
        this.LecturerID = lecturerAccount.getLecturerid();
        this.AccountID = lecturerAccount.getId();
        this.AccountType = lecturerAccount.getUserType();
        this.Username = lecturerAccount.getUsername();
        this.Password = lecturerAccount.getPassword();
    }

    public String getAccountID(){ return this.AccountID;}

    public String getLecturerID(){ return this.LecturerID;}

    public String getStudentID(){ return this.StudentID;}

    public String getTypeAccount(){ return this.AccountType;}

    public String getUserName(){return this.Username;}

    public String getPassword() {return this.Password;}
}
