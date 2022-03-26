package chinonsoharrison.service;

import chinonsoharrison.messages.MessageResponses;
import chinonsoharrison.pojo.DronePojo;
import chinonsoharrison.pojo.Medication;

public interface DispatchService {
    MessageResponses registerDrone(DronePojo drone);

    MessageResponses loadDroneWithMedications(Medication medication, long droneId);

    MessageResponses checkMedicationItemInDrone(long droneId);

    MessageResponses checkAvailableDrones();

    MessageResponses checkBatteryLevelForDrone(long droneId);
}
