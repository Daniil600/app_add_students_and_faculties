package skypro.liberyofhogwarts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import skypro.liberyofhogwarts.object.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByAge(Integer age);
    Student findStudentByName(String name);
    Student findStudentById(Long id);
    Student findStudentByAgeBetween(Integer min, Integer max);
}
