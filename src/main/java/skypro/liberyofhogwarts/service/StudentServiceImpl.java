package skypro.liberyofhogwarts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.repositories.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl{


    private StudentRepository studentRepository;


    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public void delStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student putStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudentByAgeBetween(Integer min, Integer max) {
        return studentRepository.findStudentByAgeBetween(min, max);
    }


    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student findStudentByAge(Integer age) {
        return studentRepository.findStudentByAge(age);
    }

    public Student findStudentByName(String name) {
        return studentRepository.findStudentByName(name);
    }

    public Optional<Student> findStudentById(Long id) {

        return studentRepository.findById(id);
    }


}
