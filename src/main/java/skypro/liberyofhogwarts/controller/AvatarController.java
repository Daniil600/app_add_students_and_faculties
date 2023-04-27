package skypro.liberyofhogwarts.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import skypro.liberyofhogwarts.object.Avatar;
import skypro.liberyofhogwarts.service.StudentAvatarService;
import skypro.liberyofhogwarts.service.StudentServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@RestController
@RequestMapping("avatar")
public class AvatarController {

    private final StudentServiceImpl studentService;
    private final StudentAvatarService studentAvatarService;

    public AvatarController(StudentServiceImpl studentService, StudentAvatarService studentAvatarService) {
        this.studentService = studentService;
        this.studentAvatarService = studentAvatarService;
    }


    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCover(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }

        studentAvatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/awatar/preview")
    public ResponseEntity<byte[]> downloadAwata(@PathVariable Long id) {
        Optional<Avatar> studentCoverOptioanl = studentAvatarService.findStudentCover(id);
        if (studentCoverOptioanl.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            Avatar studentCover = studentCoverOptioanl.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(studentCover.getMediaType()));
            headers.setContentLength(studentCover.getPreview().length);

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(studentCover.getPreview());
        }
    }

    @GetMapping("/{id}/awatar")
    public void downloadAwatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Optional<Avatar> studentCoverOptioanl = studentAvatarService.findStudentCover(id);
        if (studentCoverOptioanl.isEmpty()) {
            throw new RuntimeException();
        }

        Avatar studentCover = studentCoverOptioanl.get();
        Path path = Path.of(studentCover.getFilePath());


        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {

            response.setContentType(studentCover.getMediaType());
            response.setContentLength((int) studentCover.getFileSize());
            is.transferTo(os);
        }

    }
}
