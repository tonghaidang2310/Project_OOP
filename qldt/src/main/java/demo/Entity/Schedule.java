package demo.Entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Schedule {
    int scheduleId;
    String subjectCode, classCode, teacherCode, createAt, expiredAt;
    List<String> teachingTime;
    float fromTime, toTime;

    public Schedule() {
        teachingTime = new ArrayList<String>();
    }

    public int getScheduledId(){
        return scheduleId;
    }
    public void setScheduledId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getClassCode() {
        return classCode;
    }
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getTeacherCode() {
        return teacherCode;
    }
    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getCreateAt() {
        return createAt;
    }
    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getExipredAt() {
        return expiredAt;
    }
    public void setExipredAt(String exipredAt) {
        this.expiredAt = exipredAt;
    }

    public List<String> getTeachingTime() {
        return teachingTime;
    }
    public void setTeachingTime(List<String> teachingTime) {
        this.teachingTime = teachingTime;
    }

    public float getFromTime() {
        return fromTime;
    }
    public void setFromTime(float fromTime) {
        this.fromTime = fromTime;
    }

    public float getToTime() {
        return toTime;
    }
    public void setToTime(float toTime) {
        this.toTime = toTime;
    }

    public void input() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter schedule id: ");
        scheduleId = Integer.parseInt(scan.nextLine());

        System.out.println("Enter class code: ");
        classCode = scan.nextLine();

        System.out.println("Enter subject code: ");
        subjectCode = scan.nextLine();

        System.out.println("Enter teacher code: ");
        teacherCode = scan.nextLine();

        System.out.println("Enter created at: ");
        createAt = scan.nextLine();

        System.out.println("Enter expired at: ");
        expiredAt = scan.nextLine();

        System.out.println("Enter teaching time: ");
        while(true){
            System.out.format("Time (%d)", teachingTime.size() + 1);
            teachingTime.add(scan.nextLine());
            System.out.println("Continue (Y/N): ");
            String c = scan.nextLine();
            if(c.equals("N")) 
                break;
        }

        System.out.println("Enter start time: ");
        fromTime = Float.parseFloat(scan.nextLine());

        System.out.println("Enter end time: ");
        toTime = Float.parseFloat(scan.nextLine());

    }

    @Override
    public String toString(){
        return "scheduleId=" + scheduleId + ", subjectCode=" + subjectCode + ", classCode=" + classCode + ", teacherCode=" + teacherCode + ", createAt=" + createAt + ", expiredAt=" + expiredAt + ", fromTime=" + fromTime + ", toTime=" + toTime;
    }

    public void display(){
        System.out.println(toString());
        System.out.println("Teaching time:");
        for (String value : teachingTime){
            System.out.println(value);
        }
    }
}
