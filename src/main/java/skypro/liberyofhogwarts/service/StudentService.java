package skypro.liberyofhogwarts.service;

import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.object.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student getStudent(long id);
    void delStudent(long id);
    Student addStudent(Student faculty);
    Student putStudent(Student faculty);
    Collection<Student> getAll();

    Student getByAge(int age);
}
