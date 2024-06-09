package demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import demo.Data.DataBase;

public class LecturerDAO {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public String getNameLecturer(String LecturerID){
        String firstName = "";
        String lastName = "";
        try{
            connect = DataBase.connecDb();
            String sql = "SELECT * FROM Lecturer WHERE LecturerID = ?;";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, LecturerID);
            result = prepare.executeQuery();
            while(result.next()){
                firstName = result.getString("firstName");
                lastName = result.getString("lastName");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return firstName + " " + lastName;
    }
}