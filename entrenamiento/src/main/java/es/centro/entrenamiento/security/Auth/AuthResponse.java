package es.centro.entrenamiento.security.Auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
@Builder
@NoArgsConstructor
public class AuthResponse {
    String token;
    boolean result;

    // Constructor
    public AuthResponse(String token, boolean result) {
        this.token = token;
        this.result = result;
    }

}
