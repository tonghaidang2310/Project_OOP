package demo.Entity;

public class Lecturers extends Person {
    private String ID;

    public Lecturers(String firstName, String lastName, String phoneNumber, String email, String gender, String ID){
        super(firstName, lastName, phoneNumber, email, gender);
        this.ID = ID;
    }

    public String getID(){
        return ID;
    }

    public void setID(String ID){
        this.ID = ID;
    }
}
