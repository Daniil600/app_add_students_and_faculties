package skypro.liberyofhogwarts.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import skypro.liberyofhogwarts.object.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class StudentSetviceTest {

    @Autowired
    StudentService studentService;

    @AfterEach
    public void clearStudentService(){
        studentService.clear();
    }


    public static Stream<Arguments> argument_add_method(){
        return Stream.of(
                Arguments.of(1l, "Даниил", 21, new Student(1l, "Даниил", 21)),
                Arguments.of(1l, "Виктор", 22, new Student(1l, "Виктор", 22)),
                Arguments.of(1l, "Даниил", 23, new Student(1l, "Даниил", 23))
        );
    }

    public static Stream<Arguments> argument_get_method(){
        return Stream.of(
                Arguments.of(1l, "Даниил", 21, new Student(1l, "Даниил", 21)),
                Arguments.of(1l, "Виктор", 22, new Student(1l, "Виктор", 22)),
                Arguments.of(1l, "Даниил", 23, new Student(1l, "Даниил", 23))
        );
    }

    public static Stream<Arguments> argument_del_method(){
        return Stream.of(
                Arguments.of(1l, "Даниил", 21, new Student(1l, "Даниил", 21)),
                Arguments.of(1l, "Виктор", 22, new Student(1l, "Виктор", 22)),
                Arguments.of(1l, "Даниил", 23, new Student(1l, "Даниил", 23))
        );
    }

    public static Stream<Arguments> argument_put_method(){
        return Stream.of(
                Arguments.of(1l, "Даниил", 21, new Student(1l, "Михаил", 22)),
                Arguments.of(1l, "Виктор", 22, new Student(1l, "Даниил", 23)),
                Arguments.of(1l, "Даниил", 23, new Student(1l, "Виктор", 24))
        );
    }
    @ParameterizedTest
    @MethodSource("argument_add_method")
    void argument_add_method_of_studentService_success(Long id, String name, int age, Student student){
        Student studentTest = new Student(id, name, age);
        Assertions.assertEquals(student, studentService.addStudent(studentTest));
        Assertions.assertEquals(student, studentService.delStudent(id));
    }

    @ParameterizedTest
    @MethodSource("argument_get_method")
    void argument_get_method_of_studentService_success(Long id, String name, int age, Student student){
        Student studentTest = new Student(id, name, age);
        studentService.addStudent(studentTest);
        Assertions.assertEquals(student, studentService.getStudent(id));
    }

    @ParameterizedTest
    @MethodSource("argument_del_method")
    void argument_del_method_of_studentService_success(Long id, String name, int age, Student student){
        Student studentTest = new Student(id, name, age);
        studentService.addStudent(studentTest);
        Assertions.assertEquals(student, studentService.delStudent(id));
    }

    @ParameterizedTest
    @MethodSource("argument_put_method")
    void argument_put_method_of_studentService_success(Long id, String name, int age, Student student){
        Student studentTest = new Student(id, name, age);
        studentService.addStudent(studentTest);
        Assertions.assertEquals(student, studentService.putStudent(student));
    }

    @Test
    public void argument_sort_method_of_studentService_success(){
        Student student = new Student(1l, "Михаил", 22);
        Student student1 = new Student(1l, "Даниил", 23);
        Student student2 = new Student(1l, "Виктор", 24);
        Student student3 = new Student(1l, "Сергей", 24);

        ArrayList<Student> students = new ArrayList<>(List.of(student));
        ArrayList<Student> students1 = new ArrayList<>(List.of(student1));
        ArrayList<Student> students2 = new ArrayList<>(List.of(student2, student3));


        studentService.addStudent(student);
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);

        Assertions.assertEquals(students, studentService.getSort(22));
        Assertions.assertEquals(students1, studentService.getSort(23));
        Assertions.assertEquals(students2, studentService.getSort(24));
    }
}
