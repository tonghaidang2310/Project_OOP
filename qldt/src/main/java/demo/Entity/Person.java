package demo.Entity;

public class Person {
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private String Email;
    private String Gender;

    public Person(String firstName, String lastName, String phoneNumber, String email, String Gender){
        this.FirstName = firstName;
        this.LastName = lastName;
        this.PhoneNumber = phoneNumber;
        this.Email = email;
        this.Gender = Gender;
    }

    public String getFirstName(){
        return FirstName;
    }

    public String getLastName(){
        return LastName;
    }

    public String getPhoneNumber(){
        return PhoneNumber;
    }

    public String getEmail(){
        return Email;
    }

    public String getGender(){
        return Gender;
    }

    public void setFirstName(String firstName){
        this.FirstName = firstName;
    }

    public void setLastName(String lastName){
        this.LastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber){
        this.PhoneNumber = phoneNumber;
    }

    public void setEmail(String email){
        this.Email = email;
    }

    public void setGender(String gender){
        this.Gender = gender;
    }
}
