package uni.repository;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import uni.entities.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StudentRepositoryTest {

    @Test
    void save() {
        StudentRepository studentRepository = new StudentRepository();
        assertEquals(studentRepository.getAll().size(),0);
        studentRepository.save(new Student("Maria", "Ionescu", 123456));
        assertEquals(studentRepository.getAll().size(),1);

        Student student = new Student("Vlad", "Popa", 2);
        assertEquals(studentRepository.save(student), student);
        assertEquals(studentRepository.getAll().size(),2);
    }

    @Test
    @Description("test if a student with the same ID as an existing student can be added (it cannot)")
    void saveExistingStudent() {
        StudentRepository studentRepository = new StudentRepository();
        Student student = new Student("Vlad", "Popa", 2);
        studentRepository.save(student);
        Student student1 = new Student("Vlad", "Popa", 2);
        assertNull(studentRepository.save(student1));
        assertEquals(studentRepository.getAll().size(),1);
    }

    @Test
    @Description("checks if an exception is thrown when trying to add an invalid object")
    void saveAndValidate() {
        StudentRepository studentRepository = new StudentRepository();
        try {
            Student student = new Student("Maria", "Ionescu", -1);
            studentRepository.save(student);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        assertEquals(studentRepository.getAll().size(),0);
    }

    @Test
    void delete() {
        StudentRepository studentRepository = new StudentRepository();
        Student student = new Student("Vlad", "Popa", 2);
        studentRepository.save(student);
        Student student1 = new Student("Maria", "Ionescu", 123456);
        studentRepository.save(student1);
        assertEquals(studentRepository.delete(student1), student1);
        assertNull(studentRepository.delete(student1));
        assertEquals(studentRepository.delete(student), student);
        assertEquals(studentRepository.getAll().size(),0);
    }

    @Test
    void deleteByID() {
        StudentRepository studentRepository = new StudentRepository();
        studentRepository.save(new Student("Maria", "Ionescu", 123456));
        studentRepository.deleteByID(123456);
        assertEquals(studentRepository.getAll().size(),0);
    }

    @Test
    void update() {
        StudentRepository studentRepository = new StudentRepository();
        Student student = new Student("Vlad", "Popa", 2);
        studentRepository.save(student);
        Student student1 = new Student("Ana", "Popa", 2);
        assertNull(studentRepository.update(student1));
        assertEquals(studentRepository.getAll().get(0).getFirstName(), "Ana");
    }

}