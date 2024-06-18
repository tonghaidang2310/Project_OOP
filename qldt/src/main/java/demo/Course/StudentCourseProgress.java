package demo.Course;

/*
 * Class lưu trữ thông tin của một sinh viên trong một khóa học
 */

public class StudentCourseProgress{
    private int ProgressID;
    private String StudentID;
    private String CourseID;
    private String status;
    private double grade;

    public StudentCourseProgress(int ProgressID, String StudentID, String CourseID, String ClassSectionID, String status, double grade) {
        this.ProgressID = ProgressID;
        this.StudentID = StudentID;
        this.CourseID = CourseID;
        this.status = status;
        this.grade = grade;
    }

    public int getProgressID() {
        return ProgressID;
    }

    public String getStudentID() {
        return StudentID;
    }

    public String getCourseID() {
        return CourseID;
    }

    public String getStatus() {
        return status;
    }

    public double getGrade() {
        return grade;
    }
}
