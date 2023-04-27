package skypro.liberyofhogwarts.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.object.StudentCover;
import skypro.liberyofhogwarts.repositories.StudentCoverRepository;
import skypro.liberyofhogwarts.repositories.StudentRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import static io.swagger.v3.core.util.AnnotationsUtils.getExtensions;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class StudentCoverService {
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final StudentServiceImpl studentService;
    private final StudentCoverRepository studentCoverRepository;
    private final StudentRepository studentRepository;


    public StudentCoverService(StudentServiceImpl studentService, StudentCoverRepository studentCoverRepository, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentCoverRepository = studentCoverRepository;
        this.studentRepository = studentRepository;
    }

    public Optional<StudentCover> findStudentCover(long id) {
        return studentCoverRepository.findStudentCoverById(id);
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException();
        }
        Student student = optionalStudent.get();

        Path filePath = Path.of(avatarsDir, studentId + "." + getExtensions(Objects.requireNonNull(avatarFile.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Optional<StudentCover> studentCover = findStudentCover(studentId);
        StudentCover avatar;

        if (studentCover.isEmpty()) {
            avatar = new StudentCover();
        } else {
            avatar = studentCover.get();
        }

        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatar.setPreview(generateImagePriview(filePath));

        studentCoverRepository.save(avatar);

    }

    private byte[] generateImagePriview(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth()/100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image,0,0,100, height,null);
            graphics.dispose();

            ImageIO.write(preview, getExtensions(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }

    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


}
