package uni.entities;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person {
    private int teacherID;
    private List<Course> courses;

    public Teacher(String firstName, String lastName, int teacherID) {
        super(firstName, lastName);
        this.teacherID = teacherID;
        this.courses = new ArrayList<>();
        validate();
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return teacherID == teacher.teacherID;
    }

    private void validate() {
        if(teacherID <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
    }

    @Override
    public String toString() {
        List<String> courseNames = new ArrayList<>();
        for (Course course : courses) {
            courseNames.add(course.getName());
        }
        return "Teacher{" +
                "teacherID=" + teacherID +
                ", firstName=" + getFirstName() +
                ", lastName=" + getLastName() +
                ", courses=" + courseNames +
                '}';
    }

    /**
     * adds a new course to the list of courses
     * @param course the course to be added
     */
    public void addCourseToCourses(Course course) {
        this.courses.add(course);
    }

    /**
     * removes a course from the list
     * @param course the course to be removed
     */
    public void deleteCourseFromCourses(Course course) {
        this.courses.remove(course);
    }
}
