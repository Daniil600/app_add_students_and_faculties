package skypro.liberyofhogwarts.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.object.StudentCover;
import skypro.liberyofhogwarts.service.StudentCoverService;
import skypro.liberyofhogwarts.service.StudentServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentServiceImpl studentService;
    private final StudentCoverService studentCoverService;

    public StudentController(StudentServiceImpl studentService, StudentCoverService studentCoverService) {
        this.studentService = studentService;
        this.studentCoverService = studentCoverService;
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
                                          @RequestParam(required = false) Long id) {
        if (age != null) {
            return ResponseEntity.ok(studentService.findStudentByAge(age));
        }
        if (age != null) {
            return ResponseEntity.ok(studentService.findStudentByName(name));
        }
        if (age != null) {
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

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCover(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }

        studentCoverService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/awatar/preview")
    public ResponseEntity<byte[]> downloadAwata(@PathVariable Long id) {
        Optional<StudentCover> studentCoverOptioanl = studentCoverService.findStudentCover(id);
        if (studentCoverOptioanl.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            StudentCover studentCover = studentCoverOptioanl.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(studentCover.getMediaType()));
            headers.setContentLength(studentCover.getPreview().length);

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(studentCover.getPreview());
        }
    }

    @GetMapping("/{id}/awatar")
    public void downloadAwatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Optional<StudentCover> studentCoverOptioanl = studentCoverService.findStudentCover(id);
        if (studentCoverOptioanl.isEmpty()) {
            throw new RuntimeException();
        }

        StudentCover studentCover = studentCoverOptioanl.get();
        Path path = Path.of(studentCover.getFilePath());


        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {

            response.setContentType(studentCover.getMediaType());
            response.setContentLength((int) studentCover.getFileSize());
            is.transferTo(os);
        }

    }
}

