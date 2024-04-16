package es.centro.entrenamiento.security.ModelSecurity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role",uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "id")
    private int id;

    @Column(nullable = false,name = "name")
    private String name;

    //Relationships
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<User>();
}
