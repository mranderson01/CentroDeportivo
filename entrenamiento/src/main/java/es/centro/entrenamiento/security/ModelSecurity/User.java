package es.centro.entrenamiento.security.ModelSecurity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "id")
    Integer id;

    @Basic
    @Column(nullable = false,name = "username")
    String username;

    @Column(nullable = false,name = "lastname")
    String lastname;

    @Column(name = "firstname")
    String firstname;

    @Column(name = "country")
    String country;

    @Column(name = "password")
    String password;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_Id")},
            inverseJoinColumns = { @JoinColumn(name = "role_Id")}
    )
    private List<Role> roles = new ArrayList<Role>();


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
