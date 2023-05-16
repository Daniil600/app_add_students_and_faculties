package skypro.liberyofhogwarts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import skypro.liberyofhogwarts.object.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByAge(Integer age);
    Student findStudentByName(String name);
    Student findStudentByAgeBetween(Integer min, Integer max);

    @Query(value = "SELECT AVG(age) from student", nativeQuery = true)
    double avgAge();

    @Query(value = "SELECT * FROM student ORDER BY id desc LIMIT 5", nativeQuery = true)
    List<Student> lastFiveStudents();
}
