package uni;

import uni.controller.RegistrationSystem;
import uni.entities.Course;
import uni.entities.Student;
import uni.entities.Teacher;
import uni.repository.CourseRepository;
import uni.repository.StudentRepository;
import uni.repository.TeacherRepository;


public class Main {

    public static void main(String[] args) throws Exception {
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        TeacherRepository teacherRepository = new TeacherRepository();
        RegistrationSystem myRegistrationSystem = new RegistrationSystem(studentRepository, teacherRepository, courseRepository);
        Student student = new Student("Vlad", "Popa", 2);
        studentRepository.save(student);
        Teacher teacher = new Teacher("John", "Smith", 3);
        Course databases = new Course("DB", teacher,30,4);
        System.out.println(teacher);

        courseRepository.save(databases);
        System.out.println(myRegistrationSystem.register(databases, student));
        System.out.println(databases);

        System.out.println(teacher);
    }
}
