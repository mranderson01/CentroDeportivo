package es.centro.entrenamiento.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Integer id) {
        super("Usuario no encontrado " + id);
    }
}
