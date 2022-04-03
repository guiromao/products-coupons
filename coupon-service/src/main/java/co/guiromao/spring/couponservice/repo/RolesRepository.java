package co.guiromao.spring.couponservice.repo;

import co.guiromao.spring.couponservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);

}
