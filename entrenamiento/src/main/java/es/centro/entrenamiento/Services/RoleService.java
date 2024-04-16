package es.centro.entrenamiento.Services;
import es.centro.entrenamiento.Repositories.IRoleRepository;
import es.centro.entrenamiento.security.ModelSecurity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    IRoleRepository roleRepository;

     Role ReadSingle(Integer idRole){
        return GetRole(idRole);
    }

    public Role GetRole(Integer idRole){
        Role RoleRead = null;
        Optional<Role> RoleFound = roleRepository.findById(idRole);

        if (RoleFound.isPresent()) {
            RoleRead = RoleFound.get();
        }

        return RoleRead;
    }
}
