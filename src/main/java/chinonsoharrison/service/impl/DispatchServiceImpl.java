package chinonsoharrison.service.impl;

import chinonsoharrison.entity.Drone;
import chinonsoharrison.enums.State;
import chinonsoharrison.exception.DispatchException;
import chinonsoharrison.messages.MessageResponses;
import chinonsoharrison.pojo.DronePojo;
import chinonsoharrison.pojo.Medication;
import chinonsoharrison.repository.DroneRepository;
import chinonsoharrison.service.DispatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service("DispatchServiceImpl")
public class DispatchServiceImpl implements DispatchService {

    private final DroneRepository droneRepository;

    public DispatchServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public MessageResponses registerDrone(DronePojo dronepojo) {
        try{
            Drone drone = new Drone();
            BeanUtils.copyProperties(dronepojo, drone); //Create drone object from client side pojo
            drone.setState(State.IDLE); //Set new drone state to Idea
            droneRepository.save(drone); //Register Drone
            return new MessageResponses(MessageResponses.CODE_OK, MessageResponses.MESSAGE_CREATE, drone);
        }catch(Exception e){
            //Return Message Object with exception
            return new MessageResponses(MessageResponses.CODE_ERROR, e.getMessage(), null);
        }
    }

    @Override
    public MessageResponses loadDroneWithMedications(Medication medication, long droneId) {
        try{
            Drone drone = retrieveDrone(droneId);
log.info("DRONE {}",drone);
            if(State.LOADED.equals(drone.getState())){ //Check if drone is in loaded state
                throw new DispatchException("Drone is loaded");
            }

            if(drone.getWeightLimit() < medication.getWeight()){ //Check if drone can carry medication
                throw new DispatchException("Medication weight above drone weight limit");
            }

            if(drone.getBatteryCapacity() < 25){ //Check if drone's battery percentage can handle loading state
                throw new DispatchException("Drone's battery capacity is less than 25%");
            }

            drone.setMedications(medication); //Load medication into drone
            drone.setState(State.LOADED); //Set drone's state to loaded

            droneRepository.save(drone); //Save loaded drone
            return new MessageResponses(MessageResponses.CODE_OK, MessageResponses.MESSAGE_COMPLETED, drone);
        }catch(Exception e){
            return new MessageResponses(MessageResponses.CODE_ERROR, e.getMessage(), null);
        }
    }

    @Override
    public MessageResponses checkMedicationItemInDrone(long droneId) {
        try{
            Drone drone = retrieveDrone(droneId);
            return new MessageResponses(MessageResponses.CODE_OK, MessageResponses.MESSAGE_COMPLETED, drone.getMedications());
        }catch(Exception e){
            //Return Message Object with exception
            return new MessageResponses(MessageResponses.CODE_ERROR, e.getMessage(), null);
        }
    }

    @Override
    public MessageResponses checkAvailableDrones() {
        try{
            List<Drone> drones = droneRepository.findAllByStateEquals(State.IDLE);
            return new MessageResponses(MessageResponses.CODE_OK, MessageResponses.MESSAGE_COMPLETED, drones);
        }catch(Exception e){
            //Return Message Object with exception
            return new MessageResponses(MessageResponses.CODE_ERROR, e.getMessage(), null);
        }
    }

    @Override
    public MessageResponses checkBatteryLevelForDrone(long droneId) {
        try{
            int batteryCapacity = checkBatteryLevel(droneId);
            return new MessageResponses(MessageResponses.CODE_OK, MessageResponses.MESSAGE_COMPLETED, batteryCapacity);
        }catch(Exception e){
            //Return Message Object with exception
            return new MessageResponses(MessageResponses.CODE_ERROR, e.getMessage(), null);
        }
    }

    private Drone retrieveDrone(long droneId){
        Optional<Drone> droneOpt = droneRepository.findById(droneId); //Retrieve drone from database

        if(!droneOpt.isPresent()){ //Check if drone is retrieved from database
            throw new DispatchException("Drone not found");
        }

        return droneOpt.get(); //Return retrieved drone into drone object
    }

    public int checkBatteryLevel(long droneId){
        Drone drone = retrieveDrone(droneId);
        return drone.getBatteryCapacity();
    }
}
