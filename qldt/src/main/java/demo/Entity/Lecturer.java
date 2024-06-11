package demo.Entity;

/*
 * Class này dùng để lưu trữ thông tin của một giảng viên
 */
public class Lecturer extends Person {
    private String ID;

    public Lecturer(String firstName, String lastName, String phoneNumber, String email, String gender, String ID){
        super(firstName, lastName, phoneNumber, email, gender);
        this.ID = ID;
    }

    public String getID(){
        return ID;
    }

}
