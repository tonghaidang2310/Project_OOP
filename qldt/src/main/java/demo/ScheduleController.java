package demo;


import java.util.List;

import demo.DAO.ScheduleDAO;
import demo.Entity.Schedule;

public class ScheduleController {
    private ScheduleDAO scheduleDAO;

    public ScheduleController() {
        this.scheduleDAO = new ScheduleDAO();
    }

    public List<Schedule> getAllSchedules() {
        return scheduleDAO.getAllSchedules();
    }

    public void addSchedule(Schedule schedule) {
        scheduleDAO.addSchedule(schedule);
    }

    public void updateSchedule(Schedule schedule) {
        scheduleDAO.updateSchedule(schedule);
    }

    public void deleteSchedule(int scheduleID) {
        scheduleDAO.deleteSchedule(scheduleID);
    }
}

