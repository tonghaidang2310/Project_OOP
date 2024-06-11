package demo.Course;

/*
 * Class lưu trữ thông tin của một lớp học phần
 */

public class ClassSection {
    private String classSectionID;
    private String classSectionName;
    private String courseID;
    private String lecturerID;
    private int enrolledStudents;

    public ClassSection(String classSectionID, String classSectionName, String courseID, String lecturerID, int enrolledStudents) {
        this.classSectionID = classSectionID;
        this.classSectionName = classSectionName;
        this.courseID = courseID;
        this.lecturerID = lecturerID;
        this.enrolledStudents = enrolledStudents;
    }

    public String getClassSectionID() {
        return classSectionID;
    }

    public String getClassSectionName() {
        return classSectionName;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getLecturerID() {
        return lecturerID;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }
    
}
