package skypro.liberyofhogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    private FacultyRepository facultyRepository;
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        logger.info("FacultyServiceImpl is app");
        this.facultyRepository = facultyRepository;
    }

    public Faculty getFaculty(long id) {
        logger.info("getFaculty is app");
        return facultyRepository.findById(id).get();
    }

    public void delFaculty(long id) {
        logger.info("delFaculty is app");
        facultyRepository.deleteById(id);
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("addFaculty is app");
        return facultyRepository.save(faculty);
    }

    public Faculty putFaculty(Faculty faculty) {
        logger.info("putFaculty is app");
        return facultyRepository.save(faculty);
    }

    public Collection<Faculty> getAll() {
        logger.info("getAll is app");
        return facultyRepository.findAll();
    }
}
