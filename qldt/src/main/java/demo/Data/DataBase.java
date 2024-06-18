package demo.Data;

/*
 * Class này dùng để kết nối với cơ sở dữ liệu
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/Project_OOP";
    private static final String USER = "root";

    private static final String PASSWORD = "tranleanh3524@Z";
    // private static final String PASSWORD = "Lluong2004";


    // Hàm kết nối với cơ sở dữ liệu
    public static Connection connecDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected");
            return conn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
