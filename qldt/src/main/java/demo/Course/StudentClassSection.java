package demo.Course;

/*
 * Class lưu trữ thông tin của một sinh viên trong một lớp học phần
 */

public class StudentClassSection {
    private String classSectionID;
    private String studentID;

    public StudentClassSection(String classSectionID, String studentID) {
        this.classSectionID = classSectionID;
        this.studentID = studentID;
    }

    public String getClassSectionID() {
        return classSectionID;
    }

    public String getStudentID() {
        return studentID;
    }
}
