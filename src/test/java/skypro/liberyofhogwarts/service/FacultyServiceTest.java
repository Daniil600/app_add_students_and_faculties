package skypro.liberyofhogwarts.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.repositories.FacultyRepository;
import skypro.liberyofhogwarts.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = FacultyServiceImpl.class)
@ExtendWith(SpringExtension.class)
public class FacultyServiceTest {

    @Autowired
    FacultyService facultyService;

    @MockBean
    FacultyRepository facultyRepository;

    @Test
    public void studentRepository_test_get_method_success() {
        //Подготовка входных зничений
        Faculty faculty = new Faculty(1l, "Экономический", "Синий");
        Faculty faculty1 = new Faculty(2l, "Биологически ", "Красный");

        when(facultyRepository.findById(faculty.getId())).thenReturn(Optional.of(faculty));
        when(facultyRepository.findById(faculty1.getId())).thenReturn(Optional.of(faculty1));

        //Проверка данных

        Assertions.assertEquals(facultyService.getFaculty(faculty.getId()), faculty);
        Assertions.assertEquals(facultyService.getFaculty(faculty1.getId()), faculty1);

    }


    @Test
    public void studentRepository_test_add_method_success() {
        //Подготовка входных зничений
        Faculty faculty = new Faculty(1l, "Экономический", "Синий");
        Faculty faculty1 = new Faculty(1l, "Биологически ", "Красный");

        when(facultyRepository.save(faculty)).thenReturn(faculty);
        when(facultyRepository.save(faculty1)).thenReturn(faculty1);

        //Проверка данных

        Assertions.assertEquals(facultyService.addFaculty(faculty), faculty);
        Assertions.assertEquals(facultyService.addFaculty(faculty1), faculty1);

    }

    @Test
    public void studentRepository_test_find_method_success() {
        //Подготовка входных зничений
        long id = 1;
        Faculty faculty = new Faculty(1l, "Экономический", "Синий");

        when(facultyRepository.save(faculty)).thenReturn(faculty);
        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));

        //Проверка данных

        Assertions.assertEquals(facultyService.putFaculty(faculty), faculty);

    }

}
