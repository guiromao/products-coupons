package security.authserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import security.authserver.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

}
