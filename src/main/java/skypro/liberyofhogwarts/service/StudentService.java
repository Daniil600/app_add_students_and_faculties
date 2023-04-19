package skypro.liberyofhogwarts.service;

import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.object.Student;

import java.util.Collection;

public interface StudentService {
    Student getStudent(Long id);
    Student delStudent(Long id);
    Student addStudent(Student faculty);
    Student putStudent(Student faculty);

    Collection<Student> getSort(int age);

    void clear();

    Collection<Student> getAll();
}
