package chinonsoharrison.utility;


import chinonsoharrison.entity.Drone;
import chinonsoharrison.enums.Model;
import chinonsoharrison.enums.State;
import chinonsoharrison.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private DroneRepository droneRepository;

    @Autowired
    public DataLoader(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    public void run(ApplicationArguments args) {
        droneRepository.save(new Drone(1l,"8656DFBWHT435", Model.Cruiserweight,new Float(420),99,State.IDLE, null));
        droneRepository.save(new Drone(2l,"8762JKHRWRBU9",Model.Heavyweight,new Float(290),91,State.IDLE,null));
        droneRepository.save(new Drone(3l,"KNWNRN9384909",Model.Lightweight,new Float(400),72,State.IDLE,null));
    }
}