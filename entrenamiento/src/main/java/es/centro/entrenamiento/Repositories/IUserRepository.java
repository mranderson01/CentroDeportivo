package es.centro.entrenamiento.Repositories;
import java.util.Optional;

import es.centro.entrenamiento.security.ModelSecurity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
