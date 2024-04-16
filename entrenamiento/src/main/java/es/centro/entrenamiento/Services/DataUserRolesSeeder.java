package es.centro.entrenamiento.Services;

import es.centro.entrenamiento.Repositories.IRoleRepository;
import es.centro.entrenamiento.Repositories.IUserRepository;
import es.centro.entrenamiento.security.ModelSecurity.Role;
import es.centro.entrenamiento.security.ModelSecurity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataUserRolesSeeder implements CommandLineRunner {
    @Autowired
    private IUserRepository usuarioRepository;
    @Autowired
    private IRoleRepository rolRepository;

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
        Optional<User> usuario1 = usuarioRepository.findByUsername("admin@clubnautico.com");
        if (usuario1.isEmpty()){
            User admin = new User();
            admin.setFirstname("admin");
            admin.setLastname("admin");
            admin.setUsername("admin@clubnautico.com");
            admin.setCountry("spain");
            admin.setPassword( new BCryptPasswordEncoder().encode("Asdf1234!"));
            admin.getRoles().add(roles.getFirst());
            usuarioRepository.save(admin);
        }

        Optional<User> usuario2=usuarioRepository.findByUsername("owner@clubnautico.com");
        if (usuario2.isEmpty()){
            User user = new User();
            user.setFirstname("owner");
            user.setLastname("owner");
            user.setUsername("owner@clubnautico.com");
            user.setCountry("spain");

            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1324!"));
            user.getRoles().add(roles.get(1));
            usuarioRepository.save(user);
        }

        Optional<User> usuario3=usuarioRepository.findByUsername("worker@clubnautico.com");
        if (usuario3.isEmpty()){
            User user = new User();
            user.setFirstname("worker");
            user.setLastname("worker");
            user.setUsername("worker@clubnautico.com");
            user.setCountry("spain");

            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1324!"));
            user.getRoles().add(roles.get(2));
            usuarioRepository.save(user)
            ;
        }

        Optional<User> usuario4 = usuarioRepository.findByUsername("user@clubnautico.com");
        if (usuario4.isEmpty()){
            User user = new User();
            user.setFirstname("user");
            user.setLastname("user");
            user.setUsername("user@clubnautico.com");
            user.setCountry("spain");
            user.setPassword(new BCryptPasswordEncoder().encode("Asdf1324!"));
            user.getRoles().add(roles.get(3));
            usuarioRepository.save(user);
        }
    }
}
