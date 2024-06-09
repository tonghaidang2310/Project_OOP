package demo.Entity;

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
