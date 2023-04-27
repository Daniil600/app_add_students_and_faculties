package skypro.liberyofhogwarts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.repositories.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl{
    private FacultyRepository facultyRepository;
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public void delFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty putFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }
}
