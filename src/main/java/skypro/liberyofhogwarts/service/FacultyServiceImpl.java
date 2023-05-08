package skypro.liberyofhogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skypro.liberyofhogwarts.object.Faculty;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.repositories.FacultyRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class FacultyServiceImpl {
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

    public String getMostLenghthFaculty() {
        logger.info("getMostLenghthFaculty is app");
        return facultyRepository.findAll().stream()
                .map(s -> s.getName())
                .max(Comparator.comparingInt(String::length))
                .orElse("Пусто");
    }

    public String checkParaller() {
        logger.info("checkParaller is app");


        long start = System.currentTimeMillis();
        int s = Stream
                .iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        long end = System.currentTimeMillis();

        String answer = "Result: " + s + "\n" +
                "Time of comleted: " + (end - start);

        long start1 = System.currentTimeMillis();
        int s1 = Stream
                .iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        long end1 = System.currentTimeMillis();
        String answer1 = "Result: " + s + "\n" +
                "Time of comleted: " + (end1 - start1);
        return "without parallel: " + answer + "\n" + "with parallel: " + answer1;
    }
}
