package es.centro.entrenamiento.Services;

import es.centro.entrenamiento.Interfaces.IReserveInterface;
import es.centro.entrenamiento.Models.Reserve;
import es.centro.entrenamiento.Models.ReserveForm;
import es.centro.entrenamiento.Models.Schedule;
import es.centro.entrenamiento.Models.Zone;
import es.centro.entrenamiento.Repositories.IReserveRepository;
import es.centro.entrenamiento.Repositories.IUserRepository;
import es.centro.entrenamiento.Repositories.IZoneRepository;
import es.centro.entrenamiento.RSAbstract.ReserveRepositoryImpl;
import es.centro.entrenamiento.security.ModelSecurity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReserveServiceimpl implements IReserveInterface {

    @Autowired
    IReserveRepository iReserveRepository;

    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    UserService userService;

    @Autowired
    IZoneRepository iZoneRepository;

    @Autowired
    ReserveRepositoryImpl _reserveRepositoryImpl;

    @Override
    public ResponseEntity<?> GetAllReserve() {

        Optional<List<Reserve>> listReserve = _reserveRepositoryImpl.getAllReserve();

        if (listReserve.isPresent()){
            return ResponseEntity.ok().body(listReserve.get());
        }
        return ResponseEntity.internalServerError().build();
    }
    @Override
    public ResponseEntity<?> GetOneReserve(int id) {

        Reserve reserve = _reserveRepositoryImpl.findById(id);

        if (reserve == null){
            return ResponseEntity.badRequest().build();
        }

        return  ResponseEntity.ok(reserve);
    }
    @Override
    public ResponseEntity<?> DeleteReserve(int id) {
        _reserveRepositoryImpl.deleteReserve(id);
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<?> PostReserve(ReserveForm reserveForm) {

        String username = userService.GetUsername();

        //CREANDO UNA RESERVA NUEVA
        Reserve reserveNew = new Reserve();

        Optional<User> UserSeek=iUserRepository.findByUsername(username);

        UserSeek.ifPresent(reserveNew::setUser);

        //AÑADIR UN HORARIO A LA RESERVA
        Schedule scheduleNew = new Schedule();
        scheduleNew.setDay(LocalDate.parse(reserveForm.getDay()));
        scheduleNew.setStartTime(LocalDateTime.parse(reserveForm.getStartTime()));
        scheduleNew.setEndTime(LocalDateTime.parse(reserveForm.getEndTime()));
        reserveNew.setSchedule(scheduleNew);

        //AÑADIR LA ZONE A LA RESERVA
        Optional<Zone>zoneseek = iZoneRepository.findById(reserveForm.getIdZone());
        zoneseek.ifPresent(reserveNew::setZone);

        iReserveRepository.save(reserveNew);

        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<?> PutReserve(int id, ReserveForm reserveForm) {

        Optional<Reserve> reserveSeek = iReserveRepository.findById(id);
        if (reserveSeek.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        //MODIFICAR RESERVA

        //AÑADIR UN HORARIO A LA RESERVA
        Schedule scheduleNew = new Schedule();
        scheduleNew.setDay(LocalDate.parse(reserveForm.getDay()));
        scheduleNew.setStartTime(LocalDateTime.parse(reserveForm.getStartTime()));
        scheduleNew.setEndTime(LocalDateTime.parse(reserveForm.getEndTime()));
        reserveSeek.get().setSchedule(scheduleNew);

        //AÑADIR LA ZONE A LA RESERVA
        Optional<Zone>zoneseek = iZoneRepository.findById(reserveForm.getIdZone());
        zoneseek.ifPresent(reserveSeek.get()::setZone);

        iReserveRepository.save(reserveSeek.get());

        return ResponseEntity.ok().build();
    }

}
