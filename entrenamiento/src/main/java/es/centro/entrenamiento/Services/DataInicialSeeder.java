package es.centro.entrenamiento.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class DataInicialSeeder  {
    //implements CommandLineRunner
    /*
    @Autowired
    private IBoatRepository iBoatRepository;
    @Autowired
    private IDepartureRepository iDepartureRepository;
    @Autowired
    private IShipownerRepository iShipownerRepository;


    @Override
    public void run(String... args) throws Exception {

        //Socios
        /*Partner partner1 = iPartnerRepository.findByUsername("neo@clubnautico.com");
        if (partner1==null){
            partner1 = new Partner();
            partner1.setUsername("neo@clubnautico.com");
            partner1.setName("Thomas");
            partner1.setSurname("Anderson");
            partner1.setCountry("EEUU");
            iPartnerRepository.save(partner1);
        }

        Partner partner2 = iPartnerRepository.findByUsername("morfeo@clubnautico.com");
        if (partner2==null){
            partner2 = new Partner();
            partner2.setUsername("morfeo@clubnautico.com");
            partner2.setName("unknow");
            partner2.setSurname("unknow");
            partner2.setCountry("unknow");
            iPartnerRepository.save(partner2);
        }

        Partner partner3 = iPartnerRepository.findByUsername("trinity@clubnautico.com");
        if (partner3==null){
            partner3 = new Partner();
            partner3.setUsername("trinity@clubnautico.com");
            partner3.setName("unknow");
            partner3.setSurname("unknow");
            partner3.setCountry("unknow");
            iPartnerRepository.save(partner3);
        }*/

        //Botes

        /*List<Boat> listBoat = new ArrayList<>();
        Optional<Boat> boat1 = iBoatRepository.findByNumberberth(1);
        if(boat1.isEmpty()){
            Boat boat = new Boat();
            boat.setNameenrollment("Titanic");
            boat.setName("Titanic");
            boat.setNumberberth(1);
            boat.setFee(1500);
            listBoat.add(boat);
            iBoatRepository.save(boat);
        };

        Optional<Boat> boat2 = iBoatRepository.findByNumberberth(2);
        if(boat2.isEmpty()){
            Boat boat = new Boat();
            boat.setNameenrollment("Neptune");
            boat.setName("Neptune");
            boat.setNumberberth(2);
            boat.setFee(1500);
            listBoat.add(boat);
            iBoatRepository.save(boat);
        };

        Optional<Boat> boat3 = iBoatRepository.findByNumberberth(3);
        if(boat3.isEmpty()){
            Boat boat = new Boat();
            boat.setNameenrollment("Mars");
            boat.setName("Mars");
            boat.setNumberberth(3);
            boat.setFee(1500);
            listBoat.add(boat);
            iBoatRepository.save(boat);
        };

        //Propietarios

        Optional<Shipowner> shipowner1 = Optional.ofNullable(iShipownerRepository.findByUsername("shipowner1@clubnautico.com"));
        if (shipowner1.isEmpty()){
            Shipowner shipowner = new Shipowner();
            shipowner.setUsername("shipowner1@clubnautico.com");
            shipowner.setName("shipowner1Name");
            shipowner.setSurname("shipowner1Surname");
            shipowner.setCountry("shipowner1Country");
            shipowner.setDni("12345678X");
            shipowner.setPhone("123456789");
            shipowner.getBoats().add(listBoat.get(0));
            iShipownerRepository.save(shipowner);

        }

        Optional<Shipowner> shipowner2 = Optional.ofNullable(iShipownerRepository.findByUsername("shipowner2@clubnautico.com"));
        if (shipowner2.isEmpty()){
            Shipowner shipowner = new Shipowner();
            shipowner.setUsername("shipowner2@clubnautico.com");
            shipowner.setName("shipowner2Name");
            shipowner.setSurname("shipowner2Surname");
            shipowner.setCountry("shipowner2Country");
            shipowner.setDni("12345678Y");
            shipowner.setPhone("123456789");
            shipowner.getBoats().add(listBoat.get(1));
            iShipownerRepository.save(shipowner);
        }

        Optional<Shipowner> shipowner3 = Optional.ofNullable(iShipownerRepository.findByUsername("shipowner3@clubnautico.com"));
        if (shipowner3.isEmpty()){
            Shipowner shipowner = new Shipowner();
            shipowner.setUsername("shipowner3@clubnautico.com");
            shipowner.setName("shipowner3Name");
            shipowner.setSurname("shipowner3Surname");
            shipowner.setCountry("shipowner3Country");
            shipowner.setDni("12345678Z");
            shipowner.setPhone("123456789");
            shipowner.getBoats().add(listBoat.get(2));
            iShipownerRepository.save(shipowner);
        }


        //Salidas
        Optional<Departure> departure1 = iDepartureRepository.findById(1);

        if (departure1.isEmpty()){
            Boat finalBoat = listBoat.get(0);
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("menorca");
            newDeparture.setBoat(finalBoat);
            iDepartureRepository.save(newDeparture);
        }

        Optional<Departure> departure2 = iDepartureRepository.findById(2);

        if (departure2.isEmpty()){
            Boat finalBoat2 = listBoat.get(1);
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("Mallorca");
            newDeparture.setBoat(finalBoat2);
            iDepartureRepository.save(newDeparture);
        }


        Optional<Departure> departure3 = iDepartureRepository.findById(3);

        if (departure3.isEmpty()){
            Boat finalBoat3 = listBoat.get(2);
            Departure newDeparture = new Departure();
            newDeparture.setDate(new Date());
            newDeparture.setTime("31/01/2024");
            newDeparture.setDestination("Ibiza");
            newDeparture.setBoat(finalBoat3);
            iDepartureRepository.save(newDeparture);
        }

    }*/
}
