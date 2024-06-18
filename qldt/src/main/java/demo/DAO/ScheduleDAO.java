package demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import demo.Data.DataBase;
import demo.Entity.Schedule;
public class ScheduleDAO {

    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM schedules";
        try (Connection connection = DataBase.connecDb();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int scheduleID = resultSet.getInt("scheduleID");
                int classSectionID = resultSet.getInt("classSectionID");
                int classroomID = resultSet.getInt("classroomID");
                String startTime = resultSet.getString("startTime");
                String endTime = resultSet.getString("endTime");
                String dayOfWeek = resultSet.getString("dayOfWeek");

                Schedule schedule = new Schedule(scheduleID, classSectionID, classroomID, startTime, endTime, dayOfWeek);
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    public void addSchedule(Schedule schedule) {
        String query = "INSERT INTO schedules (classSectionID, classroomID, startTime, endTime, dayOfWeek) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DataBase.connecDb();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, schedule.getClassSectionID());
            preparedStatement.setInt(2, schedule.getClassroomID());
            preparedStatement.setString(3, schedule.getStartTime());
            preparedStatement.setString(4, schedule.getEndTime());
            preparedStatement.setString(5, schedule.getDayOfWeek());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSchedule(Schedule schedule) {
        String query = "UPDATE schedules SET classSectionID = ?, classroomID = ?, startTime = ?, endTime = ?, dayOfWeek = ? WHERE scheduleID = ?";
        try (Connection connection = DataBase.connecDb();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, schedule.getClassSectionID());
            preparedStatement.setInt(2, schedule.getClassroomID());
            preparedStatement.setString(3, schedule.getStartTime());
            preparedStatement.setString(4, schedule.getEndTime());
            preparedStatement.setString(5, schedule.getDayOfWeek());
            preparedStatement.setInt(6, schedule.getScheduleID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSchedule(int scheduleID) {
        String query = "DELETE FROM schedules WHERE scheduleID = ?";
        try (Connection connection = DataBase.connecDb();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, scheduleID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
