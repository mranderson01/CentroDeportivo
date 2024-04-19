package es.centro.entrenamiento.Interfaces;

import es.centro.entrenamiento.Models.ReserveForm;
import org.springframework.http.ResponseEntity;

public interface IReserveInterface {
    ResponseEntity<?> GetAllReserve();
    ResponseEntity<?> GetOneReserve(int id);
    ResponseEntity<?> PostReserve(ReserveForm reserveForm);
    ResponseEntity<?> PutReserve(int id,ReserveForm reserveForm);
    ResponseEntity<?> DeleteReserve(int id);
}
