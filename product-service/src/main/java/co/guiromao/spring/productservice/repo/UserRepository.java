package co.guiromao.spring.productservice.repo;

import co.guiromao.spring.productservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
