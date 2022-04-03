package co.guiromao.spring.productservice.repo;

import co.guiromao.spring.productservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
}
