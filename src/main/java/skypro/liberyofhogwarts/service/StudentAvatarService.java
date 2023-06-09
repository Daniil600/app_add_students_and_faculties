package skypro.liberyofhogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import skypro.liberyofhogwarts.object.Avatar;
import skypro.liberyofhogwarts.object.Student;
import skypro.liberyofhogwarts.repositories.StudentAvatarRepository;
import skypro.liberyofhogwarts.repositories.StudentRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class StudentAvatarService {
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
    Logger logger = LoggerFactory.getLogger(StudentAvatarService.class);

    private final StudentAvatarRepository studentAvatarRepository;
    private final StudentRepository studentRepository;

    public StudentAvatarService(StudentAvatarRepository studentCoverRepository, StudentRepository studentRepository) {
        this.studentAvatarRepository = studentCoverRepository;
        this.studentRepository = studentRepository;
    }

    public Optional<Avatar> findStudentCover(long id) {
        logger.info("findStudentCover is app");
        return studentAvatarRepository.findById(id);
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("uploadAvatar is app");
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
        Optional<Avatar> studentCover = findStudentCover(studentId);
        Avatar avatar;

        if (studentCover.isEmpty()) {
            avatar = new Avatar();
        } else {
            avatar = studentCover.get();
        }

        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatar.setPreview(generateImagePriview(filePath));

        studentAvatarRepository.save(avatar);

    }

    private byte[] generateImagePriview(Path filePath) throws IOException {
        logger.info("generateImagePriview is app");
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
        logger.info("getExtensions is app");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


    public List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {
        logger.info("getAllAvatars is app");

        PageRequest pageRequest = PageRequest.of(pageNumber - 1 , pageSize);
        return studentAvatarRepository.findAll(pageRequest).getContent();
    }
}
