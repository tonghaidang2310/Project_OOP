package demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import demo.Course.ClassSection;
import demo.Data.DataBase;

public class ClassSectionDAO {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public List<ClassSection> getClassSections(String courseID){
        List<ClassSection> classSectionList = new ArrayList<>();
        try{
            connect = DataBase.connecDb();
            prepare = connect.prepareStatement("SELECT * FROM classsection WHERE courseID = ?");
            prepare.setString(1, courseID);
            result = prepare.executeQuery();
            while (result.next()) {
                ClassSection classSection = new ClassSection(result.getString("classSectionID"), result.getString("classSectionName"),
                        result.getString("courseID"), result.getString("lecturerID"), result.getInt("enrolled"));
                classSectionList.add(classSection);
            }
            return classSectionList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ClassSection> getAllClassSections(){
        List<ClassSection> classSectionList = new ArrayList<>();
        try {
            connect = DataBase.connecDb();
            prepare = connect.prepareStatement("SELECT * FROM classsection");
            result = prepare.executeQuery();
            while (result.next()) {
                ClassSection classSection = new ClassSection(result.getString("classSectionID"), result.getString("classSectionName"),
                        result.getString("courseID"), result.getString("lecturerID"), result.getInt("enrolled"));
                classSectionList.add(classSection);
            }
            return classSectionList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
