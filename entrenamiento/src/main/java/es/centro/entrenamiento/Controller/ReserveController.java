package es.centro.entrenamiento.Controller;

import es.centro.entrenamiento.Interfaces.IReserveInterface;
import es.centro.entrenamiento.Models.ReserveForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reserve")
public class ReserveController {

    @Autowired
    IReserveInterface iReserveInterface;

    @GetMapping("/")
    ResponseEntity<?> getAll(){
        ResponseEntity<?> responseEntity = iReserveInterface.GetAllReserve();

        if (responseEntity.getStatusCode().is5xxServerError()){
            return ResponseEntity.status(500).body("Hubo un fallo en el servidor. Contacto con el adminsitrador.");
        }

        return ResponseEntity.status(200).body(responseEntity.getBody());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getOneReserve(@PathVariable int id){
        ResponseEntity<?> responseEntity = iReserveInterface.GetOneReserve(id);
        if (responseEntity.getStatusCode().is4xxClientError()){
            return ResponseEntity.status(404).body("No tienes acceso a esta reserva.");
        }
        return ResponseEntity.status(200).body(responseEntity.getBody());
    }

    @PostMapping("/createReserve")
    ResponseEntity<?> createReserve(@RequestBody ReserveForm reserveForm,
                                    BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return  ResponseEntity.status(400).body("Hubo problemas en la validación del formulario. Vuelva a intentarlo.");
        }
        ResponseEntity<?> responseEntity = iReserveInterface.PostReserve(reserveForm);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return  ResponseEntity.status(200).body(responseEntity.getBody());
        }
        return  ResponseEntity.status(500).body("Hubo algun problema desconocido, vuelva a intentarlo mas tarde.");
    }

    @PutMapping("/updateReserve/{id}")
    ResponseEntity<?> updateReserve (@PathVariable int id,
                                     @Valid @RequestBody ReserveForm reserveForm,
                                     BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return  ResponseEntity.status(400).body("Hubo problemas en la validación del formulario. Vuelva a intentarlo.");
        }
        ResponseEntity<?> responseEntity = iReserveInterface.PutReserve(id,reserveForm);

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return  ResponseEntity.status(200).body(responseEntity.getBody());
        }
        return  ResponseEntity.status(500).body("Hubo algun problema desconocido, vuelva a intentarlo mas tarde.");
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteReserve(@PathVariable int id){
        if (id<0){
            return  ResponseEntity.status(400).body("Se tiene que introducie un numero superior a 0. vuelva a intentarlo.");
        }

        ResponseEntity<?> responseEntity = iReserveInterface.DeleteReserve(id);
        if (responseEntity.getStatusCode().is4xxClientError()){
            return  ResponseEntity.status(400).body("No se pudo encontrar la reserva que se intenta eliminar. Por favor vuelva a intentarlo con una reserva correcta.");
        }

        if (responseEntity.getStatusCode().is2xxSuccessful()){
            return  ResponseEntity.status(200).body(responseEntity.getBody());
        }
        return  ResponseEntity.status(500).body("Hubo algun problema desconocido, vuelva a intentarlo mas tarde.");
    }
}
