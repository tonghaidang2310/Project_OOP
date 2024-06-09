package demo.Course;

public class Course {
    private String courseID;
    private String nameCourse;
    private double tuitionFee;
    private int credits;

    public Course(String courseID, String nameCourse, double tuitionFee, int credits) {
        this.courseID = courseID;
        this.nameCourse = nameCourse;
        this.tuitionFee = tuitionFee;
        this.credits = credits;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public int getCredits() {
        return credits;
    }
}
