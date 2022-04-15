package security.authserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import security.authserver.model.Role;

public interface RolesRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);

}
