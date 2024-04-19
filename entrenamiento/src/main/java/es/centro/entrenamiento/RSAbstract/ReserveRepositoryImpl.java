package es.centro.entrenamiento.RSAbstract;

import es.centro.entrenamiento.Models.Reserve;
import es.centro.entrenamiento.Repositories.IReserveRepository;
import es.centro.entrenamiento.Repositories.IUserRepository;
import es.centro.entrenamiento.security.ModelSecurity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class ReserveRepositoryImpl implements ApplicationListener<InteractiveAuthenticationSuccessEvent>{

    @Autowired
    IReserveRepository iReserveRepository;
    @Autowired
    IUserRepository iUserRepository;

    //private final Map<Integer,Reserve> reserveMap = new HashMap<>();
    private final Map<Integer,Reserve> reserveMap;

    public ReserveRepositoryImpl(Map<Integer, Reserve> reserveMap) {
        this.reserveMap = reserveMap;
    }


    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        loadReservesForUser();
    }

    public void loadReservesForUser(){

        String username = getUsername();

        Optional<User> userSeek = iUserRepository.findByUsername(username);

        Optional<List<Reserve>> reserves = Optional.empty();

        if (userSeek.isPresent()){
             reserves = iReserveRepository.findAllByUser(userSeek.get());
        }

        if(reserves.isPresent()){
            for (Reserve reserve : reserves.get()) {
                reserveMap.put(reserve.getId(), reserve);
            }
        }
    }

    public Optional<List<Reserve>> getAllReserve() {

        if (reserveMap.isEmpty()) {
            loadReservesForUser();
        }

        List<Reserve> reserveList = new ArrayList<>(reserveMap.values());
        return Optional.of(reserveList);
    }

    public Reserve findById(int id) {
        Optional<Reserve> reserve = Optional.ofNullable(reserveMap.get(id));

        if (reserve.isEmpty()) {
            String username = getUsername();

            Optional<Reserve> reserveSeek = iReserveRepository.findReserveByIdAndUsername(id,username);

            if (reserveSeek.isPresent()) {
                reserveMap.put(reserveSeek.get().getId(),reserveSeek.get());
                return reserveMap.get(id);
            }

            return  new Reserve();
        }

        return reserve.get();
    }

    public void addReserve(Reserve reserve) {
        reserveMap.put(reserve.getId(), reserve);
        iReserveRepository.save(reserve);
    }

    public String getUsername(){
        Authentication _authentication =  SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = null;

        String username = null;

        if (_authentication != null) {
            Object principal = _authentication.getPrincipal();
            if (principal instanceof UserDetails) {

                userDetails = (UserDetails) principal;
                username =  userDetails.getUsername();
            }
        }

        return username;
    }

    public void deleteReserve(int id){

        reserveMap.remove(id);

        String username = getUsername();

        iReserveRepository.deleteReserveByIdAndUsername(id,username);
    }
}

