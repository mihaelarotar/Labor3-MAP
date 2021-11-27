package uni.entities;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private long studentID;
    private int totalCredits;
    private List<Course> enrolledCourses;

    public Student(String firstName, String lastName, long studentID) {
        super(firstName, lastName);
        this.studentID = studentID;
        this.totalCredits = 0;
        this.enrolledCourses = new ArrayList<>();
        validate();
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    /**
     * data validator
     * checks if the integers are negative
     * @throws IllegalArgumentException if data is not valid
     */
    private void validate() {
        if (studentID <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        if (totalCredits < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        } else if (totalCredits > 30) {
            throw new IllegalArgumentException("Total number of credits must be smaller than 30");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentID == student.studentID;
    }

    @Override
    public String toString() {
        List<String> courses = new ArrayList<>();
        for (Course course : enrolledCourses) {
            courses.add(course.getName());
        }
        return "Student{" +
                "studentID=" + studentID +
                "firstName=" + getFirstName() +
                "lastName=" + getLastName() +
                ", totalCredits=" + totalCredits +
                ", enrolledCourses=" + courses +
                '}';
    }

    /**
     * adds a course to the list of enrolled courses and updates the total number of credits
     * @param course the course to be added
     * @throws Exception if the maximum number of credits is exceeded
     */
    public void addCourseToEnrolledCourses(Course course) throws Exception {
        if (this.totalCredits + course.getCredits() >= 30) {
            throw new Exception("The maximum number of 30 credits was exceeded");
        }
        this.enrolledCourses.add(course);
        this.totalCredits += course.getCredits();
    }

    /**
     * removes a course from the list of enrolled courses and updates the total number of credits
     * @param course the course to be removed
     */
    public void removeCourseFromEnrolledCourses(Course course) {
        this.enrolledCourses.remove(course);
        this.totalCredits -= course.getCredits();
    }
}
