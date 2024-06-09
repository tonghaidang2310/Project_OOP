package demo.Course;

public class CourseLearned {
    private String classSectionID;
    private String studentID;
    private String learnedStatus;

    public CourseLearned(String classSectionID, String studentID, String learnedStatus) {
        this.classSectionID = classSectionID;
        this.studentID = studentID;
    }

    public String getClassSectionID() {
        return classSectionID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getLearnedStatus() {
        return learnedStatus;
    }
}
