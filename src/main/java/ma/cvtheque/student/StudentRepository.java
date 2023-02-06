package ma.cvtheque.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

  boolean existsByEmailIgnoreCase(String email);

  Optional<Student> findByEmail(String email);

}
