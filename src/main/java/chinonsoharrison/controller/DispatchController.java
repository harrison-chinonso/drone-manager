package chinonsoharrison.controller;

import chinonsoharrison.messages.MessageResponses;
import chinonsoharrison.pojo.DronePojo;
import chinonsoharrison.pojo.Medication;
import chinonsoharrison.service.DispatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/dispatch/")
@Slf4j
public class DispatchController {

    private final DispatchService dispatchService;

    public DispatchController(DispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }


    @PostMapping(path = "drone/register")
    ResponseEntity<?> register(@RequestBody DronePojo request) {
        MessageResponses res = dispatchService.registerDrone(request);
        if(res.getCode().equals("success")) return new ResponseEntity<>(res, HttpStatus.OK);
        else return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "loadMedicationItem/{droneId}")
    ResponseEntity<?> loadMedicationItem(@RequestBody Medication request, @PathVariable("droneId") long droneId) {
        MessageResponses response = dispatchService.loadDroneWithMedications(request, droneId);
        if(response.getCode().equals("success")) return new ResponseEntity<>(response, HttpStatus.OK);
        else return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "checkMedicationItem/{droneId}")
    ResponseEntity<?> checkMedicationItem(@PathVariable("droneId") long droneId) {
        MessageResponses response = dispatchService.checkMedicationItemInDrone(droneId);
        if(response.getCode().equals("success")) return new ResponseEntity<>(response, HttpStatus.OK);
        else return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "checkBatteryLevel/{droneId}")
    ResponseEntity<?> checkBatteryLevelForDrone(@PathVariable("droneId") long droneId) {
        MessageResponses response = dispatchService.checkBatteryLevelForDrone(droneId);
        if(response.getCode().equals("success")) return new ResponseEntity<>(response, HttpStatus.OK);
        else return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "checkAvailableDrones")
    ResponseEntity<?> checkAvailableDrones() {
        MessageResponses response = dispatchService.checkAvailableDrones();
        if(response.getCode().equals("success")) return new ResponseEntity<>(response, HttpStatus.OK);
        else return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
