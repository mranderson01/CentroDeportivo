package es.centro.entrenamiento.Repositories;

import es.centro.entrenamiento.Models.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Repository
@Transactional
public interface IZoneRepository extends JpaRepository<Zone,Integer> {
    Optional<Zone> findByName(String name);
}
