package skypro.liberyofhogwarts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {


    private StudentRepository studentRepository;


    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudent(long id) {
        return studentRepository.findById(id).get();
    }
    @Override
    public void delStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student putStudent(Student student) {
        return studentRepository.save(student);
    }



    @Override
    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentByAgeBetween(Integer age) {
        return studentRepository.findStudentByAge(age);
    }

    @Override
    public Student findStudentByName(String name){
        return studentRepository.findStudentByName(name);
    }


}
