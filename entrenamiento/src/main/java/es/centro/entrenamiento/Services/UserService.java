package es.centro.entrenamiento.Services;

import es.centro.entrenamiento.Repositories.IRoleRepository;
import es.centro.entrenamiento.Repositories.IUserRepository;
import es.centro.entrenamiento.security.ModelSecurity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class UserService implements UserDetailsService {
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    RoleService roleSvc;

    @Autowired
    IRoleRepository roleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> usuario = iUserRepository.findByUsername(username);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        List<GrantedAuthority> authorities = usuario.get().getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                usuario.get().getUsername(),
                usuario.get().getPassword(),
                authorities
        );
    }
}
