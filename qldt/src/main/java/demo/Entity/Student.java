package demo.Entity;

public class Student extends Person{
    private String ID;
    private String Address;

    public Student(String firstName, String lastName, String phoneNumber, String email, String gender, String ID, String Address){
        super(firstName, lastName, phoneNumber, email, gender);
        this.ID = ID;
        this.Address = Address;
    }

    public Student(String fName, String lName, String gender){
        super(fName, lName, null, null, gender);
        this.ID = null;
        this.Address = null;
    }

    public String getID(){
        return ID;
    }

    public String getAddress(){
        return Address;
    }
}
