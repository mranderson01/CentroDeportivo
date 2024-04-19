package es.centro.entrenamiento.Repositories;

import es.centro.entrenamiento.Models.Reserve;
import es.centro.entrenamiento.security.ModelSecurity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IReserveRepository extends JpaRepository<Reserve, Integer> {
    Optional<Reserve> findByUser(User user);
    Optional<List<Reserve>> findAllByUser(User user);

    @Query("SELECT r FROM Reserve r WHERE r.id = :reserveId AND r.user.username = :username")
    Optional<Reserve> findReserveByIdAndUsername(@Param("reserveId") int  reserveId, @Param("username") String username);

    @Query("SELECT r FROM Reserve r WHERE r.user.username = :username ORDER BY r.schedule.startTime ASC")
    Optional<Reserve> findFirstReserveByUsername(@Param("username") String username);


    @Query("DELETE FROM Reserve r WHERE r.id = :reserveId AND r.user.username = :username")
    void deleteReserveByIdAndUsername( @Param("reserveId") int reserveId, @Param("username") String username);

    @Modifying
    @Query("SELECT r FROM Reserve r WHERE r.user.username = :username ORDER BY r.schedule.day DESC")
    void findAllReserveByUsername(@Param("username") String username);
}
