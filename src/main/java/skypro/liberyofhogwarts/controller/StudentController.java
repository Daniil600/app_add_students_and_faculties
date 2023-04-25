package skypro.liberyofhogwarts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.service.StudentService;
import skypro.liberyofhogwarts.service.StudentServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> postStudent(@RequestBody Student student) {
        Student createdStudent = studentService.addStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Student> delStudent(@PathVariable long id) {
        studentService.delStudent(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Student> putStudent(@RequestBody Student student) {
        Student putStudent = studentService.putStudent(student);
        if (putStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(putStudent);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity getStudentByAge(@PathVariable(required = false) Integer age){
        if(age != null){
            return ResponseEntity.ok(studentService.findStudentByAgeBetween(age));
        }

        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity getStudentByAge(@PathVariable(required = false) String name){
        if(name != null || !name.isBlank()){
            return ResponseEntity.ok(studentService.findStudentByName(name));
        }

        return ResponseEntity.ok(studentService.getAll());
    }
}
