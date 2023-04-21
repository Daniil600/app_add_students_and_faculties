package skypro.liberyofhogwarts.service;

import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.object.Student;

import java.util.Collection;

public interface FacultyService {
    Faculty getFaculty(long id);
    void delFaculty(long id);
    Faculty addFaculty(Faculty faculty);
    Faculty putFaculty(Faculty faculty);


    Collection<Faculty> getAll();

}
