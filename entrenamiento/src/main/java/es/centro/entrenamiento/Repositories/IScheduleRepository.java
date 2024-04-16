package es.centro.entrenamiento.Repositories;

import es.centro.entrenamiento.Models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Transactional
public interface IScheduleRepository extends JpaRepository<Schedule,Integer> {
    Optional<Schedule> findByDay(LocalDate Day);
}
