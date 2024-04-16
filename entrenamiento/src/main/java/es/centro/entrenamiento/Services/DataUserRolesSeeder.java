package es.centro.entrenamiento.Services;

import es.centro.entrenamiento.Models.Reserve;
import es.centro.entrenamiento.Models.Schedule;
import es.centro.entrenamiento.Models.Zone;
import es.centro.entrenamiento.Repositories.*;
import es.centro.entrenamiento.security.ModelSecurity.Role;
import es.centro.entrenamiento.security.ModelSecurity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DataUserRolesSeeder implements CommandLineRunner {
    @Autowired
    private IUserRepository usuarioRepository;
    @Autowired
    private IRoleRepository rolRepository;

    @Autowired
    private IZoneRepository iZoneRepository;
    @Autowired
    private IScheduleRepository iScheduleRepository;
    @Autowired
    private IReserveRepository iReserveRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        //ROLES

        List<Role> roles = new ArrayList<>();


        Optional<Role> rolAdmin = rolRepository.findRoleByName("ROLE_ADMIN");
        rolAdmin.ifPresent(roles::add);

        if (rolAdmin.isEmpty()) {
            Role rolAdmin1 = new Role();
            rolAdmin1.setName("ROLE_ADMIN");
            roles.add(rolAdmin1);
            rolRepository.save(rolAdmin1);
        }

        Optional<Role> roleOwner = rolRepository.findRoleByName("ROLE_OWNER");
        roleOwner.ifPresent(roles::add);
        if (roleOwner.isEmpty()) {
            Role rolManager1 = new Role();
            rolManager1.setName("ROLE_OWNER");
            roles.add(rolManager1);
            rolRepository.save(rolManager1);
        }

        Optional<Role> rolUser = rolRepository.findRoleByName("ROLE_USER");
        rolUser.ifPresent(roles::add);

        if (rolUser.isEmpty()) {
            Role rolUser1 = new Role();
            rolUser1.setName("ROLE_USER");
            roles.add(rolUser1);
            rolRepository.save(rolUser1);
        }

        Optional<Role> rolWorker = rolRepository.findRoleByName("ROLE_WORKER");
        rolWorker.ifPresent(roles::add);

        if (rolWorker.isEmpty()) {
            Role rolUser1 = new Role();
            rolUser1.setName("ROLE_WORKER");
            roles.add(rolUser1);
            rolRepository.save(rolUser1);
        }

        //USUARIOS
        List<User> users = new ArrayList<>();

        Optional<User> usuario1 = usuarioRepository.findByUsername("admin@clubnautico.com");
        usuario1.ifPresent(users::add);

        if (usuario1.isEmpty()){
            User admin = new User();
            admin.setUsername("admin@clubnautico.com");
            admin.setFirstname("admin");
            admin.setLastname("admin");
            admin.setPhone("123456789");
            admin.setPassword( new BCryptPasswordEncoder().encode("Asdf1234!"));
            admin.getRoles().add(roles.getFirst());

            users.add(admin);
            usuarioRepository.save(admin);
        }

        Optional<User> usuario2=usuarioRepository.findByUsername("owner@clubnautico.com");
        usuario2.ifPresent(users::add);

        if (usuario2.isEmpty()){
            User user = new User();
            user.setUsername("owner@clubnautico.com");
            user.setFirstname("owner");
            user.setLastname("owner");
            user.setPhone("123456789");
            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1324!"));
            user.getRoles().add(roles.get(1));

            users.add(user);
            usuarioRepository.save(user);
        }

        Optional<User> usuario3=usuarioRepository.findByUsername("worker@clubnautico.com");
        usuario3.ifPresent(users::add);

        if (usuario3.isEmpty()){
            User user = new User();
            user.setUsername("worker@clubnautico.com");
            user.setFirstname("worker");
            user.setLastname("worker");
            user.setPhone("123456789");
            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1324!"));
            user.getRoles().add(roles.get(2));

            users.add(user);
            usuarioRepository.save(user);
        }

        Optional<User> usuario4 = usuarioRepository.findByUsername("user@clubnautico.com");
        usuario4.ifPresent(users::add);

        if (usuario4.isEmpty()){
            User user = new User();
            user.setUsername("user@clubnautico.com");
            user.setFirstname("user");
            user.setLastname("user");
            user.setPhone("123456789");
            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1324!"));
            user.getRoles().add(roles.get(3));

            users.add(user);
            usuarioRepository.save(user);
        }

        //ZONAS
        Set<Zone> zones = new HashSet<>();

        Optional<Zone> Zone1 = iZoneRepository.findByName("funcional");
        Zone1.ifPresent(zones::add);
        if (Zone1.isEmpty()){
            Zone zone1 = new Zone();
            zone1.setName("funcional");
            zones.add(zone1);
            iZoneRepository.save(zone1);
        }

        Optional<Zone> Zone2 = iZoneRepository.findByName("hierro");
        Zone2.ifPresent(zones::add);
        if (Zone2.isEmpty()){
            Zone zone2 = new Zone();
            zone2.setName("hierro");
            zones.add(zone2);
            iZoneRepository.save(zone2);
        }

        //HORARIO y RESERVA

        //CREA RESERVA 1

        Optional<Reserve> reserve1 = iReserveRepository.findByUser(users.getFirst());
        if (reserve1.isEmpty()){
            Reserve reserve = new Reserve();

            //INSERTAR HORARIO1
                Schedule schedule1 = new Schedule();
                schedule1.setDay(LocalDate.now());
                schedule1.setStartTime(LocalDateTime.now());
                schedule1.setEndTime(LocalDateTime.now().plusHours(2));

            reserve.setSchedule(schedule1);

            //INSERTAR USUARIO1
            reserve.setUser(users.getFirst());
            iReserveRepository.save(reserve);
        }

        Optional<Reserve> reserve2 = iReserveRepository.findByUser(users.get(1));
        if (reserve2.isEmpty()) {

            //CREA RESERVA 2
            Reserve reserve = new Reserve();

            //INSERTAR HORARIO2
                Schedule schedule2 = new Schedule();
                schedule2.setDay(LocalDate.now());
                schedule2.setStartTime(LocalDateTime.now());
                schedule2.setEndTime(LocalDateTime.now().plusHours(2));

            reserve.setSchedule(schedule2);

            //INSERTAR USUARIO 3
            reserve.setUser(users.get(1));
            iReserveRepository.save(reserve);
        }
        Optional<Reserve> reserve3 = iReserveRepository.findByUser(users.get(1));
        if (reserve3.isEmpty()) {
            //CREA RESERVA 3
            Reserve reserve = new Reserve();

                //INSERTAR HORARIO 3
                Schedule schedule3 = new Schedule();
                schedule3.setDay(LocalDate.now());
                schedule3.setStartTime(LocalDateTime.now());
                schedule3.setEndTime(LocalDateTime.now().plusHours(2));
            reserve.setSchedule(schedule3);

            //INSERTAR USUARIO3
            reserve.setUser(users.get(2));
            //INSERTAR USUARIO 3
            reserve.setUser(users.get(1));
            iReserveRepository.save(reserve);
        }

        //CREA RESERVA 4
        Optional<Reserve> reserve4 = iReserveRepository.findByUser(users.get(1));
        if (reserve4.isEmpty()) {

            Reserve reserve = new Reserve();

            //INSERTAR HORARIO 4
                Schedule schedule4 = new Schedule();
                schedule4.setDay(LocalDate.now());
                schedule4.setStartTime(LocalDateTime.now());
                schedule4.setEndTime(LocalDateTime.now().plusHours(2));
                reserve.setSchedule(schedule4);

            //INSERTAR USUARIO 4
            reserve.setUser(users.get(3));
            iReserveRepository.save(reserve);
        }
    }
}
