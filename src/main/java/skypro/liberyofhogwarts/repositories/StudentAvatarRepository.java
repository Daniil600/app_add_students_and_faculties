package skypro.liberyofhogwarts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skypro.liberyofhogwarts.object.Avatar;

import java.util.Optional;
@Repository

public interface StudentAvatarRepository extends JpaRepository<Avatar, Long> {


}
