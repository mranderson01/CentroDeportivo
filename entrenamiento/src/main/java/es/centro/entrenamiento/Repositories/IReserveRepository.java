package es.centro.entrenamiento.Repositories;

import es.centro.entrenamiento.Models.Reserve;
import es.centro.entrenamiento.security.ModelSecurity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface IReserveRepository extends JpaRepository<Reserve, Integer> {
    Optional<Reserve> findByUser(User user);
}
