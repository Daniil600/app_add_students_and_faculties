package skypro.liberyofhogwarts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.service.StudentAvatarService;
import skypro.liberyofhogwarts.service.StudentServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentServiceImpl studentService;
    private final StudentAvatarService studentAvatarService;

    public StudentController(StudentServiceImpl studentService, StudentAvatarService studentAvatarService) {
        this.studentService = studentService;
        this.studentAvatarService = studentAvatarService;
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @PostMapping
    public ResponseEntity<Student> postStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.addStudent(student));
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

    @GetMapping("/getby")
    public ResponseEntity getStudentBy(@RequestParam(required = false) Integer age,
                                          @RequestParam(required = false) String name) {
        if (age != null) {
            return ResponseEntity.ok(studentService.findStudentByAge(age));
        }
        if (name != null) {
            return ResponseEntity.ok(studentService.findStudentByName(name));
        }


        return ResponseEntity.ok(studentService.getAll());
    }
    @GetMapping("getby/id")
    public ResponseEntity getStudentById(@RequestParam(required = false) Long id){
        if (id != null) {
            return ResponseEntity.ok(studentService.findStudentById(id));
        }
        return ResponseEntity.ok(studentService.getAll());
    }



    @GetMapping("/{min}/{max}")
    public ResponseEntity getStudentByAge(@PathVariable Integer min, Integer max) {
        if (min != null && max != null) {
            return ResponseEntity.ok(studentService.findStudentByAgeBetween(min, max));
        }
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity getStudentByAge(@PathVariable String name) {
        if (name != null || !name.isBlank()) {
            return ResponseEntity.ok(studentService.findStudentByName(name));
        }

        return ResponseEntity.ok(studentService.getAll());
    }


}

