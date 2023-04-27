package skypro.liberyofhogwarts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import skypro.liberyofhogwarts.object.Avatar;

import java.util.Optional;

public interface StudentAvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findStudentCoverById(Long id);

}
