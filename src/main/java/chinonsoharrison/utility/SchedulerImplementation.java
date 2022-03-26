package chinonsoharrison.utility;

import chinonsoharrison.entity.Drone;
import chinonsoharrison.repository.DroneRepository;
import chinonsoharrison.service.DispatchService;
import chinonsoharrison.service.impl.DispatchServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
@Slf4j
public class SchedulerImplementation {
  DispatchServiceImpl dispatchService;
  DroneRepository droneRepository;

    public SchedulerImplementation(DispatchServiceImpl dispatchService, DroneRepository droneRepository) {
        this.dispatchService = dispatchService;
        this.droneRepository = droneRepository;
    }

    @Scheduled(fixedDelay = 60*60*3000, initialDelay = 9000)
    public void runReNotifyTask() {
        log.info("Run task started");

        List<Drone> allDrones = droneRepository.findAll();
        Map<Long,Integer> audit = new HashMap<>();

        for(Drone drone : allDrones){ // Loop through entire drone in store
           int batteryLevel = dispatchService.checkBatteryLevel(drone.getId()); // Find current drone battery capacity
            audit.put(drone.getId(), batteryLevel); // Create history/audit event log
        }
    }
}
