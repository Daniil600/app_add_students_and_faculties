package skypro.liberyofhogwarts.service;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class StudentSetviceTest {

//
//    @Autowired
//    StudentService studentService;
//
//    @MockBean
//    StudentRepository studentRepository;
//
//    @Test
//    public void studentRepository_test_get_method_success() {
//        //Подготовка входных зничений
//        Student student = new Student(1l, "Даниил", 21);
//        Student student1 = new Student(2l, "Иван", 23);
//
//        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
//        when(studentRepository.findById(student1.getId())).thenReturn(Optional.of(student1));
//
//        //Проверка данных
//
//        Assertions.assertEquals(studentService.getStudent(student.getId()), student);
//        Assertions.assertEquals(studentService.getStudent(student1.getId()), student1);
//
//    }
//
//
//    @Test
//    public void studentRepository_test_add_method_success() {
//        //Подготовка входных зничений
//        Student student = new Student(1l, "Даниил", 21);
//        Student student1 = new Student(2l, "Иван", 23);
//
//        when(studentRepository.save(student)).thenReturn(student);
//        when(studentRepository.save(student1)).thenReturn(student1);
//
//        //Проверка данных
//
//        Assertions.assertEquals(studentService.addStudent(student), student);
//        Assertions.assertEquals(studentService.addStudent(student1), student1);
//
//    }
//
//    @Test
//    public void studentRepository_test_find_method_success() {
//        //Подготовка входных зничений
//        long id = 1;
//        Student student = new Student(1l, "Даниил", 22);
//
//        when(studentRepository.save(student)).thenReturn(student);
//        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
//
//        //Проверка данных
//
//        Assertions.assertEquals(studentService.putStudent(student), student);
//
//    }

}
