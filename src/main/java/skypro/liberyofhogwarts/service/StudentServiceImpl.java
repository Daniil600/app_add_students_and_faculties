package skypro.liberyofhogwarts.service;

import org.springframework.stereotype.Service;
import skypro.liberyofhogwarts.object.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private Long idStudent = 0l;

    @Override
    public Student getStudent(Long id) {
        return students.get(id);
    }

    @Override
    public Student delStudent(Long id) {
        return students.remove(id);
    }

    @Override
    public Student addStudent(Student student) {
        student.setId(++idStudent);
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student putStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Collection<Student> getSort(int age) {
        return students.values().stream().filter(student -> student.getAge()==age).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        students.clear();
        idStudent = 0l;
    }

    @Override
    public Collection<Student> getAll() {
        return students.values();
    }
}
