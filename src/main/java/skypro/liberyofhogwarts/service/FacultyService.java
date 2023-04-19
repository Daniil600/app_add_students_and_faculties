package skypro.liberyofhogwarts.service;

import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.object.Student;

import java.util.Collection;

public interface FacultyService {
    Faculty getFaculty(Long id);
    Faculty delFaculty(Long id);
    Faculty addFaculty(Faculty faculty);
    Faculty putFaculty(Faculty faculty);


    Collection<Faculty> getAll();

    void clear();

    Collection<Faculty> getSort(String color);
}
