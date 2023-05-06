package skypro.liberyofhogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.repositories.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl{


    private StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);


    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent(long id) {
        logger.info("getStudent is app");
        return studentRepository.findById(id).get();
    }

    public void delStudent(long id) {
        logger.info("delStudent is app");
        studentRepository.deleteById(id);
    }

    public Student addStudent(Student student) {
        logger.info("addStudent is app");
        return studentRepository.save(student);
    }

    public Student putStudent(Student student) {
        logger.info("putStudent is app");
        return studentRepository.save(student);
    }

    public Student findStudentByAgeBetween(Integer min, Integer max) {
        logger.info("findStudentByAgeBetween is app");
        return studentRepository.findStudentByAgeBetween(min, max);
    }


    public Collection<Student> getAll() {
        logger.info("getAll is app");
        return studentRepository.findAll();
    }

    public Student findStudentByAge(Integer age) {
        logger.info("findStudentByAge is app");
        return studentRepository.findStudentByAge(age);
    }

    public Student findStudentByName(String name) {
        logger.info("findStudentByName is app");
        return studentRepository.findStudentByName(name);
    }

    public Optional<Student> findStudentById(Long id) {
        logger.info("findStudentById is app");
        return studentRepository.findById(id);
    }

    public Long getCountStudent(){
        logger.info("getCountStudent is app");
        return studentRepository.count();
    }

    public double getAvgAge(){
        logger.info("getAvgAge is app");
        return studentRepository.avgAge();
    }

    public List<Student> getLastFiveStudents(){
        logger.info("getLastFiveStudents is app");
        return studentRepository.lastFiveStudents();
    }


}
