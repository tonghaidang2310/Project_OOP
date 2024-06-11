package demo.Account;

/*
 * Class này dùng để lưu trữ thông tin tài khoản
 */

public class Account {
    private String id;
    private String username;
    private String password;
    private String userType;

    public Account(String id, String username, String password, String userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }
}
