package es.centro.entrenamiento.Repositories;

import java.util.Optional;

import es.centro.entrenamiento.security.ModelSecurity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface IRoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findRoleByName(String roleAdmin);
}
