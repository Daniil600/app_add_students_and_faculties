package skypro.liberyofhogwarts.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.object.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ContextConfiguration(classes = FacultyServiceImpl.class)
@ExtendWith(SpringExtension.class)
public class FacultyServiceTest {

    @Autowired
    FacultyService facultyService;

    @AfterEach
    public void clearStudentService(){
        facultyService.clear();
    }
    public static Stream<Arguments> argument_add_method(){
        return Stream.of(
                Arguments.of(1l, "Экономический", "Синий", new Faculty(1l, "Экономический", "Синий")),
                Arguments.of(1l, "Биологический", "Красный", new Faculty(1l, "Биологический",  "Красный")),
                Arguments.of(1l, "Математический", "Жёлтый", new Faculty(1l, "Математический", "Жёлтый"))
        );
    }

    public static Stream<Arguments> argument_get_method(){
        return Stream.of(
                Arguments.of(1l, "Экономический", "Синий", new Faculty(1l, "Экономический", "Синий")),
                Arguments.of(1l, "Биологический", "Красный", new Faculty(1l, "Биологический",  "Красный")),
                Arguments.of(1l, "Математический", "Жёлтый", new Faculty(1l, "Математический", "Жёлтый"))
        );
    }

    public static Stream<Arguments> argument_del_method(){
        return Stream.of(
                Arguments.of(1l, "Экономический", "Синий", new Faculty(1l, "Экономический", "Синий")),
                Arguments.of(1l, "Биологический", "Красный", new Faculty(1l, "Биологический",  "Красный")),
                Arguments.of(1l, "Математический", "Жёлтый", new Faculty(1l, "Математический", "Жёлтый"))
        );
    }

    public static Stream<Arguments> argument_put_method(){
        return Stream.of(
                Arguments.of(1l, "Экономический", "Синий", new Faculty(1l, "Экономический", "Фиолетоый")),
                Arguments.of(1l, "Биологический", "Красный", new Faculty(1l, "Биологический",  "Белый")),
                Arguments.of(1l, "Математический", "Жёлтый", new Faculty(1l, "Математический", "Чёрный"))
        );
    }
    @ParameterizedTest
    @MethodSource("argument_add_method")
    void argument_add_method_of_studentService_success(Long id, String name, String color, Faculty faculty){
        Faculty facultyTest = new Faculty(id, name, color);
        Assertions.assertEquals(faculty, facultyService.addFaculty(facultyTest));
    }


    @ParameterizedTest
    @MethodSource("argument_get_method")
    void argument_get_method_of_studentService_success(Long id, String name, String color, Faculty faculty){
        Faculty facultyTest = new Faculty(id, name, color);
        facultyService.addFaculty(facultyTest);
        Assertions.assertEquals(faculty, facultyService.getFaculty(id));
    }

    @ParameterizedTest
    @MethodSource("argument_del_method")
    void argument_del_method_of_studentService_success(Long id, String name, String color, Faculty faculty){
        Faculty facultyTest = new Faculty(id, name, color);
        facultyService.addFaculty(facultyTest);
        Assertions.assertEquals(faculty, facultyService.delFaculty(id));
    }

    @ParameterizedTest
    @MethodSource("argument_put_method")
    void argument_put_method_of_studentService_success(Long id, String name, String color, Faculty faculty){
        Faculty facultyTest = new Faculty(id, name, color);
        facultyService.addFaculty(facultyTest);
        Assertions.assertEquals(faculty, facultyService.putFaculty(faculty));
    }


    @Test
    public void argument_sort_method_of_studentService_success(){
        Faculty faculty = new Faculty(1l, "Экономический", "Синий");
        Faculty faculty1 = new Faculty(1l, "Биологический", "Красный");
        Faculty faculty2 = new Faculty(1l, "Математический", "Жёлтый");
        Faculty faculty3 = new Faculty(1l, "Лингвистика", "Жёлтый");

        ArrayList<Faculty> students = new ArrayList<>(List.of(faculty));
        ArrayList<Faculty> students1 = new ArrayList<>(List.of(faculty1));
        ArrayList<Faculty> students2 = new ArrayList<>(List.of(faculty2, faculty3));


        facultyService.addFaculty(faculty);
        facultyService.addFaculty(faculty1);
        facultyService.addFaculty(faculty2);
        facultyService.addFaculty(faculty3);

        Assertions.assertEquals(students, facultyService.getSort("Синий"));
        Assertions.assertEquals(students1, facultyService.getSort("Красный"));
        Assertions.assertEquals(students2, facultyService.getSort("Жёлтый"));
    }
}
