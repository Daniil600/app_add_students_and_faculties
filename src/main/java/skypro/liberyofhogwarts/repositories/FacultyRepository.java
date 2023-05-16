package skypro.liberyofhogwarts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skypro.liberyofhogwarts.object.Faculty;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
