package es.centro.entrenamiento.security.ModelSecurity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.centro.entrenamiento.Models.Reserve;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Basic
    @Column(nullable = false,name = "username")
    String username;

    @Column(name = "firstname")
    String firstname;

    @Column(nullable = false,name = "lastname")
    String lastname;

    @Column(name = "phone")
    String phone;

    @Column(name = "password")
    String password;

    //RELATION

    //USER - ROLE
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_Id")},
            inverseJoinColumns = { @JoinColumn(name = "role_Id")}
    )
    @JsonBackReference
    private List<Role> roles = new ArrayList<Role>();

    //USER - RESERVE
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    @JsonBackReference
    private Set<Reserve> reserves = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> theAuthorities = new ArrayList<>();

        this.roles.forEach(role ->
                theAuthorities.add(new SimpleGrantedAuthority(role.getName()))
        );
        return theAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
