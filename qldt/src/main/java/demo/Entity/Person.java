package demo.Entity;

/*
 * Class này dùng để lưu trữ thông tin của một người
 */

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
}
