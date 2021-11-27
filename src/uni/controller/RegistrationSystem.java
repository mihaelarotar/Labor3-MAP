package uni.controller;

import uni.entities.Course;
import uni.entities.Student;
import uni.repository.CourseRepository;
import uni.repository.StudentRepository;
import uni.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSystem {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public RegistrationSystem(StudentRepository studentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * registers a student to a given course
     * @param course the course to be enrolled in
     * @param student the student to be enrolled
     * @return true if the registration was completed successfully
     * or false if the student is already registered to this course
     * or if there are no more available places for this course
     * @throws Exception if the given course or student are not in the list
     */
    public boolean register(Course course, Student student) throws Exception{
        int courseIndex = courseRepository.findIndex(course);
        int studentIndex = studentRepository.findIndex(student);
        if (courseIndex == -1) {
            throw new Exception("There is no such course in the list");
        }
        if (studentIndex == -1) {
            throw new Exception("There is no such student in the list");
        }
        Course foundCourse = courseRepository.getAll().get(courseIndex);
        Student foundStudent = studentRepository.getAll().get(studentIndex);
        if (foundCourse.getStudentsEnrolled().size() >= foundCourse.getMaxEnrollment()) {
            System.out.println("There are no more available places for this course");
            return false;
        }

        if (foundCourse.getStudentsEnrolled().contains(student) || foundStudent.getEnrolledCourses().contains(course)) {
            return false;
        }

        foundCourse.addStudentToStudentsEnrolled(student);
        foundStudent.addCourseToEnrolledCourses(course);
        return true;
    }

    /**
     * retrieves the courses with available places
     * @return a new list containing all the courses with available places
     */
    public List<Course> retrieveCoursesWithFreePlaces() {
        List<Course> coursesWithFreePlaces = new ArrayList<>();
        for(Course course : courseRepository.getAll()) {
            if (course.getMaxEnrollment() > course.getStudentsEnrolled().size()) {
                coursesWithFreePlaces.add(course);
            }
        }
        return coursesWithFreePlaces;
    }

    /**
     * retrieves all students enrolled to a given course
     * @param course the given course
     * @return a new list containing all the enrolled students
     */
    public List<Student> retrieveStudentsEnrolledForACourse(Course course) {
        List<Student> enrolledStudents = new ArrayList<>();
        for (Student student : studentRepository.getAll()) {
            if (student.getEnrolledCourses().contains(course))
                enrolledStudents.add(student);
        }
        return enrolledStudents;
    }

    /**
     * retrieves all courses in the list
     * @return the list with all courses
     */
    public List<Course> getAllCourses() {
        return courseRepository.getAll();
    }
}
