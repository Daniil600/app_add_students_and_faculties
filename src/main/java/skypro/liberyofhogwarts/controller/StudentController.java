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
    public ResponseEntity getStudentByAge(@RequestParam(required = false) Integer age,
                                          @RequestParam(required = false) String name,
                                          @RequestParam(required = false) Long id){
        if(age != null){
            return ResponseEntity.ok(studentService.findStudentByAge(age));
        }
        if(age != null){
            return ResponseEntity.ok(studentService.findStudentByName(name));
        }
        if(age != null){
            return ResponseEntity.ok(studentService.findStudentById(id));
        }

        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{min}/{max}")
    public ResponseEntity getStudentByAge(@PathVariable Integer min, Integer max){
        if(min != null && max != null){
            return ResponseEntity.ok(studentService.findStudentByAgeBetween(min,max));
        }
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity getStudentByAge(@PathVariable String name){
        if(name != null || !name.isBlank()){
            return ResponseEntity.ok(studentService.findStudentByName(name));
        }

        return ResponseEntity.ok(studentService.getAll());
    }
}
