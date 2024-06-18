package demo.Entity;

public class Schedule {
    private int scheduleID;
    private int classSectionID;
    private int classroomID;
    private String startTime;
    private String endTime;
    private String dayOfWeek;

    public Schedule(int scheduleID, int classSectionID, int classroomID, String startTime, String endTime, String dayOfWeek) {
        this.scheduleID = scheduleID;
        this.classSectionID = classSectionID;
        this.classroomID = classroomID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
    }

    public Schedule() {
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public int getClassSectionID() {
        return classSectionID;
    }

    public void setClassSectionID(int classSectionID) {
        this.classSectionID = classSectionID;
    }

    public int getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(int classroomID) {
        this.classroomID = classroomID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}