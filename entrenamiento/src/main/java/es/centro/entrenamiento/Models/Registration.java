package es.centro.entrenamiento.Models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    @NotBlank(message = "El campo de Nombre  no puede estar vacío")
    @Size(min = 3, message = "El Nombre debe tener al menos 3 caracteres")
    private String nombre;

    @NotBlank(message = "El campo de correo electrónico no puede estar vacío")
    @Email(message = "Debe proporcionar un correo electrónico válido")
    private String email;

    @NotBlank(message = "El campo de contraseña no puede estar vacío")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
}
