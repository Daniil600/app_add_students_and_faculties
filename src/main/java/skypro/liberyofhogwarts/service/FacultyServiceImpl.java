package skypro.liberyofhogwarts.service;

import org.springframework.stereotype.Service;
import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.object.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService{

    private HashMap<Long, Faculty> faculties = new HashMap<>();
    private Long idFaculty = 0l;
    @Override
    public Faculty getFaculty(Long id) {
        return faculties.get(id);
    }

    @Override
    public Faculty delFaculty(Long id) {
        return faculties.remove(id);
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++idFaculty);
        faculties.put(idFaculty, faculty);
        return faculty;
    }

    @Override
    public Faculty putFaculty(Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override
    public Collection<Faculty> getAll() {
        return faculties.values();
    }

    @Override
    public void clear() {
        faculties.clear();
        idFaculty = 0l;
    }

    @Override
    public Collection<Faculty> getSort(String color) {
        return faculties.values().stream().filter(faculty -> faculty.getColor().equals(color)).collect(Collectors.toList());
    }
}
