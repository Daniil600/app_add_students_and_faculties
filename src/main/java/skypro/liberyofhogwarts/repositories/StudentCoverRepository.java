package skypro.liberyofhogwarts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import skypro.liberyofhogwarts.object.StudentCover;

import java.util.Optional;

public interface StudentCoverRepository extends JpaRepository<StudentCover, Long> {
    Optional<StudentCover> findStudentCoverById(Long id);

}
